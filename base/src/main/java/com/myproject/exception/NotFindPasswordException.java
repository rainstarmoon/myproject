package com.myproject.exception;

/**
 * 没有发现密码异常
 * 
 * @author XiaZeyu
 */
public class NotFindPasswordException extends Exception {

	private static final long serialVersionUID = -3672265543402232529L;

	public NotFindPasswordException() {
		super();
	}

	public NotFindPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFindPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFindPasswordException(String message) {
		super(message);
	}

	public NotFindPasswordException(Throwable cause) {
		super(cause);
	}

}
