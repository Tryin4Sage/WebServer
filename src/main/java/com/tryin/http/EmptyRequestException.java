package com.tryin.http;
/**
 * 空请求异常
 * @TODO 客户端发送空请求时,抛出该异常
 * @author Tryin4Sage
 * @date 2018年2月4日下午1:49:22
 */
public class EmptyRequestException extends Exception{
	private static final long serialVersionUID = 1L;

	public EmptyRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
