package com.myproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.myproject.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@HystrixCommand(defaultFallback="myError")
	public String testGet(String name) {
		return restTemplate.getForObject("http://login/test?name="+name, String.class);
	}

	@Override
	@HystrixCommand(defaultFallback="myError")
	public String testPost(String name) {
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("name", name);
		return restTemplate.postForObject("http://login/test", postParameters, String.class);
	}
	
	public String myError() {
		return "hi,sorry,error!";
	}

}
