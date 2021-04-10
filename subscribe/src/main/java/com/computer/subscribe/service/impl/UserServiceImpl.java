package com.computer.subscribe.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.mapper.TUserMapper;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.TUserExample;
import com.computer.subscribe.pojo.TUserExample.Criteria;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.service.IUserService;
import com.computer.subscribe.util.JwtUtils;
import com.computer.subscribe.util.PaginationUtils;
import com.computer.subscribe.util.account.PasswordBusiness;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private TUserMapper userMapper;

	public static Logger logger = Logger.getLogger(UserServiceImpl.class);

	PasswordBusiness pbBusiness = PasswordBusiness.getInstance();
	PaginationUtils paginationUtil = PaginationUtils.getInstance();

	@Override
	public Integer regist(TUser user) throws OperationException {
		logger.info(user.toString());

		// 防止NPE-空指针异常
		Optional<TUser> optionalUser = Optional.ofNullable(user);
		System.err.println(optionalUser);

		if (!optionalUser.isPresent()) {
			String description = ExceptionsEnum.REGIST_DATA_INCOMPLETE
					.getDescription();

			System.err.println(description);
			logger.error(description);
		}
		TUser tUser = optionalUser.get();
		System.err.println(tUser.toString());

		/* 检测 学号/电话/邮箱 是否唯一 */
		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();

		criteria.andUserNumEqualTo(user.getUserNum());
		List<TUser> list = userMapper.selectByExample(example);

		if (list.size() > 0) {
			String description = ExceptionsEnum.ACCOUNT_DUPLICATE_CONFLICT
					.getDescription();
			System.err.println(description);
			logger.error(description);

			throw new OperationException(description);
		}

		TUserExample example2 = new TUserExample();
		Criteria criteria2 = example2.createCriteria();

		criteria2.andPhoneEqualTo(user.getPhone());
		List<TUser> list2 = userMapper.selectByExample(example2);

		if (list2.size() > 0) {
			String description = ExceptionsEnum.PHONE_DUPLICATE_CONFLICT
					.getDescription();
			System.err.println(description);
			logger.error(description);

			throw new OperationException(description);
		}

		TUserExample example3 = new TUserExample();
		Criteria criteria3 = example3.createCriteria();

		criteria3.andMailboxEqualTo(user.getMailbox());
		List<TUser> list3 = userMapper.selectByExample(example3);

		if (list3.size() > 0) {
			String description = ExceptionsEnum.EMAIL_DUPLICATE_CONFLICT
					.getDescription();
			System.err.println(description);
			logger.error(description);

			throw new OperationException(description);
		}

		// 如果是注册新的管理员，检查管理员总数是否达到50人
		if (user.getRole() == 0) {
			TUserExample example4 = new TUserExample();
			Criteria criteria4 = example4.createCriteria();

			criteria4.andRoleEqualTo(0);
			List<TUser> list4 = userMapper.selectByExample(example4);

			if (list4.size() > 50) {
				String description = ExceptionsEnum.OUT_ADMIN_QUANTITY
						.getDescription();
				System.err.println(description);
				logger.error(description);

				throw new OperationException(description);
			}
		}

		// 盐值
		String salt = pbBusiness.extractSalt();
		System.err.println("盐值__salt===" + salt);
		user.setSalt(salt);

		String keyTxt = pbBusiness.generate(user.getPassword(), salt);
		System.err.println("密码__keyTxt===" + keyTxt);
		user.setPassword(keyTxt);

		int effect = userMapper.insert(user);
		return effect;
	}

	@Override
	public LoginData login(long userNum, String passwd, Integer role)
			throws OperationException {
		LoginData data = new LoginData();
		JwtUtils utils = new JwtUtils();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer minute = 600 * 10000;

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();

		/* 根据工号或者学号查看是否存在账户 */
		// 先构造条件
		criteria.andUserNumEqualTo(userNum);
		List<TUser> list = userMapper.selectByExample(example);

		if (list.isEmpty()) {
			String description = ExceptionsEnum.ACCOUNT_NO_EXIST.getDescription();
			logger.error(
					this.getClass().getSimpleName() + "--err=== " + description);
			System.err.println(description);
			throw new OperationException(description);
		}

		for (TUser tUser : list) {
			System.err.println(tUser.toString());
			System.out.println("User.Password== " + tUser.getPassword());
		}

		TUser tUser = list.get(0);
		/* 检验密码 */
		boolean verify = pbBusiness.verify(passwd, tUser.getPassword());
		// 比较密码
		if (!verify) {
			String description = ExceptionsEnum.LOGIN_PASSWORD_ERR.getDescription();
			logger.error(
					this.getClass().getSimpleName() + "--err=== " + description);
			System.err.println(description);
			throw new OperationException(description);
		}

		/* 检验登录所选之角色是否符合 */
		if (tUser.getRole() != role) {
			String description = ExceptionsEnum.ERR_TYPE_PRIVILEGE.getDescription();
			logger.error(
					this.getClass().getSimpleName() + "--err=== " + description);
			System.err.println(description);
			throw new OperationException(description);
		}

		map.put("userid", tUser.getId());
		map.put("usernum", tUser.getUserNum());
		map.put("mailbox", tUser.getMailbox());
		map.put("phone", tUser.getPhone());
		// 生成token,有效时间2小时
		String token = utils.encode(map, minute * 60 * 2);

		data.setId(tUser.getId());
		data.setUserNum(tUser.getUserNum());
		data.setUserName(tUser.getUserName());
		data.setPhone(tUser.getPhone());
		data.setMailbox(tUser.getMailbox());
		data.setRole(role);
		data.setToken(token);
		System.err.println(data.toString());
		return data;
	}

	@Override
	public Integer revisePassword(String newPwd, String oldPwd, Long userNum)
			throws OperationException {
		logger.info("new password== " + newPwd + ", old password== " + oldPwd
				+ ", user number==" + userNum);

		TUserExample userExample = new TUserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUserNumEqualTo(userNum);
		List<TUser> list = userMapper.selectByExample(userExample);

		if (list.isEmpty()) {
			String description = ExceptionsEnum.ACCOUNT_NO_EXIST.getDescription();
			logger.error(this.getClass().getName() + "__err== " + description);
			System.err.println(description);
			throw new OperationException(description);
		}

		for (TUser tUser : list) {
			System.err.println(tUser.toString());
		}
		TUser user = list.get(0);
		System.err.println("list[0].user== " + user);

		// 检验旧密码是否与表中密文一致
		boolean verify = pbBusiness.verify(oldPwd, user.getPassword());
		if (!verify) {
			String description = ExceptionsEnum.OLD_PASSWORD_ERR.getDescription();
			logger.error(this.getClass().getName() + "_OLD_PASSWORD_ERR_== "
					+ description);
			System.err.println(description);
			throw new OperationException(description);
		}

		// 通过检验,执行修改
		String newKeyText = pbBusiness.generate(newPwd, user.getSalt());
		System.err.println("newKeyText== " + newKeyText);

		TUser user2 = new TUser();
		user2.setPassword(newKeyText);

		int row = userMapper.updateByExampleSelective(user2, userExample);
		return row;
	}

	@Override
	public Pagination<List<TUser>> getMembersListByOrder(Integer pageOrder,
			Integer pageRows, Integer id) throws OperationException {
		System.err.println(
				"pageNO== " + pageOrder + ",pageSize== " + pageRows + ",ID== " + id);

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);

		List<TUser> list = userMapper.selectByExample(example);

		// 用户存在与否
		if (list.isEmpty()) {
			String description = ExceptionsEnum.ACCOUNT_NO_EXIST.getDescription();
			logger.error(this.getClass().getName() + "__ACCOUNT_NO_EXIST__== "
					+ description);
			System.err.println(description);
			throw new OperationException(description);
		}

		TUser user = list.get(0);
		System.err.println(user.toString());

		// 帐户权限是否为 0:administrator
		if (user.getRole() != 0) {
			String description = ExceptionsEnum.NOT_ADMIN_PRIVILEGE.getDescription();
			logger.error(this.getClass().getName() + "__NOT_ADMIN_PRIVILEGE__== "
					+ description);
			System.err.println(description);
			throw new OperationException(description);
		}

		/* 分页 */
		// page data
		List<TUser> pageData = this.getUserListByLimits(pageOrder, pageRows);

		Integer idCount = this.getIdCount();

		Pagination<List<TUser>> pagination = paginationUtil.assembly(pageData,
				idCount, pageRows, pageOrder);

		System.err.println(pagination.toString());
		for (TUser tUser : pagination.getData()) {
			System.err.println(tUser.toString());
		}

		return pagination;
	}

	@Override
	public List<TUser> getUserListByLimits(Integer pageNum, Integer limit) {
		System.err.println("pageNum== " + pageNum + ", limit== " + limit);

		if (pageNum < 1) {
			pageNum = 1;
		}
		pageNum -= 1;

		TUserExample userExample = new TUserExample();
		userExample.setOffset(pageNum * limit);
		userExample.setLimit(limit);

		List<TUser> list = userMapper.selectByExample(userExample);
		for (TUser tUser : list) {
			tUser.setPassword(null);
			tUser.setSalt(null);
			System.err.println(tUser.toString());
		}

		return list;
	}

	@Override
	public List<TUser> getAllUsers() throws OperationException {
		TUserExample userExample = new TUserExample();
		userExample.setOrderByClause("id asc");

		List<TUser> list = userMapper.selectByExample(userExample);
		for (TUser tUser : list) {
			System.err.println(tUser.toString());
		}

		System.out.println(this.getClass() + "_列表行数=== " + list.size());
		return list;
	}

	@Override
	public Integer getIdCount() throws OperationException {
		int countId = userMapper.selectCountId();
		System.err.println("ID的数量== " + countId);
		return countId;
	}

}
