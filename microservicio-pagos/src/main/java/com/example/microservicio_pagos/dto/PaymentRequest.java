package com.example.microservicio_pagos.dto;

import java.math.BigDecimal;

public class PaymentRequest {
    private BigDecimal transactionAmount;
    private String currencyId;
    private String paymentMethodId;
    private Integer installments;
    private String token;
    private String description;
    private Payer payer;

    private BigDecimal Amount;

    public PaymentRequest() {
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public BigDecimal getAmount(){
        return Amount;
    }


    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void setAmount(BigDecimal Amount){
        this.Amount = Amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public static class Payer {
        private String email;
        private String identificationType;
        private String identificationNumber;

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getIdentificationType() {
            return identificationType;
        }
        public void setIdentification(String identificationType) {
            this.identificationType = identificationType;
        }
        public String getIdentificationNumber() {
            return identificationNumber;
        }
        public void setIdentificationNumber(String identificationNumber) {
            this.identificationNumber = identificationNumber;
        }
    }


}
