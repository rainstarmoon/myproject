package com.myproject.utils;

public class BaseUtil {

	public static String getFilePath(String route) {
		int indexOf = route.lastIndexOf("\\");
		if (indexOf > 0) {
			return route.substring(0, indexOf);
		}
		indexOf = route.lastIndexOf("/");
		if (indexOf > 0) {
			return route.substring(0, indexOf);
		}
		return null;
	}

	public static String getFileName(String route) {
		int indexOf = route.lastIndexOf("\\");
		if (indexOf > 0) {
			return route.substring(indexOf + 1);
		}
		indexOf = route.lastIndexOf("/");
		if (indexOf > 0) {
			return route.substring(indexOf + 1);
		}
		return null;
	}

}
