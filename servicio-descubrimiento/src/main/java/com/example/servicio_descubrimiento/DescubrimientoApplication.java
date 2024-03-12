package com.example.servicio_descubrimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DescubrimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DescubrimientoApplication.class, args);
	}

}