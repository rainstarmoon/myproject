package com.myproject.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class OpencvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private ConfigurableEnvironment environment;

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		environment = applicationContext.getEnvironment();
		System.load(environment.getProperty("library.opencv.path"));
	}

}
