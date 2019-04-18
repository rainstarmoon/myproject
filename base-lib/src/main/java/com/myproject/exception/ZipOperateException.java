package com.myproject.exception;

/**
 * zip操作异常
 * 
 * @author XiaZeyu
 */
public class ZipOperateException extends RuntimeException {

	private static final long serialVersionUID = 5316819619817424040L;

	public ZipOperateException() {
		super();
	}

	public ZipOperateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ZipOperateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZipOperateException(String message) {
		super(message);
	}

	public ZipOperateException(Throwable cause) {
		super(cause);
	}

}
