package com.computer.subscribe.listener;

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * 通过监听器获取项目绝对路径, 并且自定义配置系统属性变量 <br>
 * 
 * <i>后续:设置系统变量未生效,搁置此监听器</i>
 * 
 * @author user
 *
 */
public class Log4jListener
		implements ServletContextListener, ServletContextAttributeListener {

	public static Logger logger = Logger.getLogger(Log4jListener.class);

	private static String key = "log4j_path";
	private static String value_path = "/home/user/admin/workspaces/java-engineering/ComputerSubscribe/subscribe/logs";

	/**
	 * 设置自定义系统变量
	 */
	public void setMineProperty() {
		System.out.println("enter setMineProperty()");

		System.setProperty(key, value_path);
		System.out.println("setMineProperty() doing...");

		String property = System.getProperty(key);
		logger.info("=== getMineProperty() ====" + property);
		System.err.println("leave setMineProperty() get=== " + property);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.err.println(this.getClass().getName());
		logger.info("监听创建,ServletContext对象已创建此方法触发");
		System.err.println("服务器一启动就去加载框架的一些配置信息..");

		setMineProperty();
		logger.info("=== contextInitialized() done ===");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.err.println(this.getClass().getName());
		logger.info("监听销毁,ServletContext对象已销毁此方法触发");
		System.err.println("ServletContext对象销毁了...");
	}

}
