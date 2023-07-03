package com.matchgetit.backend.controller;

import com.matchgetit.backend.constant.*;
import com.matchgetit.backend.dto.ManagerDTO;
import com.matchgetit.backend.dto.ManagerSupportRecordDTO;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.entity.ManagerEntity;
import com.matchgetit.backend.entity.ManagerSupportRecordEntity;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.repository.ManagerRepository;
import com.matchgetit.backend.repository.ManagerSupportRecordRepository;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.service.ManagerService;
import com.matchgetit.backend.service.MemberService;
import com.matchgetit.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@Controller
@RequestMapping("/matchGetIt/manager")
@AllArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final ManagerSupportRecordRepository managerSupportRecordRepository;
    private final UserService userService; // UserService 인스턴스 추가
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final ManagerRepository managerRepository;
    private final MemberService memberService;



    @GetMapping("/managers/test")
    public List<ManagerEntity> getAllMembers() {
        return managerRepository.findAll();
    }

    @GetMapping("/managers")
    public String getManagerList(HttpServletRequest request) {
        List<MemberEntity> managerList = memberRepository.findByLoginType(LogInType.MANAGER);
        List<MemberDTO> memberDTOList = new ArrayList<>();
        System.out.println(managerList);

        for (MemberEntity memberEntity : managerList) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserId(memberEntity.getUserId());
            memberDTO.setName(memberEntity.getName());
            memberDTO.setPn(memberEntity.getPn());

            ManagerEntity manager = memberEntity.getManagerEntity();
            if(manager != null){
                String managerImage = manager.getManagerImage();

                ManagerDTO managerDTO = new ManagerDTO();
                managerDTO.setManagerImage(managerImage);

                memberDTO.setManagerDTO(managerDTO);
            }
            memberDTOList.add(memberDTO);
        }

        System.out.println(managerList.size());
        System.out.println("!");

        request.setAttribute("managerList", memberDTOList);
        return "admin/pages/Manage/Manager";
    }

    //매니저 삭제
    @PutMapping("/deactivate/{userId}")
    public ResponseEntity<String> deactivateManager(@PathVariable Long userId) {
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);
        if (memberEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        System.out.println("2323");
        // 매니저 지원 레코드 초기화
        ManagerSupportRecordEntity managerSupportRecordEntity = memberEntity.getManagerSupportRecordEntity();
        if (managerSupportRecordEntity != null) {
            memberEntity.setManagerSupportRecordEntity(null);
            managerSupportRecordEntity.setManagerUser(null);
            memberRepository.save(memberEntity);
            managerSupportRecordRepository.delete(managerSupportRecordEntity);
        }

        // 매니저 삭제
        ManagerEntity managerEntity = memberEntity.getManagerEntity();
        if (managerEntity != null) {
            memberEntity.setManagerEntity(null);
            managerEntity.setUser(null);
            memberRepository.save(memberEntity);
            managerRepository.delete(managerEntity);
        }

        // 거절 처리
        memberEntity.setLoginType(LogInType.NORMAL);
        memberRepository.save(memberEntity);

        return ResponseEntity.ok("매니저 등록을 거절하였습니다.");
    }


    //매니저 정보수정 (휴직신청때 필요)
    @PutMapping("/EditManager/updateManager/{managerId}")
    public ResponseEntity<String> updateManager(@PathVariable("managerId") Long managerId, @RequestBody ManagerDTO updatedManager) {
        ManagerDTO managerDTO = managerService.getManagerById(managerId);
        System.out.println("@");
        if (managerDTO != null) {
            // 수정된 매니저 정보 업데이트
            managerDTO.getUser().setName(updatedManager.getUser().getName());
            managerDTO.getUser().setPn(updatedManager.getUser().getPn());
            managerDTO.getUser().setGender(updatedManager.getUser().getGender());
            managerDTO.setEmploymentStatus(updatedManager.getEmploymentStatus());
            managerDTO.setLeaveStartDate(updatedManager.getLeaveStartDate());
            managerDTO.setLeaveEndDate(updatedManager.getLeaveEndDate());
            managerDTO.setLeaveReason(updatedManager.getLeaveReason());

            managerService.updateManager(managerId, managerDTO); // 변경된 매니저 정보 업데이트
            System.out.println("!");
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //일반유저가 매니저 신청때 사용되는 메서드
    @PostMapping("/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody ManagerSupportRecordDTO formDTO) {
        // 매니저 지원 양식 제출 처리
        ManagerSupportRecordEntity supportRecord = new ManagerSupportRecordEntity();
        supportRecord.setActivityZone(formDTO.getActivityZone());
        managerSupportRecordRepository.save(supportRecord);

        // MemberEntity 조회 및 값 업데이트
        Long userId = formDTO.getUser().getUserId(); // 유저 아이디 가져오기
        MemberEntity member = memberRepository.findByUserId(userId);
        if (member != null) {
            member.getManagerSupportRecordEntity().setActivityZone(formDTO.getActivityZone());
            member.setManagerSupportStatus(ManagerSupportStatus.WAITING); // 매니저 지원 상태값 업데이트
            memberRepository.save(member);
        }

        return ResponseEntity.ok("Form submitted successfully");
    }

    @GetMapping("/managerInfo/{userId}")
    public String viewManagerDetails(@PathVariable Long userId, Model model) {
        MemberDTO member = memberService.findMemberById(userId);

        ManagerDTO managerDTO = member.getManagerDTO();
        System.out.println(managerDTO);

        if(managerDTO != null){
            Long managerId = managerDTO.getManagerId();
            LocalDateTime registrationDate = managerDTO.getRegistrationDate();
            EmploymentStatus employmentStatus = managerDTO.getEmploymentStatus();

            model.addAttribute("managerId",managerId);
            model.addAttribute("registrationDate",registrationDate);
            model.addAttribute("employmentStatus",employmentStatus);

        }

        model.addAttribute("manager", member);
        return "admin/pages/Manage/ManagerInfo";
    }

    @GetMapping("/EditManager/{userId}")
    public String editManager (@PathVariable Long userId, Model model){
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);

        if (memberEntity != null) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserId(memberEntity.getUserId());
            memberDTO.setName(memberEntity.getName());
            memberDTO.setPn(memberEntity.getPn());
            memberDTO.setGender(memberEntity.getGender());

            ManagerEntity managerEntity = memberEntity.getManagerEntity();
            if (managerEntity != null){
                Long managerId = managerEntity.getManagerId();
                EmploymentStatus employmentStatus = managerEntity.getEmploymentStatus();

                ManagerDTO managerDTO = new ManagerDTO();
                managerDTO.setManagerId(managerId);
                managerDTO.setEmploymentStatus(employmentStatus);

                memberDTO.setManagerDTO(managerDTO);
            }

            model.addAttribute("manager", memberDTO);
        }
        return "admin/pages/Manage/EditManager";
    }






}
