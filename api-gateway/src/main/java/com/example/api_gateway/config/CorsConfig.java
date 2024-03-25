package com.example.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*"); // Ajusta esto según tus necesidades
        corsConfig.addAllowedMethod("*"); // O específica los métodos HTTP permitidos
        corsConfig.addAllowedHeader("*"); // O especifica las cabeceras permitidas
        corsConfig.setAllowCredentials(true); // Según sea necesario
        corsConfig.setMaxAge(3600L); // Ajusta según tus necesidades

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", corsConfig); // Aplica la configuración CORS a todas las rutas

        return new CorsWebFilter(source);
    }
}
