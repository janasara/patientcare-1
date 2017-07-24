package com.care.patientcare.diagnostic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author venkatg
 * Diagnostic Report Service 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DiagnosticApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosticApplication.class, args);
	}
}
