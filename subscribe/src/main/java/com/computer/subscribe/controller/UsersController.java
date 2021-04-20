package com.computer.subscribe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.service.IUserService;

/**
 * 用户控制器<br>
 * <b>GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交</b>
 * 
 * @author user
 *
 */
@Controller
@RequestMapping("/UsersController")
public class UsersController extends BasicController {

	@Autowired
	private IUserService ius;

	/**
	 * http://localhost:8080/subscribe/UsersController/modifyUserAction?userName=currant.goden&phone=19703640870&mailbox=506897004@qq.com&role=2&id=15
	 * 
	 * <br>
	 * <ul>
	 * <li>被修改用户的姓名</li>
	 * <li>被修改用户的角色类型</li>
	 * <li>被修改用户的邮箱</li>
	 * <li>被修改用户的电话</li>
	 * <li>基于:被修改用户的ID</li>
	 * <li>校验:管理员工号(修改者)</li>
	 * </ul>
	 * 
	 * @param submitUserParam
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyUserAction", method = RequestMethod.GET)
	public WebResponse<TUser> modifyUserAction(@Valid @NotNull TUser submitUserParam,
			HttpServletRequest request) {
		LoginData loginData = getLoginDataByToken(request);
		Long adminNum = loginData.getUserNum();

		printMethod(this.getClass() + "--modifyUserAction==", submitUserParam,
				"adminNum===" + adminNum);

		TUser user = ius.modifyUserInfoByAdminNum(submitUserParam,
				loginData.getUserNum());

		return new WebResponse<TUser>(SUCCESS, "管理员成功修改一名用户的基本资料", user);
	}

	/**
	 * http://localhost:8080/subscribe/UsersController/getMemberByOrderAction?pageOrder=0&rows=2&id=18
	 * 
	 * @param id        Administrator.ID
	 * @param pageOrder 页数
	 * @param rows      每页展示行数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMemberByOrderAction", method = RequestMethod.GET)
	public WebResponse<List<TUser>> getMemberByOrderAction(@Valid Integer pageOrder,
			@Valid Integer rows, HttpServletRequest request) {
		// 后期将从令牌中获取关键数据
		LoginData loginData = getLoginDataByToken(request);

		System.err.println(this.getClass() + "__getMemberByOrderAction.pageOrder== "
				+ pageOrder + ",rows== " + rows);

		Pagination<List<TUser>> pagination = ius.getMembersListByOrder(pageOrder,
				rows, loginData.getId());
		return new WebResponse<List<TUser>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/UsersController/revisePasswordAction?userNum=6515615&newPasswd=3245&oldPasswd=0014
	 * <br>
	 * 参数为:新密码,旧密码 <br>
	 * 
	 * @param newPasswd 新的密码
	 * @param oldPasswd 旧的密码
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/revisePasswordAction", method = RequestMethod.GET)
	public WebResponse<Integer> revisePasswordAction(
			@RequestParam("newPasswd") @Valid String newPasswd,
			@RequestParam("oldPasswd") @Valid String oldPasswd,
			HttpServletRequest req) {
		printMethod(this.getClass(), "revisePasswordAction--newpassword== "
				+ newPasswd + ", oldpassword== " + oldPasswd);

		// 后期将从令牌中获取关键数据
		String header = req.getHeader("token");
		System.out.println(this.getClass()
				+ ".revisePasswordAction\n.header.token== " + header);
		LoginData loginData = jwt.decode(header, LoginData.class);
		Long userNum = loginData.getUserNum();

		Integer affect = ius.revisePassword(newPasswd, oldPasswd, userNum);
		return new WebResponse<Integer>(SUCCESS, "SUCCESSFUL REVISE PASSWORD",
				affect);
	}

	/**
	 * http://localhost:8080/subscribe/UsersController/loginAction?role=1&userNum=385170048&passwd=664
	 * <br>
	 * 参数为:工号/学号,密码,角色(即帐号类型)<br>
	 * 
	 * @param passwd  Forehand sends password
	 * @param userNum User(administrator/teacher/students) Numer
	 * @param role    the user's role(administrator/teacher/students)
	 * @return
	 * @throws OperationException
	 */
	@ResponseBody
	@RequestMapping(value = "/loginAction", method = RequestMethod.GET)
	public WebResponse<LoginData> loginAction(@Valid String passwd,
			@Valid Long userNum, @Valid Integer role) throws OperationException {
		printMethod(this.getClass(), "loginAction()",
				"role== " + role + ",userNum== " + userNum + ",passwd== " + passwd);

		LoginData data = ius.login(userNum, passwd, role);

		return new WebResponse<LoginData>(SUCCESS, data);
	}

	/**
	 * 注册新用户<br>
	 * 
	 * http://localhost:8080/subscribe/UsersController/registerAction?userName=skypt&password=3210&userNum=1050048&phone=18370273627&mailbox=165174714570@qq.com&role=2
	 * 
	 * <br>
	 * 参数为1个用户:(邮箱,电话,工号/学号,用户名,角色)
	 * 
	 * @param user ("用户注册材料")
	 * @return
	 * @throws OperationException
	 */
	@ResponseBody
	@RequestMapping(value = "/registerAction", method = RequestMethod.GET)
	public WebResponse<Integer> registerAction(@Valid @NotNull TUser user)
			throws OperationException {

		logger.info(user.toString());
		Integer row = ius.regist(user);

		return new WebResponse<Integer>(SUCCESS, "OK", row);
	}

}
