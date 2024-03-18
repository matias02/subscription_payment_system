package com.example.microservicio_pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class MicroservicioPagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPagosApplication.class, args);
	}

}
