package com.myproject.service.face.impl;

import org.springframework.stereotype.Component;

import com.myproject.service.face.FaceService;

@Component
public class FaceServiceHystric implements FaceService {

	@Override
	public String testGet2(String name, Integer age) {
		return "Sorry testGet2";
	}

	@Override
	public String testPost2(String name, Integer age) {
		return "Sorry testPost2";
	}

}
