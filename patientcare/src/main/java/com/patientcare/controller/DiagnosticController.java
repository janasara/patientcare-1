package com.patientcare.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patientcare.dto.DiagnosticDto;
import com.patientcare.service.DiagnosticService;

/**
 * @author venkatg
 * Diagnostic Center related service
 */
@RestController
@RequestMapping("/patientcare/v1/diagnosticcenters")
public class DiagnosticController {
	@Autowired
	DiagnosticService ds;
	
	/**
	 * To get the list of diagnostic centers and if category is passed
	 * top n configured diagnostic centers is returned
	 * @param category
	 * @return
	 */
	@RequestMapping(method = GET)
	public ResponseEntity<?> getDiagnosticCentres(@RequestParam(value = "category", required=false) String category) {
		List<DiagnosticDto> diagnosticCentres = ds.getDiagnosticCentres(category);
		return new ResponseEntity<>(diagnosticCentres, HttpStatus.OK);
	}

	/**
	 * Test service
	 * @return
	 */
	@RequestMapping("/testservice")
	public ResponseEntity<?> testService() {
		JSONObject testData = new JSONObject();
		testData.put("Status", true);
		return new ResponseEntity<>(testData, HttpStatus.OK);
	}
}
