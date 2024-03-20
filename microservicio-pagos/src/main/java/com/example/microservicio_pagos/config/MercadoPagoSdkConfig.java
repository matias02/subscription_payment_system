package com.example.microservicio_pagos.config;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MercadoPagoSdkConfig {

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoSdkConfig.class);

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
        logger.info("Configurando MercadoPago SDK...");
        MercadoPagoConfig.setAccessToken(accessToken.trim());
        MercadoPagoConfig.setMaxConnections(maxConnections);
        MercadoPagoConfig.setConnectionTimeout(connectionTimeout);
        MercadoPagoConfig.setConnectionRequestTimeout(connectionRequestTimeout);
        MercadoPagoConfig.setSocketTimeout(socketTimeout);
        logger.info("MercadoPago SDK configurado con éxito. Access Token: {}", accessToken.trim());
    }

    @Bean
    public PaymentClient paymentClient() {
        logger.info("Creando bean PaymentClient de MercadoPago");
        return new PaymentClient();
    }

    @Bean
    public PreferenceClient preferenceClient() {
        logger.info("Creando bean PreferenceClient de MercadoPago");
        return new PreferenceClient();
    }
}

