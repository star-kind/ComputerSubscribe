package com.computer.subscribe.service;

import java.util.List;

import javax.validation.constraints.NotNull;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.response.Pagination;

/**
 * 用户业务接口
 * 
 * @author user
 *
 */
public interface IUserService {
	/**
	 * 统计获取ID的数量
	 * 
	 * @return
	 * @throws OperationException
	 */
	Integer getIdCount() throws OperationException;

	/**
	 * 获取所有用户
	 * 
	 * @return
	 * @throws OperationException
	 */
	List<TUser> getAllUsers() throws OperationException;

	/**
	 * 分页查询(适合在数据量不大的情况下)
	 * 
	 * @param pageNum 页码
	 * @param limit   行数
	 * @return
	 * @throws OperationException
	 */
	List<TUser> getUserListByLimits(@NotNull Integer pageNum, @NotNull Integer limit)
			throws OperationException;

	/**
	 * 分页获取全体帐号的资料
	 * 
	 * @param pageOrder 页码
	 * @param pageRows  每页多少人,每页行数
	 * @param id        查询者的id
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TUser>> getMembersListByOrder(@NotNull Integer pageOrder,
			@NotNull Integer pageRows, @NotNull Integer id)
			throws OperationException;

	/**
	 * 改变密码
	 * 
	 * @param newPwd
	 * @param oldPwd
	 * @param userNum
	 * @return
	 * @throws OperationException
	 */
	Integer revisePassword(@NotNull String newPwd, @NotNull String oldPwd,
			@NotNull Long userNum) throws OperationException;

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
