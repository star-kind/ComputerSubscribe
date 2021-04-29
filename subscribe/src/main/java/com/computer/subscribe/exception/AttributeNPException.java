package com.computer.subscribe.exception;

/**
 * 实体类属性空指针异常
 * 
 * @author user
 *
 */
public class AttributeNPException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AttributeNPException() {
		super();

	}

	public AttributeNPException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public AttributeNPException(String message, Throwable cause) {
		super(message, cause);

	}

	public AttributeNPException(String message) {
		super(message);

	}

	public AttributeNPException(Throwable cause) {
		super(cause);

	}

}
