package com.computer.subscribe.listener;

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * 通过监听器获取项目绝对路径, 并且自定义配置系统属性变量
 * 
 * @author user
 *
 */
//@WebListener
public class MineListener
		implements ServletContextListener, ServletContextAttributeListener {

	public static Logger logger = Logger.getLogger(MineListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String logDirPath = "/home/user/admin/workspaces/java-engineering/ComputerSubscribe/subscribe/logs";

		System.err.println(this.getClass());
		logger.info("监听创建,ServletContext对象已创建此方法触发");
		System.err.println("服务器一启动就去加载框架的一些配置信息..");

		System.setProperty("log4jDir", logDirPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.err.println(this.getClass());
		logger.info("监听销毁,ServletContext对象已销毁此方法触发");
		System.err.println("ServletContext对象销毁了...");
	}

}
