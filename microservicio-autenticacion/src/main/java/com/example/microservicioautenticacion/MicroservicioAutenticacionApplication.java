package com.example.microservicioautenticacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicioAutenticacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAutenticacionApplication.class, args);
	}
}
