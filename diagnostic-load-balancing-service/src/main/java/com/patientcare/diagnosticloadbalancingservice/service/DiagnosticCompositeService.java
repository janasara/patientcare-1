package com.patientcare.diagnosticloadbalancingservice.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.patientcare.diagnosticloadbalancingservice.dto.DiagnosticDto;
import com.patientcare.diagnosticloadbalancingservice.dto.StatusDto;

/**
 * @author venkatg
 * Diagnostic report service
 */
@Service
public class DiagnosticCompositeService extends BaseService {

	@Inject
	private RestTemplate restTemplate;
	
	/**
	 * To get diagnostic report for a particular order from other Micro service. 
	 * This micro service talks to different other micro services to consolidate the result.
	 * Hystrix can be defined only in @service or @component
	 * @param orderId
	 * @return Diagnostic Report
	 */
	@HystrixCommand(fallbackMethod = "fallBack") 
	public DiagnosticDto getDiagnosticReport(String orderId) {
		String diagnosticResponse = restTemplate.getForEntity("http://diagnosticreports/patientcare/v1/diagnosticreports?orderId="+orderId, String.class).getBody();
		
		DiagnosticDto diagnosticDto = null;
		try {
			diagnosticDto =  convert(diagnosticResponse, DiagnosticDto.class);
		} catch (IOException e) {
			// TODO Handle later
			e.printStackTrace();
		}
		return diagnosticDto;
	}	

	/**
	 * Fallback method when the actual Micro service fails. Can call other micro service or can have default value.
	 * This method signature should be similar to actual method for which fallback is defined.
	 * @param orderId
	 * @return Diagnostic Report
	 */
	public DiagnosticDto fallBack(String orderId) {
		DiagnosticDto diagnosticDto = new DiagnosticDto();
		return diagnosticDto;
	}
	
	/**
	 * To get status from other Micro service
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public StatusDto getStatus() throws JsonParseException, JsonMappingException, IOException {
		String statusResponse = restTemplate.getForEntity("http://diagnosticreports/patientcare/v1/diagnosticreports/testservice", String.class).getBody();
		return convert(statusResponse, StatusDto.class);
	}
	
}
