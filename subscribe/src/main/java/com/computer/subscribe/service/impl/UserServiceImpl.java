package com.computer.subscribe.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.exception.ValidatEntityNPException;
import com.computer.subscribe.mapper.TUserMapper;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.TUserExample;
import com.computer.subscribe.pojo.TUserExample.Criteria;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.service.IUserService;
import com.computer.subscribe.service.construct.AssemblyBean;
import com.computer.subscribe.service.construct.BuildUserImpl;
import com.computer.subscribe.util.JwtUtils;
import com.computer.subscribe.util.PaginationUtils;
import com.computer.subscribe.util.support.PasswordBusiness;

@Service
public class UserServiceImpl implements IUserService {
	String t = this.getClass().getName() + "\n";
	public static Logger logger = Logger.getLogger(UserServiceImpl.class);

	JwtUtils jwt = JwtUtils.getInstance();

	@Autowired
	private TUserMapper userMapper;

	PasswordBusiness pbBusiness = PasswordBusiness.getInstance();
	PaginationUtils paginationUtil = PaginationUtils.getInstance();

	String ts = this.getClass().getName() + "___\n";

	/**
	 * 管理员人数限制
	 */
	private static int admin_mounts = 900;

	@Override
	public Integer regist(TUser user)
			throws OperationException, ValidatEntityNPException {
		logger.info("\n" + user.toString());

		/* 检测 学号/电话/邮箱 是否唯一 */
		this.verifyCountAttributeForReg(user);

		// 盐值
		String salt = pbBusiness.extractSalt();
		System.err.println(this.getClass() + "+盐值__salt===" + salt);
		user.setSalt(salt);

		String keyTxt = pbBusiness.generate(user.getPassword(), salt);
		System.err.println(this.getClass() + "+密码__keyTxt===" + keyTxt);
		user.setPassword(keyTxt);

		int effect = userMapper.insert(user);
		return effect;
	}

	@Override
	public LoginData login(long userNum, String passwd, Integer role)
			throws OperationException {
		LoginData data = new LoginData();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer minute = 600 * 10000;

		this.checkUserExist(userNum);

		TUser tUser = this.getUserByUserNum(userNum);

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

		map.put("id", tUser.getId());
		map.put("userNum", tUser.getUserNum());
		map.put("mailbox", tUser.getMailbox());
		map.put("phone", tUser.getPhone());
		// 生成token,有效时间2小时
		String token = jwt.encode(map, minute * 60 * 2);

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
		paginationUtil.printMethod(this.getClass(), "revisePassword",
				"new password== " + newPwd, ", old password== " + oldPwd,
				", user number==" + userNum);

		this.checkUserExist(userNum);

		TUser user = this.getUserByUserNum(userNum);
		String tblPwdTxt = user.getPassword();// 表中密文

		// 检验提交之旧密码是否与表中密文一致
		boolean verify = pbBusiness.verify(oldPwd, tblPwdTxt);
		if (!verify) {
			String description = ExceptionsEnum.OLD_PASSWORD_ERR.getDescription();
			logger.error("_OLD_PASSWORD_ERR_== " + description);
			System.err.println(description);
			throw new OperationException(description);
		}

		// 如果新密码与表中密文一样,就无需修改
		boolean verify2 = pbBusiness.verify(newPwd, tblPwdTxt);
		if (verify2) {
			String description = ExceptionsEnum.NEWKEYWD_SAME_AS_OLDKEYWDTEXT
					.getDescription();
			logger.error("_NEWKEYWD_SAME_AS_OLDKEYWDTEXT_== " + description);
			System.err.println(description);
			throw new OperationException(description);
		}

		// 通过检验,执行修改
		String newKeyText = pbBusiness.generate(newPwd, user.getSalt());
		System.err.println(this.getClass() + "++newKeyText== " + newKeyText);

		TUserExample userExample = new TUserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUserNumEqualTo(userNum);

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

		TUser user = this.getUserById(id);

		// 用户存在与否,帐户权限是否为 0:administrator
		this.checkAdminPrivilege(user.getUserNum());

		/* 分页 - page data */
		List<TUser> pageData = this.getUserListByLimits(pageOrder, pageRows);

		Integer idCount = this.getIdCount();

		Pagination<List<TUser>> pagination = paginationUtil.assemblyUser(pageData,
				idCount, pageRows, pageOrder);

		System.err.println(pagination.toString());
		for (TUser tUser : pagination.getData()) {
			System.err.println(tUser.toString());
		}

		return pagination;
	}

	@Override
	public List<TUser> getUserListByLimits(Integer pageNum, Integer limit) {
		System.err.println(this.getClass() + "+getUserListByLimits++pageNum== "
				+ pageNum + ", limit== " + limit);

		pageNum = paginationUtil.getPageNum(pageNum);
		int offset = paginationUtil.getOffsetByPage(pageNum, limit);

		TUserExample userExample = new TUserExample();
		userExample.setOffset(offset);
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

	@Override
	public TUser modifyUserInfoByAdminNum(TUser submitUpdatedUser, Long adminNum)
			throws OperationException {
		paginationUtil.printMethod(this.getClass() + ".modifyUserInfoByAdminNum",
				"updatedUser=>" + submitUpdatedUser, ",_adminNum=== " + adminNum);

		this.checkAdminPrivilege(adminNum);
		Integer userId = submitUpdatedUser.getId();

		/* 校验被修改的用户,并返回原有用户数据 */
		TUser oldTblUser = this.checkUserByIdIfExist(userId);

		/* 若被修改对象是管理员,管理员之间不可互相修改 */
		this.stopAdminUpdatedByOther(oldTblUser);

		// 提交的某个拟定参数若与原先数据表中的一致,则不再构建为新属性
		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(userId);

		TUser commandEntity = this.getUserBeanForBuild(oldTblUser,
				submitUpdatedUser);

		int effect = userMapper.updateByExampleSelective(commandEntity, example);
		System.err.println(
				this.getClass() + "_modifyUserInfoByAdminNum_effect== " + effect);

		// 返回修改后的用户信息
		return this.checkUserByIdIfExist(userId);
	}

	@Override
	public TUser getUserByUserNum(Long userNum, Long adminNum)
			throws OperationException {
		logger.error(this.getClass() + ".getUserByUserNum,adminNum==" + userNum
				+ ",adminNum==" + adminNum);
		System.err.println(this.getClass() + ".getUserByUserNum,adminNum==" + userNum
				+ ",adminNum==" + adminNum);

		this.checkAdminPrivilege(adminNum);
		TUser user = this.checkUserExist(userNum);

		return user;
	}

	/**
	 * 检查某个用户是否存在,若是存在则返回对象
	 * 
	 * @param userNum
	 * @return
	 */
	public TUser checkUserExist(Long userNum) throws OperationException {
		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNumEqualTo(userNum);

		List<TUser> list = userMapper.selectByExample(example);
		// 查看用户是否存在
		if (list.isEmpty()) {
			String description = ExceptionsEnum.ACCOUNT_NO_EXIST.getDescription();

			logger.error(this.getClass().getName()
					+ "__查看用户是否存在__checkUserExist__== " + description);
			System.err.println(this.getClass().getName()
					+ "__查看用户是否存在__checkUserExist__==" + description);

			throw new OperationException(description);
		}

		for (TUser tUser : list) {
			tUser.setPassword(null);
			tUser.setSalt(null);
			System.err.println(tUser.toString());
		}

		TUser user = list.get(0);
		System.err
				.println(this.getClass() + "__checkUserExist==> " + user.toString());

		user.setPassword(null);
		user.setSalt(null);
		return user;
	}

	/**
	 * 校验管理员的存在与权限
	 * 
	 * @param adminNum
	 */
	public void checkAdminPrivilege(Long adminNum) throws OperationException {
		TUserExample example1 = new TUserExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUserNumEqualTo(adminNum);

		List<TUser> list1 = userMapper.selectByExample(example1);
		// 查看管理员是否存在
		if (list1.isEmpty()) {
			String description = ExceptionsEnum.ADMINISTRATOR_NO_EXIST
					.getDescription();

			logger.error(description);
			System.err.println(this.getClass()
					+ "__查看管理员是否存在__checkAdminPrivilege__== " + description);

			throw new OperationException(description);
		}

		for (TUser tUser : list1) {
			tUser.setPassword(null);
			tUser.setSalt(null);
			System.err.println(tUser.toString());
		}
		TUser adminUser = list1.get(0);

		// 校验权限
		if (adminUser.getRole() != 0) {
			String description = ExceptionsEnum.NOT_ADMIN_PRIVILEGE.getDescription();

			logger.error(description);
			System.err.println(
					this.getClass() + "__checkAdminPrivilege__== " + description);

			throw new OperationException(description);
		}
	}

	@Override
	public TUser checkAccountIsRight(Long userNum, Integer roleCode)
			throws OperationException {
		logger.info(this.getClass() + "__checkAdminPrivilege__userNum & roleCode== "
				+ userNum + "&&&" + roleCode);
		System.err.println(
				this.getClass() + "__checkAdminPrivilege__userNum & roleCode== "
						+ userNum + "&&&" + roleCode);

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNumEqualTo(userNum);

		List<TUser> list = userMapper.selectByExample(example);

		// 查看帐号是否存在
		if (list.isEmpty()) {
			String description = ExceptionsEnum.ACCOUNT_NO_EXIST.getDescription();

			logger.error(description);
			System.err.println(
					t + "__查看帐号是否存在__checkAccountIsRight__==" + description);

			throw new OperationException(description);
		}

		for (TUser tUser : list) {
			tUser.setPassword(null);
			tUser.setSalt(null);
			System.err.println(tUser.toString());
		}

		TUser user = list.get(0);
		System.err.println(t + "__checkAccountIsRight==> " + user.toString());

		String desc = null;
		if (roleCode != user.getRole()) {
			switch (roleCode) {
			case 0:
				desc = ExceptionsEnum.NOT_ADMIN_PRIVILEGE.getDescription();
				break;

			case 1:
				desc = ExceptionsEnum.NOT_TEACHER_PRIVILEGE.getDescription();
				break;

			case 2:
				desc = ExceptionsEnum.NOT_STUDENT_PRIVILEGE.getDescription();
				break;
			}

			logger.error(this.getClass() + "__检验帐号是否符合指定权限__checkAccountIsRight__== "
					+ desc);
			System.err.println(this.getClass()
					+ "__检验帐号是否符合指定权限__checkAccountIsRight__==" + desc);

			throw new OperationException(desc);
		}

		return user;
	}

	@Override
	public TUser checkBanStudent(Long userNum) throws OperationException {
		int studentRole = 2;
		System.err
				.println(this.getClass() + "__checkBanStudent.__userNum=" + userNum);

		// 校验工号,返回实体
		TUser user = this.checkUserExist(userNum);

		if (user.getRole() == studentRole) {
			String description = ExceptionsEnum.U_ACCOUNT_NOT_IT_PRIVILEGE
					.getDescription();

			System.err.println(this.getClass() + "__checkBanStudent.__description="
					+ description);
			logger.error(this.getClass() + "__checkBanStudent.__description="
					+ description);
			throw new OperationException(description);
		}

		return user;
	}

	@Override
	public TUser getUserByUserNum(Long userNum) throws OperationException {
		paginationUtil.printMethod(this.getClass(), "getUserByUserNum(userNum)",
				userNum);

		this.checkUserExist(userNum);

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNumEqualTo(userNum);

		List<TUser> list = userMapper.selectByExample(example);
		if (!list.isEmpty()) {
			TUser user = list.get(0);
			paginationUtil.printMethod(this.getClass(), "getUserByUserNum(userNum)",
					"return user=", user.toString());

			return user;
		}

		paginationUtil.printMethod(this.getClass(), "getUserByUserNum(userNum)",
				"list.isEmpty--return NULL");
		return null;
	}

	@Override
	public TUser getUserById(Integer id) throws OperationException {
		paginationUtil.printMethod(this.getClass(), "getUserById--ID=" + id);

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);

		List<TUser> list = userMapper.selectByExample(example);
		if (list.isEmpty()) {
			String description = ExceptionsEnum.ACCOUNT_NO_EXIST.getDescription();
			paginationUtil.printMethod(this.getClass(),
					"getUserById--ACCOUNT_NO_EXIST=" + description);

			throw new OperationException(description);
		}

		TUser user = list.get(0);
		paginationUtil.printMethod(this.getClass(), "getUserById--return__user=",
				user.toString());
		return user;
	}

	@Override
	public TUser modifyInfoByNormalUser(TUser submitUser) throws OperationException {
		paginationUtil.printMethod(this.getClass(),
				"modifyInfoByNormalUser--submitUser=" + submitUser.toString());

		Integer id = submitUser.getId();
		TUser tblUser = this.checkUserByIdIfExist(id);
		this.stopAdminUpdatedByOther(tblUser);

		TUser bean = this.getUserBeanForBuild(tblUser, submitUser);

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);

		int affect = userMapper.updateByExampleSelective(bean, example);
		paginationUtil.printMethod(this.getClass(),
				"modifyInfoByNormalUser--affect=" + affect);

		return this.checkUserByIdIfExist(id);
	}

	@Override
	public TUser modifyInfoByAdminMySelf(TUser admin) throws OperationException {
		paginationUtil.printMethod(this.getClass(),
				"modifyInfoByAdminMySelf--admin=" + admin.toString());

		Integer id = admin.getId();
		TUser tblUser = this.checkUserByIdIfExist(id);

		this.checkAdminPrivilege(tblUser.getUserNum());

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);

		TUser build = this.getUserBeanForBuild(tblUser, admin);
		int effect = userMapper.updateByExampleSelective(build, example);

		paginationUtil.printMethod(this.getClass(),
				"modifyInfoByAdminMySelf--effect=" + effect);

		return this.checkUserByIdIfExist(id);
	}

	@Override
	public TUser checkUserByIdIfExist(Integer userID) throws OperationException {
		paginationUtil.printMethod(this.getClass(),
				"checkUserByIdIfExist--userID=" + userID);

		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(userID);

		List<TUser> list = userMapper.selectByExample(example);
		if (list.isEmpty()) {
			String description = ExceptionsEnum.ACCOUNT_NO_EXIST.getDescription();

			paginationUtil.printMethod(this.getClass(),
					"checkUserByIdIfExist--ACCOUNT_NO_EXIST=" + description);

			throw new OperationException(description);
		}

		TUser user = list.get(0);

		user.setPassword(null);
		user.setSalt(null);
		paginationUtil.printMethod(this.getClass(),
				"checkUserByIdIfExist--return__user=", user.toString());

		return user;
	}

	@Override
	public void stopAdminUpdatedByOther(TUser adminAccount)
			throws OperationException {
		System.err
				.println(this.getClass() + "\nstopAdminUpdatedByOther--adminAccount="
						+ adminAccount.toString());

		if (adminAccount.getRole() == 0) {
			String description = ExceptionsEnum.ADMIN_CANNOT_MODIFIED
					.getDescription();

			logger.error(description);
			System.err.println(this.getClass().getName()
					+ "_modifyUserInfoByAdminNum_ADMIN_CANNOT_MODIFIED_= "
					+ description);

			throw new OperationException(description);
		}
	}

	@Override
	public TUser getUserBeanForBuild(TUser oldTblUser, TUser submitUpdatedUser)
			throws OperationException {
		paginationUtil.printMethod(this.getClass(), "oldTblUser=" + oldTblUser,
				"submitUpdatedUser=" + submitUpdatedUser);

		TUser user = new TUser();

		AssemblyBean assembly = new AssemblyBean();
		BuildUserImpl buildUser = new BuildUserImpl(user);

		TUser commandEntity = assembly.getUserCommandEntity(buildUser, oldTblUser,
				submitUpdatedUser);
		this.validAttrsAreAllNull(commandEntity);

		paginationUtil.printMethod(this.getClass(),
				"commandEntity=" + commandEntity);
		return commandEntity;
	}

	@Override
	public void validAttrsAreAllNull(TUser user) throws OperationException {
		paginationUtil.printMethod(this.getClass(),
				"validAttrsAreAllNull..user=" + user);
		int attrsNum = 4;// 被检验属性的数量
		String sign = "null";
		StringBuffer buffer = new StringBuffer(sign);
		String str = null;

		str += user.getMailbox();
		str += user.getMailbox();
		str += user.getRole();
		str += user.getUserName();
		System.err.println(this.getClass() + "..validAttrsAreAllNull..str=" + str);

		for (int i = 0; i < attrsNum; i++) {
			buffer.append(sign);
		}
		String bufStr = buffer.toString();
		System.err.println(
				this.getClass() + "..validAttrsAreAllNull..buffer.append=" + bufStr);

		Boolean eq = bufStr.equals(str);
		System.err.println(this.getClass() + "..validAttrsAreAllNull..是否相等=" + eq);

		if (eq) {
			String description = ExceptionsEnum.PROFILE_NO_DIFFERENCE
					.getDescription();
			logger.warn("\n" + description);
			throw new OperationException(description);
		}

		System.err
				.println(this.getClass() + "..validAttrsAreAllNull..else=>pass.放行");
	}

	@Override
	public void verifyCountAttributeForReg(TUser user) throws OperationException {
		logger.info("\n==" + user.toString());

		String phone = user.getPhone();
		String mailbox = user.getMailbox();
		Long userNum = user.getUserNum();
		Integer role = user.getRole();

		Integer countMailBox = userMapper.selectCountIdByMailBoxValue(mailbox);
		Integer countPhone = userMapper.selectCountIdByPhoneValue(phone);
		Integer countUserNum = userMapper.selectCountIdByUserNumValue(userNum);

		paginationUtil.printMethod(ts, "verifyCountAttributeForReg",
				"countMailBox=" + countMailBox, "countPhone=" + countPhone,
				"countUserNum=" + countUserNum);

		if (countUserNum > 0) {
			String description = ExceptionsEnum.ACCOUNT_DUPLICATE_CONFLICT
					.getDescription();
			System.err.println(ts + description);
			logger.error(description);

			throw new OperationException(description);
		}

		if (countPhone > 0) {
			String description = ExceptionsEnum.PHONE_DUPLICATE_CONFLICT
					.getDescription();
			System.err.println(ts + description);
			logger.error(description);

			throw new OperationException(description);
		}

		if (countMailBox > 0) {
			String description = ExceptionsEnum.EMAIL_DUPLICATE_CONFLICT
					.getDescription();
			System.err.println(ts + description);
			logger.error(description);

			throw new OperationException(description);
		}

		/* 如果是注册新的管理员，检查管理员总数是否超过限制 */
		if (role == 0) {
			Integer countRole = userMapper.selectCountIdByRoleValue(role);
			System.err.println(
					ts + "verifyCountAttributeForReg__countRole=" + countRole);

			if (countRole > admin_mounts) {
				String description = ExceptionsEnum.OUT_ADMIN_QUANTITY
						.getDescription();

				System.err.println(ts + description);
				logger.error(description);
				throw new OperationException(description);
			}
		}
	}

	@Override
	public TUser getProfileByMySelf(Integer userID, Long userNum)
			throws OperationException, ValidatEntityNPException {
		if (userID == null) {
			String description = ExceptionsEnum.ACCOUNT_BEING_OFFLINE
					.getDescription();
			logger.error(description);
			throw new OperationException(description);
		}

		if (userNum == null) {
			String description = ExceptionsEnum.ACCOUNT_BEING_OFFLINE
					.getDescription();
			logger.error(description);
			throw new OperationException(description);
		}

		paginationUtil.printMethod(ts,
				"..getProfileByMySelf..id=" + userID + "..user.num=" + userNum);
		TUser user = checkUserByIdIfExist(userID);

		if (!userNum.equals(user.getUserNum())) {
			String description = ExceptionsEnum.HADNOT_LOGINED.getDescription();
			logger.error(description);
			throw new OperationException(description);
		}

		return user;
	}

}
