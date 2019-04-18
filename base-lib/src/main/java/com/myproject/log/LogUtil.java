package com.myproject.log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class LogUtil {

	public static void printMyInfo(String message) {
		Logger logger = LoggerFactory.getLogger("my_info");
		logger.info(message);
	}

    public static void printNoneExceptionLog(Class<?> clazz, String message) {
        printNoneExceptionLog(clazz, LoggerEnum.INFO, message);
    }

    public static void printExceptionLog(Class<?> clazz, String message, Exception exception) {
        printExceptionLog(clazz, LoggerEnum.ERROR, message, exception);
    }

    private static void printNoneExceptionLog(Class<?> clazz, LoggerEnum loggerEnum, String message) {
        Logger logger = LoggerFactory.getLogger(clazz);
        Method method = null;
        try {
            method = logger.getClass().getMethod(loggerEnum.getValue(), String.class);
            method.invoke(logger, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printExceptionLog(Class<?> clazz, LoggerEnum loggerEnum, String message, Exception exception) {
        Logger logger = LoggerFactory.getLogger(clazz);
        Method method = null;
        StringBuffer logOut = new StringBuffer();
        logOut.append(message);
        logOut.append("\n");
        if (exception != null) {
            logOut.append(ExceptionUtils.getStackTrace(exception));
        }
        try {
            method = logger.getClass().getMethod(loggerEnum.getValue(), String.class);
            method.invoke(logger, logOut.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}