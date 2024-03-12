package com.example.microservicio_pagos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String currency;
    private String status;
    private String mercadoPagoPaymentId;
    private String paymentMethod;
    private String paymentType;
    private String mercadoPagoStatusDetail;
    private Long userId;
    private LocalDateTime paymentDate;

    public Payment() {
    }



    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }

    public String getMercadoPagoPaymentId() {
        return mercadoPagoPaymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getMercadoPagoStatusDetail() {
        return mercadoPagoStatusDetail;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMercadoPagoPaymentId(String mercadoPagoPaymentId) {
        this.mercadoPagoPaymentId = mercadoPagoPaymentId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setMercadoPagoStatusDetail(String mercadoPagoStatusDetail) {
        this.mercadoPagoStatusDetail = mercadoPagoStatusDetail;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}

