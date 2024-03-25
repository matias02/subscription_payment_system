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
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al crear la preferencia de pago: " + e.getMessage());
        }
    }

    @PostMapping("/notifications")
    public ResponseEntity<?> handlePaymentNotification(@RequestBody String notification, @RequestParam("type") String type, @RequestParam("data_id") String dataId) {
        try {
            System.out.println("Notificación recibida: " + notification + ", Tipo: " + type + ", Data ID: " + dataId);
            return ResponseEntity.ok().body("Notificación recibida");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al procesar la notificación: " + e.getMessage());
        }
    }

    @GetMapping("/validate/{paymentMethodId}")
    public ResponseEntity<?> validatePaymentMethod(@PathVariable String paymentMethodId) {
        try {
            boolean isValid = paymentService.validatePaymentMethod(paymentMethodId);

            if (isValid) {
                return ResponseEntity.ok().body("Método de pago válido");
            } else {
                return ResponseEntity.badRequest().body("Método de pago inválido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al validar el método de pago: " + e.getMessage());
        }
    }

}
