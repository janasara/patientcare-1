package com.care.patientcare.diagnostic.domain;

public class DiagnosticTest {

	private String label;
	
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public String toString(){
		String info = String.format("{'label': %s 'value': %s}", label, value);
		return info;
	}
}
