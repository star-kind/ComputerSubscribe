http://localhost:8080/subscribe/UsersController/registerAction?userName=巴斯克德里诺&userNum=10548941&phone=18520273627&mailbox=16517471@qq.com&role=1
------------------------
<!-- 配置视图解析器 -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!--配置响应前后缀 -->
		<property name="prefix" value="/WEB-INF/pages/" />
		<property name="suffix" value=".jsp" />

		<!-- 优先级 -->
		<property name="order" value="1" />
	</bean>

	<bean
		class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
		<!-- HTML文件位置 -->
		<property name="templateLoaderPath" value="/WEB-INF/pages/" />

		<property name="defaultEncoding" value="utf-8" />
		<property name="freemarkerSettings">
			<props>
				<prop key="locale">zh_CN</prop>
				<prop key="datetime_format">yyyy-MM-dd HH:mm:ss</prop>
				<prop key="date_format">yyyy-MM-dd</prop>
				<prop key="default_encoding">UTF-8</prop>
			</props>
		</property>
	</bean>
	<bean
		class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
		<property name="viewClass"
			value="org.springframework.web.servlet.view.freemarker.FreeMarkerView" />
		<property name="suffix" value=".html" />

		<!-- 优先找HTML -->
		<property name="order" value="0" />

		<!-- 解决网页中文乱码的问题 -->
		<property name="contentType" value="text/html;charset=UTF-8" />
	</bean>
------------------------
<mvc:annotation-driven />

 <context:component-scan base-package="com.computer.subscribe.controller" /> 
--------------------------
[2021-04-05 18:12:04:332] [WARN] - No mapping found for HTTP request with URI [/subscribe/UsersController/registerAction] in DispatcherServlet with name  SpringMVC  - org.springframework.web.servlet.DispatcherServlet.noHandlerFound(DispatcherServlet.java:1108)


No mapping found for HTTP request with URI   in DispatcherServlet with name  SpringMVC  - org.springframework.web.servlet.DispatcherServlet.noHandlerFound(DispatcherServlet.java:1108)
--------------
------------
-----
-------

org.springframework.beans.factory.BeanCreationException: Error creating bean with name    org.springframework.web.servlet.handler.SimpleUrlHandlerMapping#0   : Initialization of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name    org.springframework.web.servlet.handler.MappedInterceptor#1   : Could not resolve matching constructor (hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:532)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:461)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:295)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:223)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:194)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:626)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:932)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:479)
	at org.springframework.web.servlet.FrameworkServlet.configureAndRefreshWebApplicationContext(FrameworkServlet.java:651)
	at org.springframework.web.servlet.FrameworkServlet.createWebApplicationContext(FrameworkServlet.java:599)
	at org.springframework.web.servlet.FrameworkServlet.createWebApplicationContext(FrameworkServlet.java:665)
	at org.springframework.web.servlet.FrameworkServlet.initWebApplicationContext(FrameworkServlet.java:518)
	at org.springframework.web.servlet.FrameworkServlet.initServletBean(FrameworkServlet.java:459)
	at org.springframework.web.servlet.HttpServletBean.init(HttpServletBean.java:136)
	at javax.servlet.GenericServlet.init(GenericServlet.java:158)
	at org.apache.catalina.core.StandardWrapper.initServlet(StandardWrapper.java:1134)
	at org.apache.catalina.core.StandardWrapper.loadServlet(StandardWrapper.java:1089)
	at org.apache.catalina.core.StandardWrapper.load(StandardWrapper.java:983)
	at org.apache.catalina.core.StandardContext.loadOnStartup(StandardContext.java:4871)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5180)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1384)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1374)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75)
	at java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:134)
	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:909)
	at org.apache.catalina.core.StandardHost.startInternal(StandardHost.java:841)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1384)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1374)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75)
	at java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:134)
	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:909)
	at org.apache.catalina.core.StandardEngine.startInternal(StandardEngine.java:262)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.StandardService.startInternal(StandardService.java:421)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.StandardServer.startInternal(StandardServer.java:930)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:633)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:343)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:474)
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name    org.springframework.web.servlet.handler.MappedInterceptor#1   : Could not resolve matching constructor (hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:250)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1051)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:955)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:490)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:461)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:295)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:223)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:198)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeansOfType(DefaultListableBeanFactory.java:439)
	at org.springframework.context.support.AbstractApplicationContext.getBeansOfType(AbstractApplicationContext.java:1188)
	at org.springframework.beans.factory.BeanFactoryUtils.beansOfTypeIncludingAncestors(BeanFactoryUtils.java:277)
	at org.springframework.web.servlet.handler.AbstractHandlerMapping.detectMappedInterceptors(AbstractHandlerMapping.java:221)
	at org.springframework.web.servlet.handler.AbstractHandlerMapping.initApplicationContext(AbstractHandlerMapping.java:196)
	at org.springframework.web.servlet.handler.SimpleUrlHandlerMapping.initApplicationContext(SimpleUrlHandlerMapping.java:103)
	at org.springframework.context.support.ApplicationObjectSupport.initApplicationContext(ApplicationObjectSupport.java:119)
	at org.springframework.web.context.support.WebApplicationObjectSupport.initApplicationContext(WebApplicationObjectSupport.java:72)
	at org.springframework.context.support.ApplicationObjectSupport.setApplicationContext(ApplicationObjectSupport.java:73)
	at org.springframework.context.support.ApplicationContextAwareProcessor.invokeAwareInterfaces(ApplicationContextAwareProcessor.java:117)
	at org.springframework.context.support.ApplicationContextAwareProcessor.postProcessBeforeInitialization(ApplicationContextAwareProcessor.java:92)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:399)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1481)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:524)
	... 48 more

四月 05, 2021 6:23:36 下午 org.apache.catalina.core.StandardContext loadOnStartup
严重: Servlet [SpringMVC] in web application [/subscribe] threw load() exception
org.springframework.beans.factory.BeanCreationException: Error creating bean with name    org.springframework.web.servlet.handler.MappedInterceptor#1   : Could not resolve matching constructor (hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:250)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1051)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:955)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:490)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:461)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:295)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:223)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:198)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeansOfType(DefaultListableBeanFactory.java:439)
	at org.springframework.context.support.AbstractApplicationContext.getBeansOfType(AbstractApplicationContext.java:1188)
	at org.springframework.beans.factory.BeanFactoryUtils.beansOfTypeIncludingAncestors(BeanFactoryUtils.java:277)
	at org.springframework.web.servlet.handler.AbstractHandlerMapping.detectMappedInterceptors(AbstractHandlerMapping.java:221)
	at org.springframework.web.servlet.handler.AbstractHandlerMapping.initApplicationContext(AbstractHandlerMapping.java:196)
	at org.springframework.web.servlet.handler.SimpleUrlHandlerMapping.initApplicationContext(SimpleUrlHandlerMapping.java:103)
	at org.springframework.context.support.ApplicationObjectSupport.initApplicationContext(ApplicationObjectSupport.java:119)
	at org.springframework.web.context.support.WebApplicationObjectSupport.initApplicationContext(WebApplicationObjectSupport.java:72)
	at org.springframework.context.support.ApplicationObjectSupport.setApplicationContext(ApplicationObjectSupport.java:73)
	at org.springframework.context.support.ApplicationContextAwareProcessor.invokeAwareInterfaces(ApplicationContextAwareProcessor.java:117)
	at org.springframework.context.support.ApplicationContextAwareProcessor.postProcessBeforeInitialization(ApplicationContextAwareProcessor.java:92)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:399)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1481)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:524)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:461)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:295)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:223)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:194)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:626)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:932)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:479)
	at org.springframework.web.servlet.FrameworkServlet.configureAndRefreshWebApplicationContext(FrameworkServlet.java:651)
	at org.springframework.web.servlet.FrameworkServlet.createWebApplicationContext(FrameworkServlet.java:599)
	at org.springframework.web.servlet.FrameworkServlet.createWebApplicationContext(FrameworkServlet.java:665)
	at org.springframework.web.servlet.FrameworkServlet.initWebApplicationContext(FrameworkServlet.java:518)
	at org.springframework.web.servlet.FrameworkServlet.initServletBean(FrameworkServlet.java:459)
	at org.springframework.web.servlet.HttpServletBean.init(HttpServletBean.java:136)
	at javax.servlet.GenericServlet.init(GenericServlet.java:158)
	at org.apache.catalina.core.StandardWrapper.initServlet(StandardWrapper.java:1134)
	at org.apache.catalina.core.StandardWrapper.loadServlet(StandardWrapper.java:1089)
	at org.apache.catalina.core.StandardWrapper.load(StandardWrapper.java:983)
	at org.apache.catalina.core.StandardContext.loadOnStartup(StandardContext.java:4871)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5180)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1384)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1374)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75)
	at java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:134)
	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:909)
	at org.apache.catalina.core.StandardHost.startInternal(StandardHost.java:841)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1384)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1374)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75)
	at java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:134)
	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:909)
	at org.apache.catalina.core.StandardEngine.startInternal(StandardEngine.java:262)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.StandardService.startInternal(StandardService.java:421)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.StandardServer.startInternal(StandardServer.java:930)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:633)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:343)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:474)
----------------
 Error creating bean with name    org.springframework.web.servlet.handler.MappedInterceptor#1   : Could not resolve matching constructor (hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)	
--------------
<appender name="activexAppender"
		class="org.apache.log4j.DailyRollingFileAppender">
		<param name="File" value="./logs/activex.log" />
		<param name="DatePattern" value="   .   yyyy-MM-dd   .log   " />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern"
				value="[%d{yyyy-MM-dd HH:mm:ss:SSS}][%p]-%m-%l%n" />
		</layout>
	</appender>
	<!-- 指定logger的设置，additivity指示是否遵循缺省的继承机制 -->
	<logger name="com.computer.subscribe" level="debug"
		additivity="false">
		<appender-ref ref="activexAppender" />
	</logger>

-----------
[2021-04-05 22:13:09:763] [INFO] - Mapped URL path [/**] onto handler    org.springframework.web.servlet.resource.ResourceHttpRequestHandler#0    - org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.registerHandler(AbstractUrlHandlerMapping.java:315)
[2021-04-05 22:13:09:784] [INFO] - Mapped URL path [/**] onto handler    org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler#0    - org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.registerHandler(AbstractUrlHandlerMapping.java:315)
[2021-04-05 22:13:09:875] [INFO] - HV000001: Hibernate Validator 5.2.0.Final - org.hibernate.validator.internal.util.Version.<clinit>(Version.java:17)
[2021-04-05 22:13:10:544] [INFO] - Detected @ExceptionHandler methods in basicController - org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver.initExceptionHandlerAdviceCache(ExceptionHandlerExceptionResolver.java:294)
[2021-04-05 22:13:10:636] [INFO] - FrameworkServlet  SpringMVC : initialization completed in 2931 ms - org.springframework.web.servlet.FrameworkServlet.initServletBean(FrameworkServlet.java:473)
-----------------------
//**/
[2021-04-06 00:56:23:157] [DEBUG] - Cannot resolve template loader path [/WEB-INF/pages/] to [java.io.File]: using SpringTemplateLoader as fallback - org.springframework.ui.freemarker.FreeMarkerConfigurationFactory.getTemplateLoaderForPath(FreeMarkerConfigurationFactory.java:363)
java.io.FileNotFoundException: /home/user/admin/workspaces/java-engineering/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/subscribe/WEB-INF/pages does not exist.
-----------
java.io.FileNotFoundException: /home/user/admin/workspaces/java-engineering/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/subscribe/WEB-INF/pages does not exist	
-----------
----
------
org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:236)] [Did not find handler method for [/UsersController/registerAction
----
[UsersController/registerAction] against base location: ServletContext resource [/]] 
-------
[DEBUG]: [org.springframework.web.servlet.resource.ResourceHttpRequestHandler][handleRequest][org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:115)] [No matching resource found - returning 404] 
----
----
----
[DEBUG]:[org.springframework.web.servlet.DispatcherServlet][doService]    [org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:823)] [DispatcherServlet with name  SpringMVC  processing GET request for [/subscribe/UsersController/registerAction]] 
[2021-04-06 13:16:27:649] [DEBUG] - DispatcherServlet with name  SpringMVC  processing GET request for [/subscribe/UsersController/registerAction] - org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:823)
[2021-04-06 13:16:27.660] [DEBUG]:[org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping][getHandlerInternal]    [org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:226)] [Looking up handler method for path /UsersController/registerAction] 
[2021-04-06 13:16:27:660] [DEBUG] - Looking up handler method for path /UsersController/registerAction - org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:226)
[2021-04-06 13:16:27.661] [DEBUG]:[org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping][getHandlerInternal]    [org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:236)] [Did not find handler method for [/UsersController/registerAction]] 
[2021-04-06 13:16:27:661] [DEBUG] - Did not find handler method for [/UsersController/registerAction] - org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:236)
[2021-04-06 13:16:27.663] [DEBUG]:[org.springframework.web.servlet.handler.SimpleUrlHandlerMapping][lookupHandler]    [org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.lookupHandler(AbstractUrlHandlerMapping.java:169)] [Matching patterns for request [/UsersController/registerAction] are [/**]] 
[2021-04-06 13:16:27:663] [DEBUG] - Matching patterns for request [/UsersController/registerAction] are [/**] - org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.lookupHandler(AbstractUrlHandlerMapping.java:169)
[2021-04-06 13:16:27.664] [DEBUG]:[org.springframework.web.servlet.handler.SimpleUrlHandlerMapping][lookupHandler]    [org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.lookupHandler(AbstractUrlHandlerMapping.java:194)] [URI Template variables for request [/UsersController/registerAction] are {}] 
[2021-04-06 13:16:27:664] [DEBUG] - URI Template variables for request [/UsersController/registerAction] are {} - org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.lookupHandler(AbstractUrlHandlerMapping.java:194)
[2021-04-06 13:16:27.666] [DEBUG]:[org.springframework.web.servlet.handler.SimpleUrlHandlerMapping][getHandlerInternal]    [org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.getHandlerInternal(AbstractUrlHandlerMapping.java:124)] [Mapping [/UsersController/registerAction] to HandlerExecutionChain with handler [org.springframework.web.servlet.resource.ResourceHttpRequestHandler@4d7be2d] and 1 interceptor] 
[2021-04-06 13:16:27:666] [DEBUG] - Mapping [/UsersController/registerAction] to HandlerExecutionChain with handler [org.springframework.web.servlet.resource.ResourceHttpRequestHandler@4d7be2d] and 1 interceptor - org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.getHandlerInternal(AbstractUrlHandlerMapping.java:124)
[2021-04-06 13:16:27.667] [DEBUG]:[org.springframework.web.servlet.DispatcherServlet][doDispatch]    [org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:912)] [Last-Modified value for [/subscribe/UsersController/registerAction] is: -1] 
[2021-04-06 13:16:27:667] [DEBUG] - Last-Modified value for [/subscribe/UsersController/registerAction] is: -1 - org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:912)
[2021-04-06 13:16:27.669] [DEBUG]:[org.springframework.web.servlet.resource.ResourceHttpRequestHandler][getResource]    [org.springframework.web.servlet.resource.ResourceHttpRequestHandler.getResource(ResourceHttpRequestHandler.java:165)] [Trying relative path [UsersController/registerAction] against base location: ServletContext resource [/]] 
[2021-04-06 13:16:27:669] [DEBUG] - Trying relative path [UsersController/registerAction] against base location: ServletContext resource [/] - org.springframework.web.servlet.resource.ResourceHttpRequestHandler.getResource(ResourceHttpRequestHandler.java:165)
[2021-04-06 13:16:27.670] [DEBUG]:[org.springframework.web.servlet.resource.ResourceHttpRequestHandler][handleRequest]    [org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:115)] [No matching resource found - returning 404] 
[2021-04-06 13:16:27:670] [DEBUG] - No matching resource found - returning 404 - org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:115)
[2021-04-06 13:16:27.671] [DEBUG]:[org.springframework.web.servlet.DispatcherServlet][processDispatchResult]    [org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:999)] [Null ModelAndView returned to DispatcherServlet with name  SpringMVC : assuming HandlerAdapter completed request handling] 
[2021-04-06 13:16:27:671] [DEBUG] - Null ModelAndView returned to DispatcherServlet with name  SpringMVC : assuming HandlerAdapter completed request handling - org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:999)
[2021-04-06 13:16:27.671] [DEBUG]:[org.springframework.web.servlet.DispatcherServlet][processRequest]    [org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:966)] [Successfully completed request] 
[2021-04-06 13:16:27:671] [DEBUG] - Successfully completed request - org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:966)
[2021-04-06 13:16:27.672] [DEBUG]:[org.springframework.beans.factory.support.DefaultListableBeanFactory][doGetBean]    [org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:246)] [Returning cached instance of singleton bean    org.mybatis.spring.SqlSessionFactoryBean#0   ] 
[2021-04-06 13:16:27:672] [DEBUG] - Returning cached instance of singleton bean    org.mybatis.spring.SqlSessionFactoryBean#0    - org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:246)
--------------***/

[2021-04-06 13:15:20.179] [DEBUG]: [org.springframework.web.servlet.DispatcherServlet][init][org.springframework.web.servlet.HttpServletBean.init(HttpServletBean.java:139)] [Servlet  SpringMVC  configured successfully] 
[2021-04-06 13:16:27.649] [DEBUG]: [org.springframework.web.servlet.DispatcherServlet][doService][org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:823)] [DispatcherServlet with name  SpringMVC  processing GET request for [/subscribe/UsersController/registerAction]] 
[2021-04-06 13:16:27.660] [DEBUG]: [org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping][getHandlerInternal][org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:226)] [Looking up handler method for path /UsersController/registerAction] 
[2021-04-06 13:16:27.661] [DEBUG]: [org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping][getHandlerInternal][org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:236)] [Did not find handler method for [/UsersController/registerAction]] 
[2021-04-06 13:16:27.663] [DEBUG]: [org.springframework.web.servlet.handler.SimpleUrlHandlerMapping][lookupHandler][org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.lookupHandler(AbstractUrlHandlerMapping.java:169)] [Matching patterns for request [/UsersController/registerAction] are [/**]] 
[2021-04-06 13:16:27.664] [DEBUG]: [org.springframework.web.servlet.handler.SimpleUrlHandlerMapping][lookupHandler][org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.lookupHandler(AbstractUrlHandlerMapping.java:194)] [URI Template variables for request [/UsersController/registerAction] are {}] 
[2021-04-06 13:16:27.666] [DEBUG]: [org.springframework.web.servlet.handler.SimpleUrlHandlerMapping][getHandlerInternal][org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.getHandlerInternal(AbstractUrlHandlerMapping.java:124)] [Mapping [/UsersController/registerAction] to HandlerExecutionChain with handler [org.springframework.web.servlet.resource.ResourceHttpRequestHandler@4d7be2d] and 1 interceptor] 
[2021-04-06 13:16:27.667] [DEBUG]: [org.springframework.web.servlet.DispatcherServlet][doDispatch][org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:912)] [Last-Modified value for [/subscribe/UsersController/registerAction] is: -1] 
[2021-04-06 13:16:27.669] [DEBUG]: [org.springframework.web.servlet.resource.ResourceHttpRequestHandler][getResource][org.springframework.web.servlet.resource.ResourceHttpRequestHandler.getResource(ResourceHttpRequestHandler.java:165)] [Trying relative path [UsersController/registerAction] against base location: ServletContext resource [/]] 
[2021-04-06 13:16:27.670] [DEBUG]: [org.springframework.web.servlet.resource.ResourceHttpRequestHandler][handleRequest][org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:115)] [No matching resource found - returning 404] 
[2021-04-06 13:16:27.671] [DEBUG]: [org.springframework.web.servlet.DispatcherServlet][processDispatchResult][org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:999)] [Null ModelAndView returned to DispatcherServlet with name  SpringMVC : assuming HandlerAdapter completed request handling] 
[2021-04-06 13:16:27.671] [DEBUG]: [org.springframework.web.servlet.DispatcherServlet][processRequest][org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:966)] [Successfully completed request] 
[2021-04-06 13:16:27.672] [DEBUG]: [org.springframework.beans.factory.support.DefaultListableBeanFactory][doGetBean][org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:246)] [Returning cached instance of singleton bean    org.mybatis.spring.SqlSessionFactoryBean#0   ]

--------------**/
[2021-04-06 13:16:27:670] [DEBUG] - No matching resource found - returning 404 - org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:115)
-----------------------
1

For me, I had a typo in the Spring config file which points to the package:

Was:

<context:component-scan base-package="com.something.web.controlers" />
Fixed with correct spelling:

<context:component-scan base-package="com.something.web.controllers" />

-------------------------
1

ControllerMain.java requires a @RequestMapping("/") statement after the @Controller.

Change:

@Controller
public class ControllerMain {

@RequestMapping(value = "/hello", method = RequestMethod.GET)
....
....
To:

@Controller
@RequestMapping("/")
public class ControllerMain {

@RequestMapping(value = "/hello", method = RequestMethod.GET)
....
....
....
<context:exclude-filter type="annotation"
			expression="org.springframework.stereotype.Controller" />
----------------
[org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.registerHandlerMethod(AbstractHandlerMethodMapping.java:185)] [Mapped "{[/UsersController/registerAction],methods=[GET],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> 			
------------------
HTTP Status 415 – 不支持的媒体类型
Type Status Report

描述 源服务器拒绝服务请求，因为有效负载的格式在目标资源上此方法不支持。

---------
[Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.web.HttpMediaTypeNotSupportedException: Content type application/octet-stream not supported] 
----------------

--------------
Content type    null    not supported - org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)

-----------
Manifest-Version: 1.0
Created-By: 1.7.0_21 (Oracle Corporation)
Implementation-Title: spring-expression
Implementation-Version: 3.2.4.RELEASE
---------
[2021-04-06 20:38:13.460] [DEBUG]:[org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver][resolveException]    [org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)] [Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.web.HttpMediaTypeNotSupportedException: 
Content type    application/octet-stream    not supported]
-*------------


严重: Servlet.service() for servlet [SpringMVC] in context with path [/subscribe] threw exception [Request processing failed; nested exception is javax.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint    org.hibernate.validator.constraints.NotBlank    validating type    java.lang.Integer   . Check configuration for    role   ] with root cause
javax.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint    org.hibernate.validator.constraints.NotBlank    validating type    java.lang.Integer   . Check configuration for    role   
	at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.throwExceptionForNullValidator(ConstraintTree.java:229)
	at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.getConstraintValidatorNoUnwrapping(ConstraintTree.java:310)
	at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.getConstraintValidatorInstanceForAutomaticUnwrapping(ConstraintTree.java:244)
	at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.getInitializedConstraintValidator(ConstraintTree.java:163)
	at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateConstraints(ConstraintTree.java:116)
	at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateConstraints(ConstraintTree.java:87)
	at org.hibernate.validator.internal.metadata.core.MetaConstraint.validateConstraint(MetaConstraint.java:73)
	at org.hibernate.validator.internal.engine.ValidatorImpl.validateMetaConstraint(ValidatorImpl.java:592)
	at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraint(ValidatorImpl.java:555)
	at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForDefaultGroup(ValidatorImpl.java:490)
	at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForCurrentGroup(ValidatorImpl.java:454)
	at org.hibernate.validator.internal.engine.ValidatorImpl.validateInContext(ValidatorImpl.java:406)
	at org.hibernate.validator.internal.engine.ValidatorImpl.validate(ValidatorImpl.java:204)
	at org.springframework.validation.beanvalidation.SpringValidatorAdapter.validate(SpringValidatorAdapter.java:102)
	at org.springframework.validation.DataBinder.validate(DataBinder.java:772)
	at org.springframework.web.method.annotation.ModelAttributeMethodProcessor.validateIfApplicable(ModelAttributeMethodProcessor.java:159)
	at org.springframework.web.method.annotation.ModelAttributeMethodProcessor.resolveArgument(ModelAttributeMethodProcessor.java:107)
	at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:77)
	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:162)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:123)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:745)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:686)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:80)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:925)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:856)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:936)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:827)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:812)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:526)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:678)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:367)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:860)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1591)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

-----------
javax.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint    org.hibernate.validator.constraints.NotBlank    validating type    java.lang.Integer   
--------------------
[org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver][resolveException]    [org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)] [Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 2 errors
Field error in object )TUser) on field )mailbox): rejected value [null]; codes [NotBlank.TUser.mailbox,NotBlank.mailbox,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.mailbox,mailbox]; arguments []; default message [mailbox]]; default message [联系邮箱不能为空]
Field error in object )TUser) on field )password): rejected value [null]; codes [NotBlank.TUser.password,NotBlank.password,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.password,password]; arguments []; default message [password]]; default message [密码不能为空]] 
[2021-04-07 00:37:17:305] [DEBUG] - Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 2 errors
Field error in object )TUser) on field )mailbox): rejected value [null]; codes [NotBlank.TUser.mailbox,NotBlank.mailbox,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.mailbox,mailbox]; arguments []; default message [mailbox]]; default message [联系邮箱不能为空]
Field error in object )TUser) on field )password): rejected value [null]; codes [NotBlank.TUser.password,NotBlank.password,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.password,password]; arguments []; default message [password]]; default message [密码不能为空] - org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)
[2021-04-07 00:37:17.305] [DEBUG]:[org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver][resolveException]    [org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)] [Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 2 errors
Field error in object )TUser) on field )mailbox): rejected value [null]; codes [NotBlank.TUser.mailbox,NotBlank.mailbox,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.mailbox,mailbox]; arguments []; default message [mailbox]]; default message [联系邮箱不能为空]
Field error in object )TUser) on field )password): rejected value [null]; codes [NotBlank.TUser.password,NotBlank.password,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.password,password]; arguments []; default message [password]]; default message [密码不能为空]] 
[2021-04-07 00:37:17:305] [DEBUG] - Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 2 errors
Field error in object )TUser) on field )mailbox): rejected value [null]; codes [NotBlank.TUser.mailbox,NotBlank.mailbox,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.mailbox,mailbox]; arguments []; default message [mailbox]]; default message [联系邮箱不能为空]
Field error in object )TUser) on field )password): rejected value [null]; codes [NotBlank.TUser.password,NotBlank.password,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.password,password]; arguments []; default message [password]]; default message [密码不能为空] - org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)
[2021-04-07 00:37:17.305] [DEBUG]:[org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver][resolveException]    [org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)] [Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 2 errors
Field error in object )TUser) on field )mailbox): rejected value [null]; codes [NotBlank.TUser.mailbox,NotBlank.mailbox,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.mailbox,mailbox]; arguments []; default message [mailbox]]; default message [联系邮箱不能为空]
Field error in object )TUser) on field )password): rejected value [null]; codes [NotBlank.TUser.password,NotBlank.password,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.password,password]; arguments []; default message [password]]; default message [密码不能为空]] 
[2021-04-07 00:37:17:305] [DEBUG] - Resolving exception from handler [public com.computer.subscribe.pojo.response.WebResponse<java.lang.Integer> com.computer.subscribe.controller.UsersController.registerAction(com.computer.subscribe.pojo.TUser) throws com.computer.subscribe.exception.OperationException]: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 2 errors
Field error in object )TUser) on field )mailbox): rejected value [null]; codes [NotBlank.TUser.mailbox,NotBlank.mailbox,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.mailbox,mailbox]; arguments []; default message [mailbox]]; default message [联系邮箱不能为空]
Field error in object )TUser) on field )password): rejected value [null]; codes [NotBlank.TUser.password,NotBlank.password,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [TUser.password,password]; arguments []; default message [password]]; default message [密码不能为空] - org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:132)
[2021-04-07 00:37:17.306] [DEBUG]:[org.springframework.web.servlet.DispatcherServlet][processDispatchResult]    [org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:999)] [Null ModelAndView returned to DispatcherServlet with name )SpringMVC): assuming HandlerAdapter completed request handling] 
[2021-04-07 00:37:17:306] [DEBUG] - Null ModelAndView returned to DispatcherServlet with name )SpringMVC): assuming HandlerAdapter completed request handling - org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:999)
[2021-04-07 00:37:17.306] [DEBUG]:[org.springframework.web.servlet.DispatcherServlet][processRequest]    [org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:966)] [Successfully completed request] 
[2021-04-07 00:37:17:306] [DEBUG] - Successfully completed request - org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:966)
[2021-04-07 00:37:17.307] [DEBUG]:[org.springframework.beans.factory.support.DefaultListableBeanFactory][doGetBean]    [org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:246)] [Returning cached instance of singleton bean )org.mybatis.spring.SqlSessionFactoryBean#0)] 
[2021-04-07 00:37:17:307] [DEBUG] - Returning cached instance of singleton bean )org.mybatis.spring.SqlSessionFactoryBean#0) - org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:246)
