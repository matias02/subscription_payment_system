package com.example.microservicio_pagos.dto;

public class SettingsDTO {
    private String cardNumberValidation;
    private Integer cardNumberLength;
    private String binPattern;
    private Integer securityCodeLength;
    private String securityCodeLocation;


    public String getCardNumberValidation() {
        return cardNumberValidation;
    }

    public Integer getCardNumberLength() {
        return cardNumberLength;
    }

    public String getBinPattern() {
        return binPattern;
    }

    public Integer getSecurityCodeLength() {
        return securityCodeLength;
    }

    public String getSecurityCodeLocation() {
        return securityCodeLocation;
    }

    public void setCardNumberValidation(String cardNumberValidation) {
        this.cardNumberValidation = cardNumberValidation;
    }

    public void setCardNumberLength(Integer cardNumberLength) {
        this.cardNumberLength = cardNumberLength;
    }

    public void setBinPattern(String binPattern) {
        this.binPattern = binPattern;
    }

    public void setSecurityCodeLength(Integer securityCodeLength) {
        this.securityCodeLength = securityCodeLength;
    }

    public void setSecurityCodeLocation(String securityCodeLocation) {
        this.securityCodeLocation = securityCodeLocation;
    }

}
