package com.example.microservicio_pagos.controller;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.example.microservicio_pagos.dto.PaymentRequest;
import com.example.microservicio_pagos.dto.PaymentResponse;
import com.example.microservicio_pagos.service.MercadoPagoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final MercadoPagoService mercadoPagoService;

    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    public PaymentController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
        logger.info("Getting payment with ID: {}", id);
        PaymentResponse payment = mercadoPagoService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        try {
            logger.info("Creating payment for amount: {}", paymentRequest.getAmount());
            PaymentResponse paymentResponse = mercadoPagoService.createPayment(paymentRequest);
            return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
        } catch (MPApiException e) {
            logger.error("API Error: {}", e.getMessage());
            return new ResponseEntity<>("Error with MercadoPago API: " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        } catch (MPException e) {
            logger.error("MP Error: {}", e.getMessage());
            return new ResponseEntity<>("MercadoPago error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Unexpected Error: {}", e.getMessage());
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse updatedPayment = mercadoPagoService.updatePayment(id, paymentRequest);
        return ResponseEntity.ok(updatedPayment);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelPayment(@PathVariable Long id) {
        try {
            mercadoPagoService.cancelPayment(id);
            return ResponseEntity.noContent().build();
        } catch (MPApiException e) {
            return new ResponseEntity<>("Error with MercadoPago API: " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        } catch (MPException e) {
            return new ResponseEntity<>("MercadoPago error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
