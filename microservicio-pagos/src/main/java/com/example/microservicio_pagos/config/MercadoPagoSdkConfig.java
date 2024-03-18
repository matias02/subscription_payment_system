package com.example.microservicio_pagos.config;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MercadoPagoSdkConfig {

    @Value("${mercadopago.access_token}")
    private String accessToken;

    @Value("${mercadopago.max_connections:10}")
    private int maxConnections;

    @Value("${mercadopago.connection_timeout:20000}")
    private int connectionTimeout;

    @Value("${mercadopago.connection_request_timeout:20000}")
    private int connectionRequestTimeout;

    @Value("${mercadopago.socket_timeout:20000}")
    private int socketTimeout;

    @Bean
    public void configureMercadoPago() {
        MercadoPagoConfig.setAccessToken(accessToken);
        MercadoPagoConfig.setMaxConnections(maxConnections);
        MercadoPagoConfig.setConnectionTimeout(connectionTimeout);
        MercadoPagoConfig.setConnectionRequestTimeout(connectionRequestTimeout);
        MercadoPagoConfig.setSocketTimeout(socketTimeout);
    }

    @Bean
    public PaymentClient paymentClient() {
        return new PaymentClient();
    }
}

