package com.computer.subscribe.intercept;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * 
 * @author user
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, Exception arg3) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		System.err.println("拦截器:preHandle");

		/** 所要拦截的请求路径 */
		ArrayList<String> urlList = new ArrayList<String>();
		urlList.add("/UsersController/**");

		// flag变量用于判断用户是否登录, 默认为 true
		boolean flag = true;

		// 获取请求的路径进行判断(@controler的路径,如"/fore/addGoods")
		String servletPath = request.getServletPath();
		System.err.println("servletPath: " + servletPath);

		// 判断请求是否需要拦截
		for (String s : urlList) {
			if (servletPath.contains(s)) {
				flag = false;
				break;
			}
		}

		// 拦截请求
		if (!flag) {
			// 获取session中的用户
			String name = (String) request.getSession().getAttribute("username");

			// 判断用户是否已经登录
			if ("".equals(name) || name == null) {
				// 验证还未登录，跳转至登录界面
				response.sendError(440, "Author还未登录，跳转至登录界面");
				System.err.println("验证还未登录，跳转至登录界面");
			} else {
				// 如果用户已经登录，则验证通过，放行
				System.out.println("AuthorizationInterceptor放行请求");
				flag = true;
			}
		}

		return flag;
	}

}
