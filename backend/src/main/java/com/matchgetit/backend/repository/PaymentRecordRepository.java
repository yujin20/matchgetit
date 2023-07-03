package com.matchgetit.backend.repository;

import com.matchgetit.backend.constant.PaymentStatus;
import com.matchgetit.backend.entity.PaymentRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecordEntity, Integer> {
    // 회원 번호로 레코드를 조회하는 메서드
    List<PaymentRecordEntity> findByUserId(Integer userId);

    // 결제 상태로 레코드를 조회하는 메서드
    List<PaymentRecordEntity> findByTransactionStatusIn(List<PaymentStatus> statuses);

    // 거래 일자 범위로 레코드를 조회하는 메서드
    List<PaymentRecordEntity> findByTransactionDateBetween(Date startDate, Date endDate);

    // 회원 이름으로 레코드를 조회하는 메서드
    List<PaymentRecordEntity> findByUserNameContaining(String name);

    // 게임 번호로 레코드를 조회하는 메서드
    List<PaymentRecordEntity> findByGameNumberContaining(String number);

    // 추가적인 메서드 정의 가능
}

