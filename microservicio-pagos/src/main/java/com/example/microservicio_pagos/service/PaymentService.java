package com.example.microservicio_pagos.service;

import com.example.microservicio_pagos.dto.PreferenceRequestDTO;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.client.preference.PreferenceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PreferenceClient preferenceClient;

    @Autowired
    public PaymentService(PreferenceClient preferenceClient) {
        this.preferenceClient = preferenceClient;
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
}
