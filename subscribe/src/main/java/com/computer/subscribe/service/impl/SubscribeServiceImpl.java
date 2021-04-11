package com.computer.subscribe.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.mapper.TSubscribeMapper;
import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.TSubscribeExample;
import com.computer.subscribe.pojo.TSubscribeExample.Criteria;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.service.ISubscribeService;
import com.computer.subscribe.service.IUserService;

@Service
public class SubscribeServiceImpl implements ISubscribeService {
	@Autowired
	private TSubscribeMapper mapper;

	@Autowired
	private IUserService ius;

	@Override
	public TSubscribe addNewScuSubscribe(TSubscribe subscribe)
			throws OperationException {
		System.err.println(this.getClass() + "==>addNewScuSubscribe==>TSubscribe== "
				+ subscribe.toString());

		// 检查预约申请者是否为学生
		TUser user = ius.checkAccountIsRight(subscribe.getApplicant(), 2);

		// 发起申请时间
		Date nowDate = new Date();

		// 预约状态:默认审核中
		subscribe.setSubscribeStatus(0);
		subscribe.setApplicationStartTime(nowDate);
		subscribe.setApplicant(user.getUserNum());

		int affect = mapper.insert(subscribe);

		System.err.println(
				this.getClass() + "==>addNewScuSubscribe==>affect== " + affect);
		System.err.println(
				this.getClass() + "==>addNewScuSubscribe==>TSubscribe Return== "
						+ subscribe.toString());

		return subscribe;
	}

}
