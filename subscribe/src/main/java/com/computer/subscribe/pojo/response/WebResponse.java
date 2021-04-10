package com.computer.subscribe.pojo.response;

import java.util.List;

import com.computer.subscribe.pojo.TUser;

/**
 * 响应实体类
 * 
 * @author user
 * @param <T>
 *
 */
public class WebResponse<T> {
	private Integer code;
	private String message;
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public WebResponse(Integer code, T data) {
		this.code = code;
		this.data = data;
	}

	public WebResponse(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public WebResponse(Integer success, String string) {
	}

	public WebResponse() {
	}

	public WebResponse(Integer code, Pagination<List<TUser>> pagination) {
		this.code = code;
		this.data = (T) pagination;
	}

	@Override
	public String toString() {
		return "WebResponse [code=" + code + ", message=" + message + ", data="
				+ data + "]";
	}

}
