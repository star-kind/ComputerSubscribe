package com.computer.subscribe.exception;

/**
 * 自定义单一类异常枚举
 * 
 * @author user
 *
 */
public enum OffcialExEnum {
	BIND_EX(777),
	/***/
	METHOD_ARGUMENT_NOT_VALID_EX(776),
	/***/
	VALIDATION_EX(775),
	/***/
	CONSTRAINT_VIOLATION_EX(774),
	/** 实体类属性空指针异常 */
	ATTRIBUTE_NP_EX(773),;

	public void setStatus(Integer index) {
		this.status = index;
	}

	private Integer status;

	OffcialExEnum(Integer status) {
		this.setStatus(status);
	}

	public Integer getStatus() {
		return status;
	}

}
