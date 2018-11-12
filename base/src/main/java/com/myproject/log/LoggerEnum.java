package com.myproject.log;

public enum LoggerEnum {
	/**
	 * 
	 */
	TRACE("trace"),
	/**
	 * 
	 */
	DEBUG("debug"),
	/**
	 * 
	 */
	INFO("info"),
	/**
	 * 
	 */
	WARN("warn"),
	/**
	 * 
	 */
	ERROR("error");

	private String value;

	private LoggerEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}