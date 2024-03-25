package com.example.api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    private static final Logger logger = LoggerFactory.getLogger(GatewayConfig.class);

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/autenticacion/**")
                        .filters(f -> f.rewritePath("/autenticacion/(?<path>.*)", "/${path}")
                                .filter((exchange, chain) -> {
                                    logger.info("Request to autenticacion-service: {}", exchange.getRequest().getPath());
                                    return chain.filter(exchange).then(Mono.fromRunnable(() ->
                                            logger.info("Response status code: {}", exchange.getResponse().getStatusCode())));
                                }))
                        .uri("lb://autenticacion-service"))
                .route(r -> r.path("/api/users/**")
                        .filters(f -> f.rewritePath("/api/users/(?<path>.*)", "/${path}")
                                .filter((exchange, chain) -> {
                                    logger.info("Request to usuario-service: {}", exchange.getRequest().getPath());
                                    return chain.filter(exchange).then(Mono.fromRunnable(() ->
                                            logger.info("Response status code: {}", exchange.getResponse().getStatusCode())));
                                }))
                        .uri("lb://usuario-service"))
                .route(r -> r.path("/api/payments/**")
                        .filters(f -> f.filter((exchange, chain) -> {
                            logger.info("Request to microservicio-pagos: {}", exchange.getRequest().getPath());
                            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                                    logger.info("Response status code: {}", exchange.getResponse().getStatusCode())));
                        }))
                        .uri("lb://microservicio-pagos"))
                .route(r -> r.path("/api/subscriptions/**")
                        .filters(f -> f.rewritePath("/api/subscriptions/(?<path>.*)", "/${path}")
                                .filter((exchange, chain) -> {
                                    logger.info("Request to microservicios-sub: {}", exchange.getRequest().getPath());
                                    return chain.filter(exchange).then(Mono.fromRunnable(() ->
                                            logger.info("Response status code: {}", exchange.getResponse().getStatusCode())));
                                }))
                        .uri("lb://microservicios-sub"))
                .build();
    }
}
