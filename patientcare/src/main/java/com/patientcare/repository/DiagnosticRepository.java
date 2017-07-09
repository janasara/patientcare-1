package com.patientcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patientcare.domain.Diagnostic;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
	
	List<Diagnostic> findAll();
	
	Diagnostic findById(int id);

}
