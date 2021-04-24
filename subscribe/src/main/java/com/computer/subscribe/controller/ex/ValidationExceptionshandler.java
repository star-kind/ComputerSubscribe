package com.computer.subscribe.controller.ex;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.controller.BasicController;
import com.computer.subscribe.pojo.response.WebResponse;

/**
 * 接口参数校验之异常返还处理
 * 
 * @author user
 *
 */
@ControllerAdvice
public class ValidationExceptionshandler extends BasicController {

	/**
	 * 方法参数校验:BindException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public WebResponse<Void> handleBindException(BindException e) {
		System.err.println(this.getClass() + "\n__handleBindException__" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(bind_ex_code);
		response.setMessage(
				e.getBindingResult().getFieldError().getDefaultMessage());
		return response;
	}

	/**
	 * 方法参数校验:MethodArgumentNotValidException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 * 
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public WebResponse<Void> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		System.err.println(this.getClass()
				+ "\n__handleMethodArgumentNotValidException\n__" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(method_argument_not_valid_ex_code);
		response.setMessage(
				e.getBindingResult().getFieldError().getDefaultMessage());
		return response;
	}

	/**
	 * 方法参数校验:ValidationException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 */
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	public WebResponse<Void> handleValidationException(ValidationException e) {
		System.err
				.println(this.getClass() + "\n__handleValidationException\n__" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(validation_ex_code);
		response.setMessage(e.getCause().getMessage());
		return response;
	}

	/**
	 * 方法参数校验:ConstraintViolationException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 */
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public WebResponse<Void> handleConstraintViolationException(
			ConstraintViolationException e) {
		System.err.println(
				this.getClass() + "\n__handleConstraintViolationException\n__" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(constraint_violation_ex_code);
		response.setMessage(e.getCause().getMessage());
		return response;
	}
}
