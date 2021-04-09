package com.computer.subscribe.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.response.WebResponse;

/**
 * 超类控制器
 * 
 * @author user
 *
 */
// @ControllerAdvice：包含@Component, 可以被扫描到, 统一处理异常
@ControllerAdvice
public class BasicController {
	/**
	 * "成功"
	 */
	public static final Integer SUCCESS = 200;

	/**
	 * 当产生业务异常时,触发此方法,返回异常給前台
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler({ OperationException.class })
	public WebResponse<Void> exceptionsHandler(Throwable e) {
		WebResponse<Void> res = new WebResponse<Void>();

		res.setMessage(e.getLocalizedMessage());

		/* 根据异常信息设异常码 */
		switch (e.getLocalizedMessage()) {
		case "此电话号码已注册，请更换另一个号码":
			res.setCode(ExceptionsEnum.PHONE_DUPLICATE_CONFLICT.getCode());
			break;

		case "账号(学号或工号)不存在":
			res.setCode(ExceptionsEnum.ACCOUNT_NO_EXIST.getCode());
			break;

		case "登录密码错误":
			res.setCode(ExceptionsEnum.LOGIN_PASSWORD_ERR.getCode());
			break;

		case "您要修改的原密码不正确":
			res.setCode(ExceptionsEnum.OLD_PASSWORD_ERR.getCode());
			break;

		case "此邮箱已被注册，请更换另外一个":
			res.setCode(ExceptionsEnum.EMAIL_DUPLICATE_CONFLICT.getCode());
			break;

		case "您不是学生，无此权限":
			res.setCode(ExceptionsEnum.NOT_STUDENT_PRIVILEGE.getCode());
			break;

		case "您不是教师，无此权限":
			res.setCode(ExceptionsEnum.NOT_TEACHER_PRIVILEGE.getCode());
			break;

		case "管理员至多允许同时存在5位":
			res.setCode(ExceptionsEnum.OUT_ADMIN_QUANTITY.getCode());
			break;

		case "注册时提交的材料不完整":
			res.setCode(ExceptionsEnum.REGIST_DATA_INCOMPLETE.getCode());
			break;

		case "请输入账号(学号或工号)":
			res.setCode(ExceptionsEnum.DIDNOT_INPUT_ACCOUNT.getCode());
			break;

		case "请输入登录密码":
			res.setCode(ExceptionsEnum.DIDNOT_INPUT_LOGIN_KEY.getCode());
			break;

		case "请输入的要设置的新密码":
			res.setCode(ExceptionsEnum.DIDNOT_INPUT_NEW_KWD.getCode());
			break;

		case "账号[学号或工号]已存在":
			res.setCode(ExceptionsEnum.ACCOUNT_DUPLICATE_CONFLICT.getCode());
			break;

		case "帐户类型错误!您不是这个类型的帐户":
			res.setCode(ExceptionsEnum.ERR_TYPE_PRIVILEGE.getCode());
			break;
		}
		return res;
	}

}
