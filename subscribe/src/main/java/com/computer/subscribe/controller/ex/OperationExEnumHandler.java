package com.computer.subscribe.controller.ex;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.response.WebResponse;

/**
 * 自定义异常枚举之返还处理
 * 
 * @author user
 *
 */
@ControllerAdvice
public class OperationExEnumHandler {
	/**
	 * 当产生业务异常时,触发此方法,返回异常給前台
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler({ OperationException.class })
	public WebResponse<Void> handleOperationException(Throwable e) {
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

		case "您提交的原密码不正确":
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

		case "提交的新密码与原先的登录密码一致,无需修改":
			res.setCode(ExceptionsEnum.NEWKEYWD_SAME_AS_OLDKEYWDTEXT.getCode());
			break;

		case "您尚未登录或登录状态已过期,请登录帐号":
			res.setCode(ExceptionsEnum.HADNOT_LOGINED.getCode());
			break;

		case "您提交的新信息与原有的资料没有差别,若您真的想要修改资料,请认真填写":
			res.setCode(ExceptionsEnum.PROFILE_NO_DIFFERENCE.getCode());
			break;

		case "学号或工号未填写,请填写好后再提交":
			res.setCode(ExceptionsEnum.USER_USERNUM_IS_NULL.getCode());
			break;

		case "用户电话未填写,请填写好后再提交":
			res.setCode(ExceptionsEnum.USER_PHONE_IS_NULL.getCode());
			break;

		case "用户邮箱未填写,请填写好后再提交":
			res.setCode(ExceptionsEnum.USER_MAILBOX_IS_NULL.getCode());
			break;

		case "用户密码未填写,请填写好后再提交":
			res.setCode(ExceptionsEnum.USER_PASSWORD_IS_NULL.getCode());
			break;

		case "用户姓名未填写,请填写好后再提交":
			res.setCode(ExceptionsEnum.USER_USERNAME_IS_NULL.getCode());
			break;

		case "帐户类型未填写,请填写好后再提交":
			res.setCode(ExceptionsEnum.USER_ROLE_IS_NULL.getCode());
			break;

		case "您尚未登录,请先登录帐号":
			res.setCode(ExceptionsEnum.ACCOUNT_BEING_OFFLINE.getCode());
			break;

		case "无效的审核预约状态":
			res.setCode(ExceptionsEnum.INVALID_SUBSCRIBE_STATUS.getCode());
			break;

		case "预约申请时段超出范围,请在上午,下午,晚上三者之间选择":
			res.setCode(ExceptionsEnum.SUBSCRIBE_USEINTERVAL_OUT_RANGE.getCode());
			break;

		case "机房数量不得超出限制":
			res.setCode(ExceptionsEnum.ROOM_QUANTITY_CANNOT_EXCEED.getCode());
			break;
		}

		return res;
	}
}
