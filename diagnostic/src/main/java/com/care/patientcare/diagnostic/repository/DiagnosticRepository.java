package com.care.patientcare.diagnostic.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.care.patientcare.diagnostic.domain.Diagnostic;

public interface DiagnosticRepository extends MongoRepository<Diagnostic, String> {
	List<Diagnostic> findByName(String name);
	
	Diagnostic findById(String id);
	
	List<Diagnostic> findByEmail(String email);
	
}
