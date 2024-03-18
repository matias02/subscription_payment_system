package com.example.microservicio_pagos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private Long id;

    private String status;
    private String paymentMethodId;
    private String paymentTypeId;
    private String currencyId;
    private BigDecimal transactionAmount;
    private BigDecimal transactionDetails;
    private BigDecimal netReceivedAmount;
    private Integer installments;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateApproved;
    private String operationType;
    private String payerId;
    private String externalReference;
    private String additionalInfo;
    private String PayerEmail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }


    public String getPayerEmail() {
        return PayerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.PayerEmail = payerEmail;
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
    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }
    public BigDecimal getTransactionDetails () {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public void getTransactionDetails(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getNetReceivedAmount() {
        return netReceivedAmount;
    }

    public void setNetReceivedAmount(BigDecimal netReceivedAmount) {
        this.netReceivedAmount = netReceivedAmount;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(LocalDateTime dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getPayer() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}
