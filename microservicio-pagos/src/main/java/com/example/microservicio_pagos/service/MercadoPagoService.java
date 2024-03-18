package com.example.microservicio_pagos.service;

import com.example.microservicio_pagos.dto.PaymentRequest;
import com.example.microservicio_pagos.dto.PaymentResponse;
import com.example.microservicio_pagos.entity.Payment;
import com.example.microservicio_pagos.repository.PaymentRepository;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Optional;

@Service
public class MercadoPagoService {

    private final PaymentClient paymentClient;
    private final PaymentRepository paymentRepository;

    @Autowired
    public MercadoPagoService(PaymentClient paymentClient, PaymentRepository paymentRepository) {
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
        Payment savedPayment = paymentRepository.save(convertToEntity(createdPayment, paymentData));
        return convertToResponse(savedPayment);
    }

    public PaymentResponse getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return convertToResponse(payment.get());
        } else {
            throw new RuntimeException("Payment not found for this id :: " + id);
        }
    }


    public PaymentResponse updatePayment(Long id, PaymentRequest paymentData) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (!paymentOptional.isPresent()) {
            throw new RuntimeException("Payment not found for this id :: " + id);
        }

        Payment payment = paymentOptional.get();
        payment.setTransactionAmount(paymentData.getTransactionAmount());
        payment.setCurrencyId(paymentData.getCurrencyId());
        payment.setPaymentMethodId(paymentData.getPaymentMethodId());
        payment.setInstallments(paymentData.getInstallments());
        payment.setDescription(paymentData.getDescription());
        payment.setPayerEmail(paymentData.getPayer().getEmail());

        Payment updatedPayment = paymentRepository.save(payment);

        return convertToResponse(updatedPayment);
    }

    public void cancelPayment(Long id) throws MPException, MPApiException {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (!paymentOptional.isPresent()) {
            throw new RuntimeException("Payment not found for this id :: " + id);
        }

        Payment payment = paymentOptional.get();
        String status = payment.getStatus();

        if (!status.equals("pending") && !status.equals("in_process")) {
            throw new RuntimeException("Only payments that are 'pending' or 'in_process' can be cancelled.");
        }


        payment.setStatus("cancelled");
        paymentRepository.save(payment);
    }


    public PaymentResponse refundPayment(Long id) throws MPException, MPApiException {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (!paymentOptional.isPresent()) {
            throw new RuntimeException("Payment not found for this id :: " + id);
        }

        Payment payment = paymentOptional.get();
        payment.setStatus("refunded");
        Payment updatedPayment = paymentRepository.save(payment);

        return convertToResponse(updatedPayment);
    }

    private Payment convertToEntity(com.mercadopago.resources.payment.Payment mercadoPagoPayment, PaymentRequest paymentData) {
        Payment payment = new Payment();
        payment.setId(mercadoPagoPayment.getId());
        payment.setStatus(mercadoPagoPayment.getStatus());
        payment.setPaymentMethodId(mercadoPagoPayment.getPaymentMethodId());
        payment.setCurrencyId(paymentData.getCurrencyId());
        payment.setTransactionAmount(mercadoPagoPayment.getTransactionAmount());
        payment.setInstallments(paymentData.getInstallments());
        payment.setDescription(paymentData.getDescription());

        payment.setPayerEmail(paymentData.getPayer().getEmail());

        payment.setPaymentTypeId(mercadoPagoPayment.getPaymentTypeId());
        payment.setDateApproved(mercadoPagoPayment.getDateApproved() != null ?
                mercadoPagoPayment.getDateApproved().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() :
                null);
        payment.setExternalReference(mercadoPagoPayment.getExternalReference());

        return payment;
    }


    private PaymentResponse convertToResponse(Payment payment) {
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
