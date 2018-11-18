package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.service.TestService;

@RestController
public class TestCtrl {

	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/test/get")
	public String test2Service(String name) {
		return testService.testGet(name);
	}
	
	@RequestMapping(value = "/test/post")
	public String test1Service(String name) {
		return testService.testPost(name);
	}

}
