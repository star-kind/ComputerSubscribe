package com.computer.subscribe.service;

import java.util.List;

import javax.validation.Valid;
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
	 * 检验用户属性是否全部为空<br>
	 * 效力用途:修改用<br>
	 * 被查属性:用户名,电话,邮箱,角色
	 * 
	 * @param user
	 * @throws OperationException
	 */
	void validAttrsAreAllNull(TUser user) throws OperationException;

	/**
	 * 获取经过精心构建的,作修改用途的用户参数实体
	 * 
	 * @param oldTblUser
	 * @param submitUpdatedUser
	 * @return
	 */
	TUser getUserBeanForBuild(@Valid TUser oldTblUser,
			@Valid TUser submitUpdatedUser) throws OperationException;

	/**
	 * 阻止其他任何人修改某位管理员的信息
	 * 
	 * @param adminAccount
	 * @return
	 * @throws OperationException
	 */
	void stopAdminUpdatedByOther(TUser adminAccount) throws OperationException;

	/**
	 * 检查是否是学生角色,若是则报错,若不是则返还[教师/管理员]实体
	 * 
	 * @param userNum
	 * @return
	 * @throws OperationException
	 */
	TUser checkBanStudent(@NotNull Long userNum) throws OperationException;

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
	 * 检查某个用户是否存在,若是存在则返回对象[据学号/工号]
	 * 
	 * @param userNum
	 * @return
	 */
	TUser checkUserExist(@NotNull Long userNum) throws OperationException;

	/**
	 * 检查某个用户是否存在,若是存在则返回对象,不存在则报错[据用户ID]<br>
	 * 密文+密钥不返回
	 * 
	 * @param userID
	 * @return
	 */
	TUser checkUserByIdIfExist(@NotNull Integer userID) throws OperationException;

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
	 * 根据工号/学号获取用户数据(全部,含密文+盐值)
	 * 
	 * @param userNum
	 * @return
	 * @throws OperationException
	 */
	TUser getUserByUserNum(@Valid @NotNull Long userNum) throws OperationException;

	/**
	 * <b>管理员之间不能互相修改</b> <br>
	 * 修改用户资料,只能由管理员进行修改<br>
	 * 基于[工号]-管理员+被修改对象的ID;
	 * <ol>
	 * 可修改字段:
	 * <li>电话</li>
	 * <li>邮箱</li>
	 * <li>用户名</li>
	 * <li>角色权限</li>
	 * </ol>
	 * 
	 * @param submitUpdatedUser 对被修改帐户提交的的新信息
	 * @param adminNum
	 * @return
	 * @throws OperationException
	 */
	TUser modifyUserInfoByAdminNum(@NotNull TUser submitUpdatedUser,
			@Valid @NotNull Long adminNum) throws OperationException;

	/**
	 * 普通用户(师生)修改自己的基本资料<br>
	 * 基于[ID]-普通师生自身的; 可修改字段: 电话,邮箱,用户名<br>
	 * 
	 * @param submitUser
	 * @return
	 * @throws OperationException
	 */
	TUser modifyInfoByNormalUser(@Valid @NotNull TUser submitUser)
			throws OperationException;

	/**
	 * 管理员修改自己的资料;可修改:电话,邮箱,用户名,角色权限 <br>
	 * 基于帐号ID
	 * 
	 * @param admin
	 * @return
	 * @throws OperationException
	 */
	TUser modifyInfoByAdminMySelf(@Valid @NotNull TUser admin)
			throws OperationException;

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
	 * @param id        查询者的id(必须是管理员)
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TUser>> getMembersListByOrder(@NotNull Integer pageOrder,
			@NotNull Integer pageRows, @NotNull Integer id)
			throws OperationException;

	/**
	 * 根据用户ID获取用户数据[含密文+密钥]
	 * 
	 * @param id
	 * @return
	 * @throws OperationException
	 */
	TUser getUserById(@Valid @NotNull Integer id) throws OperationException;

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
