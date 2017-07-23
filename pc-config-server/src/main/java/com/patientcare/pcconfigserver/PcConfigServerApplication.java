package com.patientcare.pcconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author venkatg
 * Config Server
 */
@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
public class PcConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcConfigServerApplication.class, args);
	}
}
 