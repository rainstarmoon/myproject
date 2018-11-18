package com.myproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

	@RequestMapping("/test")
	public Object test(HttpServletRequest request,String name, Integer age) {
		
		
		
		return name + ":"+request.getMethod()+":" + age;
	}

}
