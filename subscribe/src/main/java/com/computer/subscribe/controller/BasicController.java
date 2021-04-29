package com.computer.subscribe.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.util.JwtUtils;
import com.computer.subscribe.util.support.ValidFieldNPExUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 超类控制器
 * 
 * @author user
 *
 */
@ControllerAdvice
public class BasicController {
	public static Logger logger = Logger.getLogger(BasicController.class);

//	protected static int bind_ex_code = 777;
//	protected static int method_argument_not_valid_ex_code = 776;
//	protected static int validation_ex_code = 775;
//	protected static int constraint_violation_ex_code = 774;

	public static final Integer SUCCESS = 200;

	JwtUtils jwt = JwtUtils.getInstance();
	ValidFieldNPExUtil validFieldNPExUtil = ValidFieldNPExUtil.getInstance();

	/**
	 * 打印程序执行过程中的一些数据/参数
	 * 
	 * @param elements
	 */
	public void printMethod(Object... elements) {
		for (Object ele : elements) {
			System.err.println("------");
			System.err.println(ele);
		}
	}

	/**
	 * 从请求头中的令牌里,获取相应数据
	 * 
	 * @param req
	 * @return
	 */
	public LoginData getLoginDataByToken(HttpServletRequest req) {
		String headerToken = req.getHeader("token");
		printMethod(this.getClass(), "headerToken== " + headerToken);

		if ("".equals(headerToken)) {
			String description = ExceptionsEnum.ACCOUNT_BEING_OFFLINE
					.getDescription();
			logger.error(description);
			throw new OperationException(description);
		}
		System.err.println(this.getClass() + "\n__getDataFromToken__headerToken="
				+ headerToken);

		LoginData loginData = jwt.decode(headerToken, LoginData.class);

		printMethod(this.getClass().getName(), "loginData", loginData.toString());
		return loginData;
	}

}
