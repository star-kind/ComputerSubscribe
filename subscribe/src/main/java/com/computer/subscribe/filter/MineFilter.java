package com.computer.subscribe.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class MineFilter<br>
 * 跨域设置拦截器
 */
public class MineFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public MineFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 禁止首页访问
		String url = request.getRequestURI();
		if ("/".equals(url)) {
			return;
		}

		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");

		// 响应类型
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, DELETE, OPTIONS, DELETE");

		// 响应头设置
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header, StarKid-Access-Token");

		// 拦截器之链
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
