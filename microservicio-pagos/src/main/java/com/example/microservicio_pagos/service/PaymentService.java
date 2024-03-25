package com.example.microservicio_pagos.service;

import com.example.microservicio_pagos.dto.PreferenceRequestDTO;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.client.preference.PreferenceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PreferenceClient preferenceClient;
    private final PaymentClient paymentClient;
    private final RestTemplate restTemplate;
    private final String subscriptionServiceBaseUrl = "http://localhost:8080/api/subscriptions";

    private final List<String> supportedPaymentMethods = Arrays.asList("visa", "mastercard", "paypal");

    @Autowired
    public PaymentService(PreferenceClient preferenceClient, PaymentClient paymentClient, RestTemplate restTemplate) {
        this.preferenceClient = preferenceClient;
        this.paymentClient = paymentClient;
        this.restTemplate = restTemplate;
    }
    public String createPaymentPreference(PreferenceRequestDTO dto) throws MPException, MPApiException {
        PreferenceRequest sdkPreferenceRequest = convertToSdkPreferenceRequest(dto);
        Preference preference = preferenceClient.create(sdkPreferenceRequest);
        return preference.getInitPoint();
    }

    private PreferenceRequest convertToSdkPreferenceRequest(PreferenceRequestDTO dto) {
        List<PreferenceItemRequest> items = dto.getItems().stream()
                .map(item -> PreferenceItemRequest.builder()
                        .title(item.getTitle())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build())
                .collect(Collectors.toList());

        PreferencePayerRequest payer = PreferencePayerRequest.builder()
                .name(dto.getPayer().getName())
                .surname(dto.getPayer().getSurname())
                .email(dto.getPayer().getEmail())
                .build();

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success(dto.getBackUrls().getSuccess())
                .failure(dto.getBackUrls().getFailure())
                .pending(dto.getBackUrls().getPending())
                .build();


        return PreferenceRequest.builder()
                .items(items)
                .payer(payer)
                .backUrls(backUrls)
                .autoReturn(dto.getAutoReturn())
                .notificationUrl(dto.getNotificationUrl())
                .build();
    }

    public void handlePaymentNotification(String notificationType, Long dataId) {
        if ("payment".equals(notificationType)) {
            try {
                Payment payment = paymentClient.get(dataId);

                if (payment != null && "approved".equals(payment.getStatus())) {
                    Long subscriptionId = obtainSubscriptionIdFromPayment(payment);
                    updateSubscriptionStatus(subscriptionId, "ACTIVE");
                }
            } catch (MPException | MPApiException e) {
                System.out.println("Error al consultar el estado del pago: " + e.getMessage());
            }
        }
    }



    private void updateSubscriptionStatus(Long subscriptionId, String newStatus) {
        String url = subscriptionServiceBaseUrl + "/" + subscriptionId + "/status";
        restTemplate.patchForObject(url, newStatus, String.class);
    }

    private Long obtainSubscriptionIdFromPayment(Payment payment) {
        return Long.valueOf(payment.getExternalReference());
    }

    public boolean validatePaymentMethod(String paymentMethodId) {
        return supportedPaymentMethods.contains(paymentMethodId.toLowerCase());
    }

}