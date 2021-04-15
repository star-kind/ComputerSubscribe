package com.computer.subscribe.exception;

/**
 * 枚举，将自我操作异常类型汇集于此
 * 
 * @author user
 *
 */
public enum ExceptionsEnum {
	/* SUBSCRIBE */
	/** 系统在周末不接受学生预约申请,教师也不能进行任何批复 */
	INVALID_DATE_WEEKEND(906, "系统在周末不接受学生预约申请,教师也不能进行任何批复"),
	/** 预约使用的日期无效,必须是下一周的周一至周五 */
	SUBSCRIBE_DATE_INVALID(907, "预约使用的日期无效,必须是下一周的周一至周五"),
	/** 您已经预约成功了另一个机房在此日同一时段的机位,请不要重复申请预约了 */
	SUCCESS_SUBSCRIBE_DUPLICATION(908, "您已经预约成功了另一个机房在此日同一时段的机位,请不要重复申请预约了"),
	/** 您此前已对某间机房,在同一日期同一时段提出了申请,其正在等待审核中,请不要重复申请预约 */
	WAITTING_APPLY_DUPLICATION(909, "您此前已对某间机房,在同一日期同一时段提出了申请,其正在等待审核中,请不要重复申请预约"),
	/** 预约业已是这个状态,请勿重复审批 */
	HANDLE_STATUS_DUPLICATION(910, "预约业已是这个状态,请勿重复审批"),
	/** 申请人在该日同时段下,已有通过之预约,勿重复批准其通过 */
	SUBSCRIBE_HAS_SUCCESS_DUPLICATION(911, "申请人在该日同时段下,已有通过之预约,勿重复批准其通过"),
	/** 该预约申请时间不再本周内,已失效 */
	SUBSCRIBE_NOT_IN_THIS_WEEK(912, "该预约申请发起的时间不在本周内,已失效"),
	/** 预约申请不存在 */
	SUBSCRIBE_NOT_EXIST(913, "预约申请不存在"),
	/* user */
	/** 此电话号码已注册，请更换另一个号码 */
	PHONE_DUPLICATE_CONFLICT(1006, "此电话号码已注册,请更换另一个号码"),
	/** 账号不存在 */
	ACCOUNT_NO_EXIST(1007, "账号不存在"),
	/** 登录密码错误 */
	LOGIN_PASSWORD_ERR(1008, "登录密码错误"),
	/** 您要修改的原密码不正确 */
	OLD_PASSWORD_ERR(1009, "您要修改的原密码不正确"),
	/** 此邮箱已被注册，请更换另外一个 */
	EMAIL_DUPLICATE_CONFLICT(1010, "此邮箱已被注册,请更换另外一个"),
	/** 您不是管理员，无此权限 */
	NOT_ADMIN_PRIVILEGE(1011, "您不是管理员,无此权限"),
	/** 您不是学生，无此权限 */
	NOT_STUDENT_PRIVILEGE(1012, "您不是学生,无此权限"),
	/** 您不是教师，无此权限 */
	NOT_TEACHER_PRIVILEGE(1013, "您不是教师,无此权限"),
	/** 管理员至多允许同时存在5位 */
	OUT_ADMIN_QUANTITY(1014, "管理员数量超出规定"),
	/** 注册时提交的材料不完整 */
	REGIST_DATA_INCOMPLETE(1015, "注册时提交的材料不完整"),
	/** 请输入账号(学号或工号) */
	DIDNOT_INPUT_ACCOUNT(1016, "请输入账号(学号或工号)"),
	/** 请输入登录密码 */
	DIDNOT_INPUT_LOGIN_KEY(1017, "请输入登录密码"),
	/** 请输入要设置的新密码 */
	DIDNOT_INPUT_NEW_KWD(1018, "请输入要设置的新密码"),
	/** 账号[学号或工号]已存在 */
	ACCOUNT_DUPLICATE_CONFLICT(1019, "账号[学号或工号]已存在"),
	/** 帐户类型错误!您不是这个类型的帐户 */
	ERR_TYPE_PRIVILEGE(1020, "帐户类型错误!您不是这个类型的帐户"),
	/** 管理员帐户不可被直接修改 */
	ADMIN_CANNOT_MODIFIED(1021, "管理员帐户不可被直接修改"),
	/** 您的帐号没有这个权限 */
	U_ACCOUNT_NOT_IT_PRIVILEGE(1022, "您的帐号没有这个权限"),
	/** 管理员帐户不存在 */
	ADMINISTRATOR_NO_EXIST(1030, "管理员帐户不存在"),;

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
	 * 
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
	public static String getDescByCode(Integer code) {
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
	public static Integer getCodeByDesc(String description) {
		for (ExceptionsEnum element : ExceptionsEnum.values()) {
			if (description.equals(element.description)) {
				return element.code;
			}
		}
		return null;
	}

}
