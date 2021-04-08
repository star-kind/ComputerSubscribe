package com.computer.subscribe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档设置
 * 
 * @author user
 *
 */

@EnableSwagger2 // 启动swagger
@EnableWebMvc
@ComponentScan(basePackages = { "com.computer.subscribe.config" })
@Configuration // 让spring加载该类
public class SwaggerConfigur extends WebMvcConfigurationSupport {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors
						.basePackage("com.computer.subscribe.controller"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("机房预约系统")// 标题
				.description("后台接口文档")// 描述
				.version("0.0.1")// 版本号
				.build();
	}
}