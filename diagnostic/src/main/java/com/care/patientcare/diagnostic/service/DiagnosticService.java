package com.care.patientcare.diagnostic.service;

import java.util.ArrayList;
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
	public List<DiagnosticDto> getDiagnosticReport(String orderId) {
		List<DiagnosticDto> diagnosticReports = new ArrayList();
		if (orderId != null) {
			Diagnostic diagnostic = get(orderId);
			if (diagnostic!=null)
				diagnosticReports.add(convert(diagnostic, DiagnosticDto.class));
			else
				diagnosticReports.add(new DiagnosticDto());
		} else {
			List<Diagnostic> diagnosticList = diagnosticRepository.findAll();
			diagnosticReports = populateDiagnosticDto(diagnosticList);
		}
		return diagnosticReports;
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
	 * Return all diagnostic reports for a particular User
	 * @return
	 */
	public List<DiagnosticDto> getDiagnosticReports(String email) {
		List<Diagnostic> diagnosticReports = diagnosticRepository.findByEmail(email);
		return populateDiagnosticDto(diagnosticReports);
	}
	
	
	/**
     * Convert domain to DTO
     * @param List of diagnosticReports
     * @return a list of diagnosticReport Dto(s)
     */
    private List populateDiagnosticDto(List diagnosticReports) {
    	diagnosticReports.forEach(d -> convert(d, DiagnosticDto.class));
    	return diagnosticReports;
    } 
}
