package com.computer.subscribe.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.util.JwtUtils;

/**
 * 自定义拦截器
 * 
 * @author user
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	public static Logger logger = Logger.getLogger(LoginInterceptor.class);

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
		System.out.println("RequestURL=== " + request.getRequestURL());

		String token = request.getHeader("token");
		System.err.println("token-header:preHandle===> " + token);

		if (StringUtils.isNotEmpty(token)) {
			JwtUtils utils = new JwtUtils();
			PrintWriter out = response.getWriter();

			LoginData data = utils.decode(token, LoginData.class);
			System.out.println("decode:preHandle===> " + data.toString());

			LoginData data2 = utils.updateDecode(token, LoginData.class);
			System.err.println("updateDecode::preHandle===> " + data2.toString());

			out.write("令牌存在业已登录");
			out.flush();
			out.close();
		}
		return true;
	}

}
