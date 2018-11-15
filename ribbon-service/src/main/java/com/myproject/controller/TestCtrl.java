package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestCtrl {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/test/post")
	public String test1Service(String name) {
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("name", name);
		return restTemplate.postForObject("http://login/test", postParameters, String.class);
	}

	@RequestMapping(value = "/test/get")
	public String test2Service(String name) {
		return restTemplate.getForObject("http://login/test?name="+name, String.class);
	}

}
