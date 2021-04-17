package com.computer.subscribe.controller;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.response.WebResponse;

import javax.validation.ConstraintViolationException;

/**
 * 超类控制器
 * 
 * @author user
 *
 */
//可以被扫描到, 统一处理异常
@ControllerAdvice
public class BasicController {
	public static Logger logger = Logger.getLogger(BasicController.class);

	private static int PARAM_FAIL_CODE = 1001;
	private static int VALIDATION_CODE = 1002;

	/**
	 * "成功"
	 */
	public static final Integer SUCCESS = 200;

	/**
	 * 方法参数校验:BindException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public WebResponse<Void> handleBindException(BindException e) {
		System.err.println(this.getClass() + "+++ handleBindException +++" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(PARAM_FAIL_CODE);
		response.setMessage(
				e.getBindingResult().getFieldError().getDefaultMessage());
		return response;
	}

	/**
	 * 方法参数校验:MethodArgumentNotValidException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 * 
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public WebResponse<Void> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		System.err.println(this.getClass()
				+ "+++ handleMethodArgumentNotValidException +++" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(PARAM_FAIL_CODE);
		response.setMessage(
				e.getBindingResult().getFieldError().getDefaultMessage());
		return response;
	}

	/**
	 * 方法参数校验:ValidationException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 */
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	public WebResponse<Void> handleValidationException(ValidationException e) {
		System.err
				.println(this.getClass() + "+++ handleValidationException +++" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(VALIDATION_CODE);
		response.setMessage(e.getCause().getMessage());
		return response;
	}

	/**
	 * 方法参数校验:ConstraintViolationException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 */
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public WebResponse<Void> handleConstraintViolationException(
			ConstraintViolationException e) {
		System.err.println(
				this.getClass() + "+++ handleConstraintViolationException +++" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(VALIDATION_CODE);
		response.setMessage(e.getCause().getMessage());
		return response;
	}

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
		case "此电话号码已注册,请更换另一个号码":
			res.setCode(ExceptionsEnum.PHONE_DUPLICATE_CONFLICT.getCode());
			break;

		case "账号不存在":
			res.setCode(ExceptionsEnum.ACCOUNT_NO_EXIST.getCode());
			break;

		case "登录密码错误":
			res.setCode(ExceptionsEnum.LOGIN_PASSWORD_ERR.getCode());
			break;

		case "您要修改的原密码不正确":
			res.setCode(ExceptionsEnum.OLD_PASSWORD_ERR.getCode());
			break;

		case "此邮箱已被注册,请更换另外一个":
			res.setCode(ExceptionsEnum.EMAIL_DUPLICATE_CONFLICT.getCode());
			break;

		case "您不是学生,无此权限":
			res.setCode(ExceptionsEnum.NOT_STUDENT_PRIVILEGE.getCode());
			break;

		case "您不是教师,无此权限":
			res.setCode(ExceptionsEnum.NOT_TEACHER_PRIVILEGE.getCode());
			break;

		case "管理员数量超出规定":
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

		case "请输入要设置的新密码":
			res.setCode(ExceptionsEnum.DIDNOT_INPUT_NEW_KWD.getCode());
			break;

		case "账号[学号或工号]已存在":
			res.setCode(ExceptionsEnum.ACCOUNT_DUPLICATE_CONFLICT.getCode());
			break;

		case "帐户类型错误!您不是这个类型的帐户":
			res.setCode(ExceptionsEnum.ERR_TYPE_PRIVILEGE.getCode());
			break;

		case "管理员帐户不可被直接修改":
			res.setCode(ExceptionsEnum.ADMIN_CANNOT_MODIFIED.getCode());
			break;

		case "管理员帐户不存在":
			res.setCode(ExceptionsEnum.ADMINISTRATOR_NO_EXIST.getCode());
			break;

		case "预约使用的日期无效,必须是下一周的周一至周五":
			res.setCode(ExceptionsEnum.SUBSCRIBE_DATE_INVALID.getCode());
			break;

		case "您已经预约成功了另一个机房在此日同一时段的机位,请不要重复申请预约了":
			res.setCode(ExceptionsEnum.SUCCESS_SUBSCRIBE_DUPLICATION.getCode());
			break;

		case "您此前已对某间机房,在同一日期同一时段提出了申请,其正在等待审核中,请不要重复申请预约":
			res.setCode(ExceptionsEnum.WAITTING_APPLY_DUPLICATION.getCode());
			break;

		case "您的帐号没有这个权限":
			res.setCode(ExceptionsEnum.U_ACCOUNT_NOT_IT_PRIVILEGE.getCode());
			break;

		case "预约业已是这个状态,请勿重复审批":
			res.setCode(ExceptionsEnum.HANDLE_STATUS_DUPLICATION.getCode());
			break;

		case "申请人在该日同时段下,已有通过之预约,勿重复批准其通过":
			res.setCode(ExceptionsEnum.SUBSCRIBE_HAS_SUCCESS_DUPLICATION.getCode());
			break;

		case "该预约申请时间不再本周内,已失效":
			res.setCode(ExceptionsEnum.SUBSCRIBE_NOT_IN_THIS_WEEK.getCode());
			break;

		case "预约申请不存在":
			res.setCode(ExceptionsEnum.SUBSCRIBE_NOT_EXIST.getCode());
			break;

		case "你并非此份预约的申请发起者,无法对其进行修改":
			res.setCode(ExceptionsEnum.NOT_THIS_SUBSCRIBE_APPLIER.getCode());
			break;

		case "本间机房现在无法使用":
			res.setCode(ExceptionsEnum.COMPUTER_ROOM_UNAVAILABLE.getCode());
			break;

		case "机房地点位置早已重复":
			res.setCode(ExceptionsEnum.LOCATION_DUPLICATION_ERROR.getCode());
			break;

		case "机房编号早已存在":
			res.setCode(ExceptionsEnum.ROOM_NUM_DUPLICATION_ERROR.getCode());
			break;

		case "电脑机房不存在":
			res.setCode(ExceptionsEnum.COMPUTER_ROOM_NOT_EXIST.getCode());
			break;

		case "实际可用电脑数不得大于电脑总数":
			res.setCode(ExceptionsEnum.ACTUAL_CANNOT_MORE_THAN_TOAL.getCode());
			break;

		}
		return res;
	}

}
