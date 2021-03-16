package com.computer.subscribe.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.mapper.TUserMapper;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.TUserExample;
import com.computer.subscribe.pojo.TUserExample.Criteria;
import com.computer.subscribe.service.IUserService;
import com.computer.subscribe.util.account.PasswordBusiness;

@Service
public class UserServiceImpl implements IUserService {
	/**
	 * 默认初始密码-普通用户
	 */
	private static final String INIT_KEY = "666666";

	/**
	 * 默认初始密码-非普通用户
	 */
	private static final String INIT_KEY_ADMIN = "100300fun";

	@Autowired
	private TUserMapper userMapper;

	public static Logger logger = Logger.getLogger(UserServiceImpl.class);

	PasswordBusiness pbBusiness = PasswordBusiness.getInstance();

	@Override
	public Integer regist(TUser user) throws OperationException {
		logger.info(user.toString());

		// 防止NPE-空指针异常
		Optional<TUser> optionalUser = Optional.ofNullable(user);
		System.err.println(optionalUser);

		if (!optionalUser.isPresent()) {
			String description = ExceptionsEnum.REGIST_DATA_INCOMPLETE.getDescription();

			System.err.println(description);
			logger.error(description);

			// throw new OperationException(description);
		}
		TUser tUser = optionalUser.get();
		System.err.println(tUser.toString());

		/* 检测 学号/电话/邮箱 是否唯一 */
		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();

		criteria.andUserNumEqualTo(user.getUserNum());
		List<TUser> list = userMapper.selectByExample(example);

		if (list.size() > 0) {
			String description = ExceptionsEnum.ACCOUNT_DUPLICATE_CONFLICT.getDescription();
			System.err.println(description);
			logger.error(description);

			throw new OperationException(description);
		}

		TUserExample example2 = new TUserExample();
		Criteria criteria2 = example2.createCriteria();

		criteria2.andPhoneEqualTo(user.getPhone());
		List<TUser> list2 = userMapper.selectByExample(example2);

		if (list2.size() > 0) {
			String description = ExceptionsEnum.PHONE_DUPLICATE_CONFLICT.getDescription();
			System.err.println(description);
			logger.error(description);

			throw new OperationException(description);
		}

		TUserExample example3 = new TUserExample();
		Criteria criteria3 = example3.createCriteria();

		criteria3.andMailboxEqualTo(user.getMailbox());
		List<TUser> list3 = userMapper.selectByExample(example3);

		if (list3.size() > 0) {
			String description = ExceptionsEnum.EMAIL_DUPLICATE_CONFLICT.getDescription();
			System.err.println(description);
			logger.error(description);

			throw new OperationException(description);
		}

		// 如果是注册新的管理员，检查管理员总数是否达到五人
		if (user.getRole() == 0) {
			TUserExample example4 = new TUserExample();
			Criteria criteria4 = example4.createCriteria();

			criteria4.andRoleEqualTo(0);
			List<TUser> list4 = userMapper.selectByExample(example4);

			if (list4.size() > 4) {
				String description = ExceptionsEnum.OUT_ADMIN_QUANTITY.getDescription();
				System.err.println(description);
				logger.error(description);

				throw new OperationException(description);
			}
		}

		// 盐值
		String salt = pbBusiness.extractSalt();
		System.err.println("salt===" + salt);
		user.setSalt(salt);

		// 密码
		if (user.getRole() == 0) {
			String keyTxt = pbBusiness.generate(INIT_KEY_ADMIN, salt);
			user.setPassword(keyTxt);
		} else {
			String keyTxt = pbBusiness.generate(INIT_KEY, salt);
			user.setPassword(keyTxt);
		}

		int effect = userMapper.insert(user);
		return effect;
	}

}
