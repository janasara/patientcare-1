package com.patientcare.pcgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author venkatg
 * Zuul Proxy 
 */
@SpringBootApplication
@EnableZuulProxy 
@EnableDiscoveryClient 
public class PcGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcGatewayServiceApplication.class, args);
	}
}
