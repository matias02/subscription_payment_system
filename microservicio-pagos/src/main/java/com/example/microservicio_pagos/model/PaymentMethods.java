package com.example.microservicio_pagos.model;

import java.util.List;

public class PaymentMethods {
    private List<String> excludedPaymentMethods;
    private List<String> excludedPaymentTypes;

    public PaymentMethods() {}

    public PaymentMethods(List<String> excludedPaymentMethods, List<String> excludedPaymentTypes) {
        this.excludedPaymentMethods = excludedPaymentMethods;
        this.excludedPaymentTypes = excludedPaymentTypes;
    }

    public List<String> getExcludedPaymentMethods() {
        return excludedPaymentMethods;
    }

    public void setExcludedPaymentMethods(List<String> excludedPaymentMethods) {
        this.excludedPaymentMethods = excludedPaymentMethods;
    }

    public List<String> getExcludedPaymentTypes() {
        return excludedPaymentTypes;
    }

    public void setExcludedPaymentTypes(List<String> excludedPaymentTypes) {
        this.excludedPaymentTypes = excludedPaymentTypes;
    }
}
