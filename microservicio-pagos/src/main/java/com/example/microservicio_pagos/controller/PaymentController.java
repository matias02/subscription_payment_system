package com.example.microservicio_pagos.controller;

import com.example.microservicio_pagos.dto.PreferenceRequestDTO;
import com.example.microservicio_pagos.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-preference")
    public ResponseEntity<?> createPaymentPreference(@RequestBody PreferenceRequestDTO preferenceRequestDTO) {
        try {
            String paymentPreferenceUrl = paymentService.createPaymentPreference(preferenceRequestDTO);
            return ResponseEntity.ok().body(paymentPreferenceUrl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la preferencia de pago: " + e.getMessage());
        }
    }
}
