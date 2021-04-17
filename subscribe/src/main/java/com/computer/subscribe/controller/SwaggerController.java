package com.computer.subscribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h2>http://localhost:8080/subscribe/SwaggerController/swagger-ui</h2>
 * 
 * @author user
 *
 */
@Controller
@RequestMapping("/SwaggerController")
public class SwaggerController extends BasicController {

	/**
	 * @Description 进入swagger-ui.jsp页面
	 * @Param []
	 * @return
	 * @Version 1.0
	 */
	@RequestMapping("/swagger-ui")
	public String index() {
		System.err.println("come in SwaggerController.index()");
		logger.info("进入swagger-ui-index.jsp页面");

		return "swagger/index";
	}

	/**
	 * @Description 进入swagger-ui.jsp页面
	 * @Param []
	 * @return
	 * @Version 2.0
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		System.err.println("come in SwaggerController.welcome()");
		logger.info("进入swagger-ui-welcome.jsp页面");

		return "swagger/welcome";
	}

}
