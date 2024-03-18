package com.example.microservicio_pagos.service;

import com.example.microservicio_pagos.dto.PaymentRequest;
import com.example.microservicio_pagos.dto.PaymentResponse;
import com.example.microservicio_pagos.entity.Payment;
import com.example.microservicio_pagos.repository.PaymentRepository;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class PaymentService {

    private final PaymentClient paymentClient;
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentClient paymentClient, PaymentRepository paymentRepository) {
        this.paymentClient = paymentClient;
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse createPayment(PaymentRequest paymentData) throws MPException, MPApiException {
        PaymentCreateRequest paymentRequest = PaymentCreateRequest.builder()
                .transactionAmount(paymentData.getTransactionAmount())
                .token(paymentData.getToken())
                .description(paymentData.getDescription())
                .installments(paymentData.getInstallments())
                .paymentMethodId(paymentData.getPaymentMethodId())
                .payer(PaymentPayerRequest.builder()
                        .email(paymentData.getPayer().getEmail())
                        .identification(IdentificationRequest.builder()
                                .type(paymentData.getPayer().getIdentificationType())
                                .number(paymentData.getPayer().getIdentificationNumber())
                                .build())
                        .build())
                .build();

        com.mercadopago.resources.payment.Payment createdPayment = paymentClient.create(paymentRequest);

        Payment savedPayment = convertToEntity(createdPayment, paymentData);
        paymentRepository.save(savedPayment);
        return convertToPaymentResponse(savedPayment);
    }

    private Payment convertToEntity(com.mercadopago.resources.payment.Payment mercadoPagoPayment, PaymentRequest paymentData) {
        Payment payment = new Payment();
        payment.setId(mercadoPagoPayment.getId());
        payment.setStatus(mercadoPagoPayment.getStatus());
        payment.setPaymentMethodId(mercadoPagoPayment.getPaymentMethodId());
        payment.setPaymentTypeId(mercadoPagoPayment.getPaymentTypeId());
        payment.setCurrencyId(paymentData.getCurrencyId());
        payment.setTransactionAmount(mercadoPagoPayment.getTransactionAmount());
        payment.setInstallments(mercadoPagoPayment.getInstallments());
        payment.setDescription(mercadoPagoPayment.getDescription());
        payment.setDateCreated(mercadoPagoPayment.getDateCreated() != null ? mercadoPagoPayment.getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null);
        payment.setDateApproved(mercadoPagoPayment.getDateApproved() != null ? mercadoPagoPayment.getDateApproved().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null);
        payment.setOperationType(mercadoPagoPayment.getOperationType());
        if (mercadoPagoPayment.getPayer() != null && mercadoPagoPayment.getPayer().getId() != null) {
            payment.setPayerId(mercadoPagoPayment.getPayer().getId());
        }
        payment.setExternalReference(mercadoPagoPayment.getExternalReference());
        payment.setAdditionalInfo(mercadoPagoPayment.getAdditionalInfo() != null ? mercadoPagoPayment.getAdditionalInfo().toString() : null);
        return payment;
    }

    private PaymentResponse convertToPaymentResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getId());
        response.setStatus(payment.getStatus());
        response.setTransactionAmount(payment.getTransactionAmount());
        response.setCurrencyId(payment.getCurrencyId());
        response.setDateApproved(payment.getDateApproved());
        response.setPaymentMethodId(payment.getPaymentMethodId());
        response.setInstallments(payment.getInstallments());
        response.setDescription(payment.getDescription());
        response.setPayerEmail(payment.getPayerEmail());
        response.setExternalReference(payment.getExternalReference());
        return response;


        
    }
}

