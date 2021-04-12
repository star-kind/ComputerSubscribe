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
	 * 判断某个用户是否存在,是否为指定类型的帐户<br>
	 * <ul>
	 * 参数:
	 * <li>学号/工号;</li>
	 * <li>指定的角色权限码</li>
	 * </ul>
	 */
	TUser checkAccountIsRight(@NotNull Long userNum, @NotNull Integer roleCode)
			throws OperationException;

	/**
	 * 检查某个用户是否存在,若是存在则返回对象
	 * 
	 * @param userNum
	 * @return
	 */
	TUser checkUserExist(@NotNull Long userNum) throws OperationException;

	/**
	 * 校验管理员的存在与权限
	 * 
	 * @param adminNum
	 * @throws OperationException
	 */
	void checkAdminPrivilege(@NotNull Long adminNum) throws OperationException;

	/**
	 * 
	 * @param userNum
	 * @param adminNum 管理员的工号
	 * @return
	 * @throws OperationException
	 */
	TUser getUserByUserNum(@NotNull Long userNum, @NotNull Long adminNum)
			throws OperationException;

	/**
	 * 修改用户资料,只能由管理员进行修改<br>
	 * 基于user number(工号)-管理员; 可修改字段: 电话,邮箱,用户名<br>
	 * 
	 * <b>管理员之间不能互相修改</b>
	 * 
	 * @param userName
	 * @param mailbox
	 * @param phone
	 * @param userNum  被修改对象的user numer
	 * @param adminNum
	 * @return
	 * @throws OperationException
	 */
	Integer modifyUserInfoByAdminNum(@NotNull String userName,
			@NotNull String mailbox, @NotNull String phone, @NotNull Long userNum,
			@NotNull Long adminNum) throws OperationException;

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
