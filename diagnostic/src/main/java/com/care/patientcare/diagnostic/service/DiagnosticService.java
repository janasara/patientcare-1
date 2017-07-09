package com.care.patientcare.diagnostic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.patientcare.diagnostic.domain.Diagnostic;
import com.care.patientcare.diagnostic.dto.DiagnosticDto;
import com.care.patientcare.diagnostic.repository.DiagnosticRepository;

/**
 * @author venkatg
 * Diagnostic report service
 */
@Service
public class DiagnosticService extends BaseService {

	@Autowired
	DiagnosticRepository diagnosticRepository;
	
	/**
	 * To get diagnostic report for a particular order
	 * @param orderId
	 * @return
	 */
	public DiagnosticDto getDiagnosticReport(String orderId) {
		Diagnostic diagnostic = get(orderId);
		
		// Convert domain to dto and return
		return convert(diagnostic, DiagnosticDto.class);
	}	
	
	/**
	 * Service to insert Diagnostic report for a particular order
	 * @param diagnosticDto
	 * @return
	 */
	public DiagnosticDto create(DiagnosticDto diagnosticDto) {
		Diagnostic diagnostic = convert(diagnosticDto, Diagnostic.class);
		diagnostic = diagnosticRepository.save (diagnostic);
		return convert(diagnostic, DiagnosticDto.class);
	}
	
	/**
	 * Service to update Diagnostic report for a particular order
	 * @param id
	 * @param diagnosticDto
	 * @return
	 */
	public DiagnosticDto update(String id, DiagnosticDto diagnosticDto) {
		Diagnostic diagnostic = get(id);
		diagnostic = convert(diagnosticDto, Diagnostic.class);
		diagnostic = diagnosticRepository.save (diagnostic);
		return convert(diagnostic, DiagnosticDto.class);
	}

	
	/**
	 * Service to delete Diagnostic report for a particular order
	 * @param id
	 * @return
	 */
	public DiagnosticDto delete(String id) {
		Diagnostic deletedDiagnostic = get(id);
		diagnosticRepository.delete(deletedDiagnostic);
		return convert(deletedDiagnostic, DiagnosticDto.class);
	}
	
	/**
	 * Get data based on Id
	 * @param id
	 * @return
	 */
	public Diagnostic get(String id) {
		return diagnosticRepository.findById(id);
	}
	
	/**
     * Convert domain to DTO
     * @param List of diagnosticCentres
     * @return a list of diagnosticCentre Dto(s)
     */
    private List populateDiagnosticDto(List diagnosticCentres) {
    	diagnosticCentres.forEach(d -> convert(d, DiagnosticDto.class));
    	return diagnosticCentres;
    } 
}
