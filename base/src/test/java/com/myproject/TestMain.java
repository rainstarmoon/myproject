package com.myproject;

import java.io.File;

import com.myproject.utils.BaseUtil;
import com.myproject.utils.PasswordUtil;
import com.myproject.utils.ZipUtil;

public class TestMain {
	public static void main(String[] args) {
		File file = new File("F:\\BaiduNetdiskDownload\\test");
		File[] listFiles = file.listFiles();
		for (File tmp : listFiles) {
			if (ZipUtil.extractAllFiles(tmp,
					BaseUtil.getFilePath(tmp.getPath()) + "\\" + tmp.getName().substring(0, tmp.getName().length() - 4),
					"www.fuli10.net")) {
			} else {
				System.out.println(tmp.getName());
			}

		}

		/*
		 * PasswordUtil.initCommonPasswordSet(); String pass = "0"; int length = 1; long
		 * begin = System.currentTimeMillis(); while (true) { if
		 * (ZipUtil.extractAllFiles("F:\\文档.zip", pass)) { System.out.println("密码测试了" +
		 * PasswordUtil.PASSWORD_TEST_TIMES + "次"); break; } pass =
		 * PasswordUtil.calcNextPassword(pass); if (pass.length() > length) { length =
		 * pass.length(); System.out.println(length); } } long end =
		 * System.currentTimeMillis(); System.out.println("共花费：" + (end - begin) / 1000
		 * + "秒");
		 */
	}
}
