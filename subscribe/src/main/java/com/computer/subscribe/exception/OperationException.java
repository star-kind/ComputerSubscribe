package com.computer.subscribe.exception;

/**
 * 自定义操作异常
 * 
 * @author user
 *
 */
public class OperationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public OperationException() {
		super();
		
	}

	public OperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public OperationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public OperationException(String message) {
		super(message);
		
	}

	public OperationException(Throwable cause) {
		super(cause);
		
	}

	
}
