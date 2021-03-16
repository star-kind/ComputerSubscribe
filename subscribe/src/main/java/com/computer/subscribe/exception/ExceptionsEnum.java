package com.computer.subscribe.exception;

/**
 * 枚举，将自我操作异常类型汇集于此
 * 
 * @author user
 *
 */
public enum ExceptionsEnum {
	/** 此电话号码已注册，请更换另一个号码 */
	PHONE_DUPLICATE_CONFLICT(1006, "此电话号码已注册，请更换另一个号码"),
	/**账号(学号或工号)不存在*/
	ACCOUNT_NO_EXIST(1007,"账号(学号或工号)不存在"),
	/**登录密码错误*/
	LOGIN_PASSWORD_ERR(1008,"登录密码错误"),
	/**您要修改的原密码不正确*/
	OLD_PASSWORD_ERR(1009,"您要修改的原密码不正确"),
	/**此邮箱已被注册，请更换另外一个*/
	EMAIL_DUPLICATE_CONFLICT(1010,"此邮箱已被注册，请更换另外一个"),
	/**您不是管理员，无此权限*/
	NOT_ADMIN_PRIVILEGE(1011,"您不是管理员，无此权限"),
	/**您不是学生，无此权限*/
	NOT_STUDENT_PRIVILEGE(1012,"您不是学生，无此权限"),
	/**您不是教师，无此权限*/
	NOT_TEACHER_PRIVILEGE(1013,"您不是教师，无此权限"),
	/**管理员至多允许同时存在5位*/
	OUT_ADMIN_QUANTITY(1014,"管理员至多允许同时存在5位"),
	/**注册时提交的材料不完整*/
	REGIST_DATA_INCOMPLETE(1015,"注册时提交的材料不完整"),
	/**请输入账号(学号或工号)*/
	DIDNOT_INPUT_ACCOUNT(1016,"请输入账号(学号或工号)"),
	/**请输入登录密码*/
	DIDNOT_INPUT_LOGIN_KEY(1017,"请输入登录密码"),
	/**请输入的要设置的新密码*/
	DIDNOT_INPUT_NEW_KWD(1018,"请输入的要设置的新密码"),
	/**账号[学号或工号]已存在*/
	ACCOUNT_DUPLICATE_CONFLICT(1019,"账号[学号或工号]已存在"),
	;
	
	/**
	 * 异常码
	 */
	private Integer code;
	
	/**
	 * 异常之具体描述
	 */
	private String description;
	
	/**
	 * 无参构造器
	 */
	private ExceptionsEnum() {
	}

	/**
	 * 双参数构造器
	 * @param code
	 * @param description
	 */
	private ExceptionsEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 根据code获取description
	 * 
	 * @param code
	 * @return
	 */
	public String getDescByCode(Integer code) {
		for (ExceptionsEnum element : ExceptionsEnum.values()) {
			if (code == element.code) {
				return element.description;
			}
		}
		return null;
	}

	/**
	 * 根据description获取code
	 * 
	 * @param description
	 * @return
	 */
	public Integer getCodeByDesc(String description) {
		for (ExceptionsEnum element : ExceptionsEnum.values()) {
			if (description.equals(element.description)) {
				return element.code;
			}
		}
		return null;
	}
	
}
