package com.computer.subscribe.controller.ex;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.controller.BasicController;
import com.computer.subscribe.exception.AttributeNPException;
import com.computer.subscribe.exception.OffcialExEnum;
import com.computer.subscribe.pojo.response.WebResponse;

/**
 * 接口参数校验之异常返还处理
 * 
 * @author user
 *
 */
@ControllerAdvice
public class ValidationExceptionshandler extends BasicController {
	String ts = this.getClass().getName() + "_____\n";

	/**
	 * 方法参数校验:BindException<br>
	 * <b>如果参数校验注解检测到异常,则返还到前台</b>
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public WebResponse<Void> handleBindException(BindException e) {
		System.err.println(ts + "handleBindException" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(OffcialExEnum.BIND_EX.getStatus());
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
		System.err.println(ts + "handleMethodArgumentNotValidException" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(OffcialExEnum.METHOD_ARGUMENT_NOT_VALID_EX.getStatus());
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
		System.err.println(ts + "handleValidationException\n" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(OffcialExEnum.VALIDATION_EX.getStatus());
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
		System.err.println(ts + "handleConstraintViolationException\n__" + e);
		logger.error(e.getMessage(), e);

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(OffcialExEnum.CONSTRAINT_VIOLATION_EX.getStatus());
		response.setMessage(e.getCause().getMessage());
		return response;
	}

	/**
	 * 实体类属性空指针异常之控制层处理
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(AttributeNPException.class)
	public WebResponse<Void> handleAttributeNPEx(AttributeNPException ex) {
		System.err.println(ts + "--handleAttributeNPEx--" + ex);
		logger.error(ex.getLocalizedMessage());

		WebResponse<Void> response = new WebResponse<Void>();

		response.setCode(OffcialExEnum.ATTRIBUTE_NP_EX.getStatus());
		response.setMessage(ex.getLocalizedMessage());
		return response;
	}
	
}
