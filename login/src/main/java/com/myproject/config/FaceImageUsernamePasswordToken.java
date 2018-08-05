package com.myproject.config;

import java.io.File;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 其他方法都研究研究怎么重写
 * 
 * @author XiaZeyu
 *
 */
public class FaceImageUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -9030041640419050325L;

	private File faceImage;

	@Override
	public Object getPrincipal() {
		return getUsername();
	}

	@Override
	public Object getCredentials() {
		if (faceImage == null) {
			return getPassword();
		}
		return faceImage;
	}

	public FaceImageUsernamePasswordToken() {
	}

	public FaceImageUsernamePasswordToken(final File faceImage) {
		this.faceImage = faceImage;
		this.setUsername("admin");
	}

	public FaceImageUsernamePasswordToken(final String username, final String password) {
		super(username, password);
	}

	public File getFaceImage() {
		return faceImage;
	}

	public void setFaceImage(File faceImage) {
		this.faceImage = faceImage;
	}

}
