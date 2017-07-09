package com.patientcare.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;class BaseService {

    @Autowired
    ObjectMapper objectMapper;
	
	@SuppressWarnings("unchecked")
    protected <T> T convert(Object fromValue, Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }
}
