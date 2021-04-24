package com.computer.subscribe.exception;

/**
 * 自定义异常-校验实体属性空指针
 * 
 * @author user
 *
 */
public class ValidatEntityNPException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidatEntityNPException() {
		super();

	}

	public ValidatEntityNPException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ValidatEntityNPException(String message, Throwable cause) {
		super(message, cause);

	}

	public ValidatEntityNPException(String message) {
		super(message);

	}

	public ValidatEntityNPException(Throwable cause) {
		super(cause);

	}

}
