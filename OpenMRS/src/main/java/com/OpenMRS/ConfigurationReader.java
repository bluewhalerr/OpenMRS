package com.OpenMRS;

public class ConfigurationReader {
	
	private ConfigurationReader() {
		
	}
	
	public static ConfigurationHelper getInstance() throws Exception {
		ConfigurationHelper helper = new ConfigurationHelper();
		return helper;
	}
}
