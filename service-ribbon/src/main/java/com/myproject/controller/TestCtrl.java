package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestCtrl {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/test2")
	public String test2Service() {
		return restTemplate.getForObject("http://FACE-RECOGNITION/test/test", String.class);
	}

}
