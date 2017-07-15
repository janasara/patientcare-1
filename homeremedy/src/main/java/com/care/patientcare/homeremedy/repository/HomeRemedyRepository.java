package com.care.patientcare.homeremedy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.care.patientcare.homeremedy.domain.HomeRemedy;

public interface HomeRemedyRepository extends MongoRepository<HomeRemedy, String> {
	
	List<HomeRemedy> findAll();
	
	HomeRemedy findById(String id);
	
}
