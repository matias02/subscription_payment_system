package com.example.microservicios_sub.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;

@Service
public class PaymentIntegrationService {

    @Value("${paymentService.url}")
    private String paymentServiceUrl;

    private final RestTemplate restTemplate;

    public PaymentIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validatePaymentMethod(String paymentMethodId) {
        String url = paymentServiceUrl + "/validate/" + paymentMethodId;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Response body: " + response.getBody());
                return response.getBody().contains("válido");
            } else {
                System.out.println("Non-successful response code: " + response.getStatusCode());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
