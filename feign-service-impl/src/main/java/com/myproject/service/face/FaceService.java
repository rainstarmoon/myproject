package com.myproject.service.face;

import org.springframework.cloud.openfeign.FeignClient;

import com.myproject.service.face.impl.FaceServiceHystric;

import feign.Body;
import feign.Param;
import feign.RequestLine;

@FeignClient(value = "face-recognition",fallback=FaceServiceHystric.class)
public interface FaceService {

//	@RequestMapping(value = "/test/test", method = RequestMethod.GET)
//	String testGet(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age);
//	
//	@RequestMapping(value = "/test/test", method = RequestMethod.POST)
//	String testPost(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age);
	
	@RequestLine("GET /test/test?name={name}&age={age}")
	String testGet2(@Param("name") String name, @Param("age") Integer age);
	
	@RequestLine("POST /test/test")
    @Body("name={name}&age={age}")
	String testPost2(@Param("name") String name, @Param("age") Integer age);
	
}
