package com.myproject;

import com.myproject.utils.PasswordUtil;
import com.myproject.utils.ZipUtil;

public class TestMain {
	public static void main(String[] args) {
		PasswordUtil.initCommonPasswordSet();
		String password = "0000";
		while (true) {
			System.out.println("当前密码是：" + password);
			if (ZipUtil.extractAllFiles("F:\\文档.zip", password)) {
				break;
			}
			password = PasswordUtil.calcNextPassword(password);
		}
	}
}
