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
@Api
@Controller
@RequestMapping("/UsersController")
public class UsersController extends BasicController {
	public static Logger logger = Logger.getLogger(UsersController.class);

	@Autowired
	private IUserService ius;

	/**
	 * 注册新用户<br>
	 * 
	 * http://localhost:8080/subscribe/UsersController/registerAction?userName=巴斯克德里诺&userNum=10548941&phone=18520273627&mailbox=16517471@qq.com&role=1
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
	@ApiOperation(value = "新用户注册", notes = "参数为1个用户:{邮箱,电话,工号或学号,用户名,角色}", httpMethod = "get")
	public WebResponse<Integer> registerAction(@ApiParam("用户注册材料") @Valid TUser user)
			throws OperationException {

		logger.info(user.toString());
		Integer row = ius.regist(user);

		return new WebResponse<Integer>(SUCCESS, "", row);
	}
}
