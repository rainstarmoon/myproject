package com.myproject.controller.face;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.service.face.FaceService;

@RestController
public class FaceController {

	@Autowired
	private FaceService faceService;

/*	@GetMapping(value = "/testGet1")
	public String testGet1(String name, Integer age) {
		return faceService.testGet(name, age);
	}

	@GetMapping(value = "/testPost1")
	public String testPost1(String name, Integer age) {
		return faceService.testPost(name, age);
	}*/

	@GetMapping(value = "/testGet2")
	public String testGet2(String name, Integer age) {
		return faceService.testGet2(name, age);
	}

	@GetMapping(value = "/testPost2")
	public String testPost2(String name, Integer age) {
		return faceService.testPost2(name, age);
	}

}
