package com.computer.subscribe.service;

import javax.validation.constraints.NotNull;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;

/**
 * 用户业务接口
 * 
 * @author user
 *
 */
public interface IUserService {
	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 * @throws OperationException
	 */
	Integer regist(@NotNull TUser user) throws OperationException;

	/**
	 * 登录
	 * 
	 * @param userNum
	 * @param passwd
	 * @param role
	 * @return
	 * @throws OperationException
	 */
	LoginData login(@NotNull long userNum, @NotNull String passwd,
			@NotNull Integer role) throws OperationException;
}
