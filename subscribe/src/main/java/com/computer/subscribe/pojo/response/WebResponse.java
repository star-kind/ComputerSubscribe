package com.computer.subscribe.pojo.response;

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

	public WebResponse(Integer code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public WebResponse(Integer success, String string) {
	}

	public WebResponse() {
	}

	@Override
	public String toString() {
		return "WebResponse [code=" + code + ", message=" + message + ", data="
				+ data + "]";
	}

}
