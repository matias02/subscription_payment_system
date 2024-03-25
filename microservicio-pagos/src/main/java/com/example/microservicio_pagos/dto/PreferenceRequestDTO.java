package com.example.microservicio_pagos.dto;

import java.util.List;

public class PreferenceRequestDTO {
    private List<ItemDTO> items;
    private PayerDTO payer;
    private String autoReturn;
    private String notificationUrl;
    private String externalReference;
    private BackUrlsDTO backUrls;

    private PaymentMethodsDTO paymentMethods;


    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public PayerDTO getPayer() {
        return payer;
    }

    public void setPayer(PayerDTO payer) {
        this.payer = payer;
    }

    public String getAutoReturn() {
        return autoReturn;
    }



    public void setAutoReturn(String autoReturn) {
        this.autoReturn = autoReturn;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public BackUrlsDTO getBackUrls() {
        return backUrls;
    }

    public void setBackUrls(BackUrlsDTO backUrls) {
        this.backUrls = backUrls;
    }
}
