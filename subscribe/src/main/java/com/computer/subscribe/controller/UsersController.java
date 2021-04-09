package com.computer.subscribe.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.controller.BasicController;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户控制器
 * 
 * @author user
 *
 */
@Api(value = "/UsersController")
@Controller
@RequestMapping("/UsersController")
public class UsersController extends BasicController {
	public static Logger logger = Logger.getLogger(UsersController.class);

	@Autowired
	private IUserService ius;

	/**
	 * http://localhost:8080/subscribe/UsersController/loginAction?role=
	 * @param passwd
	 * @param userNum
	 * @param role
	 * @return
	 * @throws OperationException
	 */
	@ResponseBody
	@RequestMapping(value = "/loginAction", method = RequestMethod.GET)
	@ApiOperation(value = "帐户登录", notes = "参数为:工号/学号,密码,角色(即帐号类型)", httpMethod = "GET")
	public WebResponse<LoginData> loginAction(
			@ApiParam("Forehand sends password") @Valid String passwd,
			@ApiParam("User(administrator/teacher/students) Numer") @Valid Long userNum,
			@ApiParam("the user's role(administrator/teacher/students)") @Valid Integer role)
			throws OperationException {
		logger.info(
				"role== " + role + ",userNum== " + userNum + ",passwd== " + passwd);
		System.err.println(
				"role== " + role + ",userNum== " + userNum + ",passwd== " + passwd);

		LoginData data = ius.login(userNum, passwd, role);

		return new WebResponse<LoginData>(SUCCESS, data);
	}

	/**
	 * 注册新用户<br>
	 * 
	 * http://localhost:8080/subscribe/UsersController/registerAction?userName=skypt&userNum=1050048&phone=18370273627&mailbox=165174714570@qq.com&role=2
	 * 
	 * <br>
	 * 
	 * 
	 * <b>GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交</b>
	 * 
	 * @param user
	 * @return
	 * @throws OperationException
	 */
	@ResponseBody
	@RequestMapping(value = "/registerAction", method = RequestMethod.GET)
	@ApiOperation(value = "新用户注册", notes = "参数为1个用户:(邮箱,电话,工号/学号,用户名,角色)", httpMethod = "GET")
	public WebResponse<Integer> registerAction(@ApiParam("用户注册材料") @Valid TUser user)
			throws OperationException {

		logger.info(user.toString());
		Integer row = ius.regist(user);

		return new WebResponse<Integer>(SUCCESS, "OK", row);
	}
}
