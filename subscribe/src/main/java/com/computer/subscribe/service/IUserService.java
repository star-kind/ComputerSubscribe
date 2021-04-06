package com.computer.subscribe.service;

import javax.validation.constraints.NotNull;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.TUser;

/**
 * 用户业务接口
 * 
 * @author user
 *
 */
public interface IUserService {
	// 注册
	Integer regist(@NotNull TUser user) throws OperationException;
}
