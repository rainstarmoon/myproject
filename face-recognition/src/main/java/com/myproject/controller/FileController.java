package com.myproject.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

	@RequestMapping("/upload/image")
	public Object uploadImage(HttpServletRequest request,MultipartFile[] files) {
		for(MultipartFile tmp:files) {
			OutputStream os = null;
			try {
				os = new FileOutputStream("E:/a.png");
				IOUtils.copy(tmp.getInputStream(),os);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(os!=null) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "hello world";
	}

}
