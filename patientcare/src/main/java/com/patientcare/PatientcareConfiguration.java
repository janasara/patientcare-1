package com.patientcare;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author venkatg
 * Config client to read configuration from Config Server
 */
@Component
@ConfigurationProperties(prefix="dcenters")
public class PatientcareConfiguration {
	private int topCentresCount;

	public int getTopCentresCount() {
		return topCentresCount;
	}

	public void setTopCentresCount(int topCentresCount) {
		this.topCentresCount = topCentresCount;
	}
}
