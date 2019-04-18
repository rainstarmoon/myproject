package com.myproject.exception;

/**
 * 网络连接异常
 * 
 * @author XiaZeyu
 */
public class HttpClientException extends RuntimeException {

	private static final long serialVersionUID = 8407065718232621634L;

	public HttpClientException() {
		super();
	}

	public HttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HttpClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpClientException(String message) {
		super(message);
	}

	public HttpClientException(Throwable cause) {
		super(cause);
	}

}
