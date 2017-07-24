package com.care.patientcare.diagnostic.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.patientcare.diagnostic.dto.DiagnosticDto;
import com.care.patientcare.diagnostic.dto.StatusDto;
import com.care.patientcare.diagnostic.service.DiagnosticService;

/**
 * @author venkatg
 * Diagnostic Report Service 
 */
@RestController
@EnableDiscoveryClient 
@RequestMapping("/patientcare/v1/diagnosticreports")
public class DiagnosticController {

	@Value("${server.port}")
	private int port;
	

	@Autowired
	DiagnosticService ds;
	
	/**
	 * To get Diagnostic report
	 * @return
	 */
	@RequestMapping(method = GET)
	public DiagnosticDto getDiagnosticReport(@RequestParam String orderId) {
		DiagnosticDto diagnosticReports= ds.getDiagnosticReport(orderId);
		return diagnosticReports;
	}
	
	/**
	 * To get all Diagnostic report based on User
	 * @return
	 */
	@RequestMapping(value = "/{email:.+}", method = GET)
	public List<DiagnosticDto> getDiagnosticReports(@PathVariable("email") String email) {
		List<DiagnosticDto> diagnosticReports= ds.getDiagnosticReports(email);
		return diagnosticReports;
	}
	
	/**
	 * To insert Diagnostic report for a particular order
	 * @param diagnosticDto
	 * @return
	 */
	@RequestMapping(method = POST)
	public ResponseEntity<?> create(@RequestBody @Valid DiagnosticDto diagnosticDto) {
        return new ResponseEntity<>(ds.create(diagnosticDto), CREATED);
    }

	/**
	 * To delete Diagnostic report for a particular order
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return new ResponseEntity<>(ds.delete(id), OK);
    }
	
	/**
	 * To update Diagnostic report for a particular order
	 * @param id
	 * @param diagnosticDto
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = PUT)
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid DiagnosticDto diagnosticDto) {
        return new ResponseEntity<>(ds.update(id, diagnosticDto), OK);
    }
	
	
	/**
	 * Test Service without DB connection
	 * @return
	 */
	@RequestMapping("/testservice")
	public ResponseEntity<?> testService() {
		StatusDto statusDto = new StatusDto();
		statusDto.setStatus(true);
		statusDto.setTime(new Date().toString());
		statusDto.setPort(String.valueOf(port));
		return new ResponseEntity<>(statusDto, OK);
	}
}
