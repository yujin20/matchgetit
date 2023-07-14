package com.matchgetit.backend.controller;

import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.service.AdminDashboardService;
import com.matchgetit.backend.service.AdminPageUserService;
import com.matchgetit.backend.service.PaymentHistoryService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminMainController {
    private final AdminDashboardService dashboardService;
    private final AdminPageUserService userService;
    private final PaymentHistoryService paymentService;

    @PostConstruct
    public void createUsers() {
//        userService.createUsers();
//        dashboardService.createManagers();
//        paymentService.createPayments();
//        dashboardService.createDashboradDataEntity();
    }

    @GetMapping("/matchGetIt/admin/gate")
    public String gate(HttpSession session, Model model, HttpServletRequest request) {
        System.out.println(session.getAttribute("member"));
        System.out.println(session.getAttribute("jwtToken"));
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null || member.getLoginType() != LogInType.ADMIN) {
            model.addAttribute("msg", "잘못된 접근입니다.");
            model.addAttribute("url", "http://localhost:3000/");
            return "admin/components/Utils/alert";
        }

//        return "forword:/matchGetIt/admin/login";
        return "redirect:/matchGetIt/admin";
    }


    @GetMapping(value = {"/matchGetIt/admin"})
    public String mainPage(Model model) {
        Map<String, Long> userCounts = dashboardService.getUserCounts();
        model.addAttribute("userCounts", userCounts);

        Map<String, Long> managerCounts = dashboardService.getManagerCounts();
        model.addAttribute("managerCounts", managerCounts);

        Map<String, Long> matchCounts = dashboardService.getMatchCounts();
        model.addAttribute("matchCounts", matchCounts);

        Map<String, Long> inquiryCounts = dashboardService.getInquiryCounts();
        model.addAttribute("inquiryCounts", inquiryCounts);

        return "admin/pages/Dashboard/Dashboard";
    }

}
