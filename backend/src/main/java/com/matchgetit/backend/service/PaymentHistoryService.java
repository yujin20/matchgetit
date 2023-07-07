package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.PaymentStatus;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PaymentRecordDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PaymentRecordEntity;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.repository.PaymentRecordRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentHistoryService {

    private final PaymentRecordRepository paymentRecordRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public List<PaymentRecordEntity> getPaymentRecords() {
        return paymentRecordRepository.findAll();
    }

    public List<PaymentRecordEntity> getPaymentRecordsByDateRange(Date startDate, Date endDate) {
        return paymentRecordRepository.findByTransactionDateBetween(startDate, endDate);
    }

    public List<PaymentRecordEntity> getPaymentRecordsBySearchCondition(String condition, String keyword) {
        if (condition.equals("name")) {
            // 회원 이름으로 결제 기록 검색
            List<MemberEntity> members = memberRepository.findByNameContaining(keyword);
            List<PaymentRecordEntity> paymentRecords = new ArrayList<>();
            for (MemberEntity member : members) {
                List<PaymentRecordEntity> records = paymentRecordRepository.findByMemberContaining(member);
                paymentRecords.addAll(records);
            }
            return paymentRecords;
        } else if (condition.equals("userId")) {
            // 회원 ID로 결제 기록 검색
            Long userId = Long.valueOf(keyword);
            MemberEntity member = memberRepository.findByUserId(userId);
            if (member != null) {
                return paymentRecordRepository.findByMemberContaining(member);
            }
        } else if (condition.equals("gameNumber")) {
            // 게임 번호로 결제 기록 검색
            return paymentRecordRepository.findByGameNumberContaining(keyword);
        }
        return null;
    }

    public List<PaymentRecordEntity> getPaymentRecordsByStatus(List<PaymentStatus> statuses) {
        return paymentRecordRepository.findByTransactionStatusIn(statuses);
    }


    public List<PaymentRecordDTO> getPaymentRecordDTOList() {
        List<PaymentRecordEntity> paymentList = paymentRecordRepository.findAll();
        List<PaymentRecordDTO> paymentDTOList = new ArrayList<>();

        modelMapper.typeMap(PaymentRecordEntity.class, PaymentRecordDTO.class)
                .addMappings(mapping -> {
                    mapping.map(PaymentRecordEntity::getTransactionDate, PaymentRecordDTO::setTransactionDateTime);
                    mapping.map(PaymentRecordEntity::getCancelDate, PaymentRecordDTO::setCancelDateTime);
                    mapping.map(PaymentRecordEntity::getTransactionStatus, PaymentRecordDTO::setPaymentStatus);
                });

        modelMapper.typeMap(MemberEntity.class, MemberDTO.class)
                .addMappings(mapping -> {
                    mapping.skip(MemberDTO::setPaymentRecordDTO);
                    mapping.skip(MemberDTO::setPaymentRecordEntityList);
                });

        for (PaymentRecordEntity payment: paymentList) {
            PaymentRecordDTO paymentDTO = modelMapper.map(payment, PaymentRecordDTO.class);
            paymentDTO.setUserId(modelMapper.map(payment.getMember(), MemberDTO.class));
            paymentDTOList.add(paymentDTO);
        }

        return paymentDTOList;
    }

}
