package com.myproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value = "/test")
	public String test() {
		return "test";
	}

	@RequestMapping(value = "/test2")
	public String test2() {
		return "test2";
	}

}
