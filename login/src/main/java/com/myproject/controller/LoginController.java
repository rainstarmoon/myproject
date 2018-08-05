package com.myproject.controller;

import java.io.File;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myproject.config.FaceImageUsernamePasswordToken;

@Controller
@RequestMapping
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String loginSuccess() {
		// Subject subject = SecurityUtils.getSubject();
		return "loginSuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin() {
		Subject subject = SecurityUtils.getSubject();
		FaceImageUsernamePasswordToken token = new FaceImageUsernamePasswordToken(new File("E:\\workspace_myself\\git\\myproject\\info\\20160407_233616.jpg"));
		// 登录
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 验证是否登录成功
		if (subject.isAuthenticated()) {
			return "redirect:/loginSuccess";
		} else {
			token.clear();
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}

}
