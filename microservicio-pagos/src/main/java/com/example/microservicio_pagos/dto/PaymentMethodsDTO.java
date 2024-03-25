package com.example.microservicio_pagos.dto;

import java.util.List;

public class PaymentMethodsDTO {
    private String id;
    private String name;
    private String paymentTypeId;
    private String status;
    private String secureThumbnail;
    private String thumbnail;
    private String deferredCapture;
    private List<SettingsDTO> settings;
    private List<String> additionalInfoNeeded;
    private Double minAllowedAmount;
    private Double maxAllowedAmount;
    private Integer accreditationTime;
    private List<FinancialInstitutionDTO> financialInstitutions;
    private List<String> processingModes;

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public String getStatus() {
        return status;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDeferredCapture() {
        return deferredCapture;
    }

    public List<SettingsDTO> getSettings() {
        return settings;
    }

    public List<String> getAdditionalInfoNeeded() {
        return additionalInfoNeeded;
    }

    public Double getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public Double getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public Integer getAccreditationTime() {
        return accreditationTime;
    }

    public List<FinancialInstitutionDTO> getFinancialInstitutions() {
        return financialInstitutions;
    }

    public List<String> getProcessingModes() {
        return processingModes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDeferredCapture(String deferredCapture) {
        this.deferredCapture = deferredCapture;
    }

    public void setSettings(List<SettingsDTO> settings) {
        this.settings = settings;
    }

    public void setAdditionalInfoNeeded(List<String> additionalInfoNeeded) {
        this.additionalInfoNeeded = additionalInfoNeeded;
    }

    public void setMinAllowedAmount(Double minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public void setMaxAllowedAmount(Double maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public void setAccreditationTime(Integer accreditationTime) {
        this.accreditationTime = accreditationTime;
    }

    public void setFinancialInstitutions(List<FinancialInstitutionDTO> financialInstitutions) {
        this.financialInstitutions = financialInstitutions;
    }

    public void setProcessingModes(List<String> processingModes) {
        this.processingModes = processingModes;
    }

    public PaymentMethodsDTO() {
    }

    public PaymentMethodsDTO(String id, String name, String paymentTypeId, String status, String secureThumbnail, String thumbnail, String deferredCapture, List<SettingsDTO> settings, List<String> additionalInfoNeeded, Double minAllowedAmount, Double maxAllowedAmount, Integer accreditationTime, List<FinancialInstitutionDTO> financialInstitutions, List<String> processingModes) {
        this.id = id;
        this.name = name;
        this.paymentTypeId = paymentTypeId;
        this.status = status;
        this.secureThumbnail = secureThumbnail;
        this.thumbnail = thumbnail;
        this.deferredCapture = deferredCapture;
        this.settings = settings;
        this.additionalInfoNeeded = additionalInfoNeeded;
        this.minAllowedAmount = minAllowedAmount;
        this.maxAllowedAmount = maxAllowedAmount;
        this.accreditationTime = accreditationTime;
        this.financialInstitutions = financialInstitutions;
        this.processingModes = processingModes;
    }
}