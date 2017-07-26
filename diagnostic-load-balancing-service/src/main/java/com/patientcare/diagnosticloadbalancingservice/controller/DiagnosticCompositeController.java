package com.patientcare.diagnosticloadbalancingservice.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.patientcare.diagnosticloadbalancingservice.dto.DiagnosticDto;
import com.patientcare.diagnosticloadbalancingservice.dto.StatusDto;
import com.patientcare.diagnosticloadbalancingservice.service.DiagnosticCompositeService;

/**
 * @author venkatg
 * Diagnostic Report Service 
 */
@RestController
@RequestMapping("/patientcare/v1/diagnosticreports")
public class DiagnosticCompositeController {

	@Autowired
	DiagnosticCompositeService ds;
	
	/**
	 * To get Diagnostic report
	 * @return
	 */
	@RequestMapping(method = GET)
	public ResponseEntity<?> getDiagnosticReport(@RequestParam(value = "orderId", required=false) String orderId) throws JsonParseException, JsonMappingException, IOException {
		List<DiagnosticDto> diagnosticReports= ds.getDiagnosticReport(orderId);
		if (diagnosticReports != null)
			return new ResponseEntity<>(diagnosticReports, OK);
		else 
			return new ResponseEntity<>("Service Not Available", NOT_FOUND);
	}
	
	
	/**
	 * Client side load balancing. Returns RestTemplate with RestTemplate interceptor 
	 * and this intercepter will communicate with Ribbon client for doing the load balancing 
	 * in the client side.
	 * @return RestTemplate
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate(getClientHttpRequestFactory());
	}

	
    /** Define timeout, without this, will result in Hystrix fallback method */
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 5000;
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    clientHttpRequestFactory.setReadTimeout(timeout);
	    return clientHttpRequestFactory;
	}
	
	/**
	 * Test Service without DB connection
	 * @return Service status
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/testservice")
	public ResponseEntity<?> testService() throws JsonParseException, JsonMappingException, IOException {
		StatusDto statusDto = ds.getStatus();
		return new ResponseEntity<>(statusDto, OK);
	}
}
