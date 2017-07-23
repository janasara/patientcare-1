package com.patientcare.diagnosticloadbalancingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author venkatg
 * Diagnostic report Service
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class DiagnosticLoadBalancingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosticLoadBalancingServiceApplication.class, args);
	}
}
