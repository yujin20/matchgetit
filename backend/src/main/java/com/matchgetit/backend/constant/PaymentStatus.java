package com.matchgetit.backend.constant;

public enum PaymentStatus {
    COMPLETED("결제 완료"),  // 결제 완료
    CANCELED("결제 취소"),   // 결제 취소
    REFUNDED("부분 환불");   // 부분 환불

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
