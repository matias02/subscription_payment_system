package com.example.microservicio_pagos.model;




import java.util.Date;
import java.util.List;

public class PreferenceRequest {
    private List<Item> items;
    private Payer payer;
    private BackUrls backUrls;
    private String autoReturn;
    private PaymentMethods paymentMethods;
    private String notificationUrl;
    private String statementDescriptor;
    private String externalReference;
    private Boolean expires;
    private Date expirationDateFrom;
    private Date expirationDateTo;
    public List<Item> getItems() {
        return items;
    }

    public Payer getPayer() {
        return payer;
    }

    public BackUrls getBackUrls() {
        return backUrls;
    }

    public String getAutoReturn() {
        return autoReturn;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public Boolean getExpires() {
        return expires;
    }

    public Date getExpirationDateFrom() {
        return expirationDateFrom;
    }

    public Date getExpirationDateTo() {
        return expirationDateTo;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public void setBackUrls(BackUrls backUrls) {
        this.backUrls = backUrls;
    }

    public void setAutoReturn(String autoReturn) {
        this.autoReturn = autoReturn;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public void setExpires(Boolean expires) {
        this.expires = expires;
    }

    public void setExpirationDateFrom(Date expirationDateFrom) {
        this.expirationDateFrom = expirationDateFrom;
    }

    public void setExpirationDateTo(Date expirationDateTo) {
        this.expirationDateTo = expirationDateTo;
    }





    public static class Builder {
        private List<Item> items;
        private Payer payer;
        private BackUrls backUrls;
        private String autoReturn;
        private PaymentMethods paymentMethods;
        private String notificationUrl;
        private String statementDescriptor;
        private String externalReference;
        private Boolean expires;
        private Date expirationDateFrom;
        private Date expirationDateTo;


        public Builder items(List<Item> items) { // Cambio hecho aquí
            this.items = items;
            return this;
        }

        public Builder payer(Payer payer) {
            this.payer = payer;
            return this;
        }

        public Builder backUrls(BackUrls backUrls) {
            this.backUrls = backUrls;
            return this;
        }

        public Builder autoReturn(String autoReturn) {
            this.autoReturn = autoReturn;
            return this;
        }

        public Builder paymentMethods(PaymentMethods paymentMethods) {
            this.paymentMethods = paymentMethods;
            return this;
        }

        public Builder notificationUrl(String notificationUrl) {
            this.notificationUrl = notificationUrl;
            return this;
        }

        public Builder statementDescriptor(String statementDescriptor) {
            this.statementDescriptor = statementDescriptor;
            return this;
        }

        public Builder externalReference(String externalReference) {
            this.externalReference = externalReference;
            return this;
        }

        public Builder expires(Boolean expires) {
            this.expires = expires;
            return this;
        }

        public Builder expirationDateFrom(Date expirationDateFrom) {
            this.expirationDateFrom = expirationDateFrom;
            return this;
        }

        public Builder expirationDateTo(Date expirationDateTo) {
            this.expirationDateTo = expirationDateTo;
            return this;
        }

        public PreferenceRequest build() {
            PreferenceRequest preferenceRequest = new PreferenceRequest();
            preferenceRequest.items = this.items;
            preferenceRequest.payer = this.payer;
            preferenceRequest.backUrls = this.backUrls;
            preferenceRequest.autoReturn = this.autoReturn;
            preferenceRequest.paymentMethods = this.paymentMethods;
            preferenceRequest.notificationUrl = this.notificationUrl;
            preferenceRequest.statementDescriptor = this.statementDescriptor;
            preferenceRequest.externalReference = this.externalReference;
            preferenceRequest.expires = this.expires;
            preferenceRequest.expirationDateFrom = this.expirationDateFrom;
            preferenceRequest.expirationDateTo = this.expirationDateTo;
            return preferenceRequest;
        }
    }

}
