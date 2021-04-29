package com.computer.subscribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 进入到本地服务器里的前端目录
 * 
 * @author user
 *
 */
@Controller
@RequestMapping("/TestController")
public class TestController extends BasicController {
	String ts = this.getClass() + "------\n";

	/**
	 * http://localhost:8080/subscribe/TestController/testInto
	 * 
	 * @Description 进入 inddex.jsp 页面
	 * @Param []
	 * @return
	 * @Version 1.0
	 */
	@RequestMapping("/testInto")
	public String testInto() {
		System.err.println(ts + "..testInto()");
		return "swagger/index";
	}

	/**
	 * http://localhost:8080/subscribe/TestController/testInto2
	 * 
	 * @Description 进入welcome.jsp页面
	 * @Param []
	 * @return
	 * @Version 2.0
	 */
	@RequestMapping("/welcome")
	public String testInto2() {
		System.err.println(ts + "..testInto2()");
		return "swagger/welcome";
	}

}
