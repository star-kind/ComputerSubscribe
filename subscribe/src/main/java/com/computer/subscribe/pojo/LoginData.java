package com.computer.subscribe.pojo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 专门为用户登录所建的对象
 * 
 * @author user
 *
 */
@Data
@NoArgsConstructor
public class LoginData extends TUser {
	@NonNull
	@NotBlank(message = "Token字符串不能为空")
	private String token;

	@Override
	public String toString() {
		return "LoginData [token=" + token + ", toString()=" + super.toString()
				+ "]";
	}

}
