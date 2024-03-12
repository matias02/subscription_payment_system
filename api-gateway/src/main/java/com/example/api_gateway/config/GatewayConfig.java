package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/autenticacion/**")
                        .filters(f -> f.rewritePath("/autenticacion/(?<path>.*)", "/${path}"))
                        .uri("lb://autenticacion-service"))
                .route(r -> r.path("/usuarios/**")
                        .filters(f -> f.rewritePath("/usuarios/(?<path>.*)", "/${path}"))
                        .uri("lb://microservicio-usuarios"))
                .route(r -> r.path("/pagos/**")
                        .filters(f -> f.rewritePath("/pagos/(?<path>.*)", "/${path}"))
                        .uri("lb://microservicio-pagos"))
                .build();
    }
}
