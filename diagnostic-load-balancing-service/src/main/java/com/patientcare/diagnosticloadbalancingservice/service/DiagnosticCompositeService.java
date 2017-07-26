package com.patientcare.diagnosticloadbalancingservice.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
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
	public List<DiagnosticDto> getDiagnosticReport(String orderId) {
		// String diagnosticResponse = restTemplate.getForEntity("http://diagnosticreports/patientcare/v1/diagnosticreports, String.class).getBody();
		String restUrl = "http://diagnosticreports/patientcare/v1/diagnosticreports";
		if (orderId!=null)
			restUrl = restUrl + "?orderId="+orderId;
		String diagnosticResponse = restTemplate.getForEntity(restUrl, String.class).getBody();
		
		List<DiagnosticDto> diagnosticReports = null;
		try {
			DiagnosticDto[] adiagnosticReports = convert(diagnosticResponse, DiagnosticDto[].class);
			diagnosticReports = Arrays.asList(adiagnosticReports);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diagnosticReports;
	}	

	/**
	 * Fallback method when the actual Micro service fails. Can call other micro service or can have default value.
	 * This method signature should be similar to actual method for which fallback is defined.
	 * @param orderId
	 * @return Diagnostic Report
	 */
	public List<DiagnosticDto> fallBack(String orderId) {
		return null;
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
