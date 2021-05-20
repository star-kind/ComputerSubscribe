package com.computer.subscribe.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
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

	String str = this.getClass().getName() + "____\n";

	JwtUtils jwt = JwtUtils.getInstance();

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
		String string = str + "__拦截器:preHandle()__\n";

		/*
		 * 给指定请求路径,或若已在线,也可放行
		 * RequestURL=http://localhost:8080/subscribe/UsersController/registerAction
		 */
		StringBuffer requestURL = request.getRequestURL();
		String actionURL = getActionURL(requestURL);
		Boolean outBool = ruleOutURL(actionURL);

		String token = request.getHeader("token");
		Boolean hadOnline = judgeHadOnline(token);

		/*
		 * 若既不是指定的请求路径,令牌又为空
		 */
		if (!outBool) {
			if (!hadOnline) {
				String description = ExceptionsEnum.HADNOT_LOGINED.getDescription();
				logger.warn(description);
				System.err.println(string + "--HADNOT_LOGINED==" + description);
				throw new OperationException(description);
			}
		}

		return true;
	}

	/**
	 * 判断是否已经在线,在线则归真,离线则返假
	 * 
	 * @param token
	 * @return
	 */
	public Boolean judgeHadOnline(String token) {
		Boolean hadOnline = true;
		System.err.println(str + "__header..token=" + token);

		if (!StringUtils.isNotEmpty(token)) {
			hadOnline = false;
			System.err.println(str + "--hadOnline==" + hadOnline);

		} else if ("undefined".equals(token)) {
			hadOnline = false;
			System.err.println(str + "--undefined--hadOnline==" + hadOnline);

		} else {
			LoginData data = jwt.decode(token, LoginData.class);
			System.out.println(str + "__decode=" + data.toString());

			LoginData data2 = jwt.updateDecode(token, LoginData.class);
			System.err.println(str + "__updateDecode=" + data2.toString());
		}

		return hadOnline;
	}

	/**
	 * 获取终端请求路径
	 * 
	 * @param requestURL
	 * @return
	 */
	public String getActionURL(StringBuffer requestURL) {
		System.err.println(str + "--getActionURL..strbuf..requestURL=" + requestURL);

		int i = requestURL.lastIndexOf("/");
		System.out.println(str + "--getActionURL..requestURL..index=" + i);

		String actionName = requestURL.substring(i + 1);
		System.err.println(
				str + "--getActionURL..requestURL..actionName=" + actionName);

		return actionName;
	}

	/**
	 * 判断是否在规则外,在则返真,不在则返假
	 * 
	 * @param reqUrl
	 * @return
	 */
	public Boolean ruleOutURL(String reqUrl) {
		System.err.println(str + "--ruleOutURL..reqUrl=" + reqUrl);
		Boolean ruleOutBool = false;

		switch (reqUrl) {
		case "registerAction":
			ruleOutBool = true;
			break;

		case "loginAction":
			ruleOutBool = true;
			break;

		default:
			System.err.println(str + "--ruleOutURL..switch..default..ruleOutBool="
					+ ruleOutBool);
			break;
		}

		System.out.println(str + "--ruleOutURL..return..ruleOutBool=" + ruleOutBool);
		return ruleOutBool;
	}

}
