package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableTransactionManagement
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class LoginApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}
	
}
