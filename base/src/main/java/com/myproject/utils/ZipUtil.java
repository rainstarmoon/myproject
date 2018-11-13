package com.myproject.utils;

import java.io.File;

import com.myproject.exception.ZipOperateException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

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
			throw new ZipOperateException(e);
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
			throw new ZipOperateException(e);
		}
	}
	
	public static boolean compressFiles(String sourcePath, File targetFile) {
        try {
            ZipFile zipFile = new ZipFile(targetFile);
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            zipFile.addFolder(sourcePath, parameters);
            return true;
        } catch (ZipException e) {
            throw new ZipOperateException(e);
        }
    }

    public static boolean compressFiles(File sourceFile, File targetFile) {
        try {
            ZipFile zipFile = new ZipFile(targetFile);
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            zipFile.addFile(sourceFile, parameters);
            return true;
        } catch (ZipException e) {
            throw new ZipOperateException(e);
        }
    }

}
