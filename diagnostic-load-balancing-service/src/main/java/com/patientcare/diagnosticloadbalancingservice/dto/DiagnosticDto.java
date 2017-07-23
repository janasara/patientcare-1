package com.patientcare.diagnosticloadbalancingservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiagnosticDto {
	private String id;

	private String name;
	
	private int age;
	
	private String gender;
	
	private String email;
	
	private String mobile;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	private String dcenter;
	
	private List<DiagnosticTestDto> tests;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getDcenter() {
		return dcenter;
	}

	public void setDcenter(String dcenter) {
		this.dcenter = dcenter;
	}

	public List<DiagnosticTestDto> getTests() {
		return tests;
	}

	public void setTests(List<DiagnosticTestDto> diagnosticTests) {
		this.tests = diagnosticTests;
	}
	
}
