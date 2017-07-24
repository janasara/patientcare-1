package com.patientcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author venkatg
 * Diagnostic Center related service
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class PatientcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientcareApplication.class, args);
	}
}
