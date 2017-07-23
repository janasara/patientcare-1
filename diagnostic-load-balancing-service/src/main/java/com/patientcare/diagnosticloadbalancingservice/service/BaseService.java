package com.patientcare.diagnosticloadbalancingservice.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;class BaseService {

    @Autowired
    ObjectMapper objectMapper;
	
	@SuppressWarnings("unchecked")
    protected <T> T convert(Object fromValue, Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }
	
	@SuppressWarnings("unchecked")
    protected <T> T convert(String fromValue, Class<T> toValueType) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(fromValue, toValueType);
    }
}
