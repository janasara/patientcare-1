package com.patientcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientcare.dto.DiagnosticDto;
import com.patientcare.domain.Diagnostic;
import com.patientcare.repository.DiagnosticRepository;

/**
 * @author venkatg
 * Diagnostic service
 */
@Service
public class DiagnosticService extends BaseService {

	@Autowired
	DiagnosticRepository diagnosticRepository;
	
	/**
	 * Service to return the list of diagnostic centers based on the category
	 * @param category
	 * @return
	 */
	public List<DiagnosticDto> getDiagnosticCentres(String category) {
		List<Diagnostic> diagnosticCentres;
		diagnosticCentres = diagnosticRepository.findAll();
		if ("TOP".equalsIgnoreCase(category))
		{
			long noOfTopCentres = 1; // TODO from Config server
			diagnosticCentres = diagnosticCentres.stream().limit(noOfTopCentres).collect(Collectors.toList());
		}
		
		// Convert domain to dto and return
		return populateDiagnosticDto(diagnosticCentres);
	}	
	
	
	/**
     * Convert Diagnostic domain to diagnosticDto 
     * @param List of diagnosticCentres
     * @return a list of DiagnosticCentres DTO
     */
    private List populateDiagnosticDto(List diagnosticCentres) {
    	diagnosticCentres.forEach(d -> convert(d, DiagnosticDto.class));
    	return diagnosticCentres;
    } 
}
