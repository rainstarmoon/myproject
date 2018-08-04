package com.myproject.config;

import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Value("${library.opencv.haarcascade_frontalface_alt}")
	private String LIBRARY_OPENCV_HAARCASCADE_FRONTALFACE_ALT;

	@Bean(name = "haarcascadeFrontalfaceAlt")
	public CascadeClassifier createHaarcascadeFrontalfaceAlt() {
		CascadeClassifier haarcascadeFrontalfaceAlt = new CascadeClassifier(LIBRARY_OPENCV_HAARCASCADE_FRONTALFACE_ALT);
		return haarcascadeFrontalfaceAlt;
	}

}
