package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.PaymentStatus;
import com.matchgetit.backend.entity.PaymentRecordEntity;
import com.matchgetit.backend.repository.PaymentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentHistoryService {

    private final PaymentRecordRepository paymentRecordRepository;

    @Autowired
    public PaymentHistoryService(PaymentRecordRepository paymentRecordRepository) {
        this.paymentRecordRepository = paymentRecordRepository;
    }

    public List<PaymentRecordEntity> getPaymentRecords() {
        return paymentRecordRepository.findAll();
    }

    public List<PaymentRecordEntity> getPaymentRecordsByDateRange(Date startDate, Date endDate) {
        return paymentRecordRepository.findByTransactionDateBetween(startDate, endDate);
    }

    public List<PaymentRecordEntity> getPaymentRecordsBySearchCondition(String condition, String keyword) {
        if (condition.equals("name")) {
            return paymentRecordRepository.findByUserNameContaining(keyword);
        } else if (condition.equals("userId")) {
            return paymentRecordRepository.findByUserId(Integer.valueOf(keyword));
        } else if (condition.equals("gameNumber")) {
            return paymentRecordRepository.findByGameNumberContaining(keyword);
        }
        return null;
    }

    public List<PaymentRecordEntity> getPaymentRecordsByStatus(List<PaymentStatus> statuses) {
        return paymentRecordRepository.findByTransactionStatusIn(statuses);
    }
}
