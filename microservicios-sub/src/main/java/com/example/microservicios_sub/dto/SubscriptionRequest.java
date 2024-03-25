package com.example.microservicios_sub.dto;

import java.time.LocalDate;

public class SubscriptionRequest {

    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String paymentMethodId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
    public SubscriptionRequest() {
    }

    public SubscriptionRequest(Long userId, LocalDate startDate, LocalDate endDate, String status, String paymentMethodId) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.paymentMethodId = paymentMethodId;
    }

}
