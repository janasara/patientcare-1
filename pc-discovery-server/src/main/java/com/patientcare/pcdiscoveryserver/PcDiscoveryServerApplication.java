package com.patientcare.pcdiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author venkatg
 * Eureka Discover Server
 */

@EnableEurekaServer
@SpringBootApplication
public class PcDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcDiscoveryServerApplication.class, args);
	}
}
