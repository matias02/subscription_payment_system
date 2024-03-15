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
                .route(r -> r.path("/api/users/**")
                        .filters(f -> f.rewritePath("/api/users/(?<path>.*)", "/${path}"))
                        .uri("lb://usuario-service"))
                .route(r -> r.path("/pagos/**")
                        .filters(f -> f.rewritePath("/pagos/(?<path>.*)", "/${path}"))
                        .uri("lb://microservicio-pagos"))
                .build();
    }
}
