package com.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@Configuration
public class FeignConfig {

	@Bean
	public Contract feignConfiguration() {
		return new feign.Contract.Default();
	}
	
}