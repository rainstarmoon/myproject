package com.myproject.utils;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipUtil {

	public static boolean extractAllFiles(String zipFilePath, String password) {
		return extractAllFiles(zipFilePath, BaseUtil.getFilePath(zipFilePath), password);
	}

	public static boolean extractAllFiles(String sourcePath, String targetPath, String password) {
		try {
			ZipFile zipFile = new ZipFile(sourcePath);
			zipFile.setFileNameCharset("GBK");
			if (password != null) {
				if (zipFile.isEncrypted()) {
					zipFile.setPassword(password);
				}
			}
			zipFile.extractAll(targetPath);
			return true;
		} catch (ZipException e) {
			return false;
		}
	}
	
	public static boolean extractAllFiles(File zipFile, String password) {
		return extractAllFiles(zipFile, BaseUtil.getFilePath(zipFile.getPath()), password);
	}
	
	public static boolean extractAllFiles(File sourceFile, String targetPath, String password) {
		try {
			ZipFile zipFile = new ZipFile(sourceFile);
			zipFile.setFileNameCharset("GBK");
			if (password != null) {
				if (zipFile.isEncrypted()) {
					zipFile.setPassword(password);
				}
			}
			zipFile.extractAll(targetPath);
			return true;
		} catch (ZipException e) {
			return false;
		}
	}
	
	

}
