package com.computer.subscribe.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.mapper.TSubscribeMapper;
import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.TSubscribeExample;
import com.computer.subscribe.pojo.TSubscribeExample.Criteria;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.service.ISubscribeService;
import com.computer.subscribe.service.IUserService;
import com.computer.subscribe.util.support.DateTimeKits;

@Service
public class SubscribeServiceImpl implements ISubscribeService {
	public static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private TSubscribeMapper mapper;

	@Autowired
	private IUserService ius;

	DateTimeKits dateTimeKits = DateTimeKits.getInstance();

	@Override
	public TSubscribe addNewScuSubscribe(TSubscribe subscribe)
			throws OperationException {
		System.err.println(this.getClass() + "==>addNewScuSubscribe==>TSubscribe=="
				+ subscribe.toString());

		Date nowDate = new Date();

		// 检查预约申请者是否为学生
		TUser user = ius.checkAccountIsRight(subscribe.getApplicant(), 2);

		// 判断申请使用之日期是否符合规定,是否为当前发起时间的下一周的周一至周五
		Boolean isBelong = dateTimeKits.judgeCentral(subscribe.getApplyUseDate());
		if (!isBelong) {
			String description = ExceptionsEnum.SUBSCRIBE_DATE_INVALID
					.getDescription();

			logger.info(this.getClass() + "==>addNewScuSubscribe==>isBelong=="
					+ description);
			System.err.println(this.getClass()
					+ "==>addNewScuSubscribe==>isBelong== " + description);
			throw new OperationException(description);
		}

		// 判断申请发起当日是否为周末
		Boolean isWeekend = dateTimeKits.judgeIsWeekend(nowDate);
		if (isWeekend) {
			String description = ExceptionsEnum.INVALID_DATE_WEEKEND
					.getDescription();

			logger.info(this.getClass() + "==>addNewScuSubscribe==>isWeekEnd=="
					+ description);
			System.err.println(this.getClass()
					+ "==>addNewScuSubscribe==>isWeekEnd==" + description);
			throw new OperationException(description);
		}

		// 校验申请者是否存在已经通过批准的,同日同时段,任意机房预约
		Boolean duplicate = this.checkApplyDuplicate(subscribe.getApplicant(),
				subscribe.getUseInterval(), 1, subscribe.getApplyUseDate());

		if (!duplicate) {
			String description = ExceptionsEnum.SUCCESS_SUBSCRIBE_DUPLICATION
					.getDescription();

			logger.info(this.getClass()
					+ "==>addNewScuSubscribe==>subscribe_duplication=="
					+ description);
			System.err.println(this.getClass()
					+ "==>addNewScuSubscribe==>subscribe_duplication=="
					+ description);

			throw new OperationException(description);
		}

		// 校验之前是否对同一日期,同一时段的同一机房,重复提交了申请
		Boolean duplicate2 = this.checkApplyDuplicate(subscribe.getApplicant(),
				subscribe.getUseInterval(), subscribe.getApplyUseDate());
		if (!duplicate2) {
			String description = ExceptionsEnum.WAITTING_APPLY_DUPLICATION
					.getDescription();

			logger.info(this.getClass()
					+ "==>addNewScuSubscribe==>subscribe_duplication=="
					+ description);
			System.err.println(this.getClass()
					+ "==>addNewScuSubscribe==>subscribe_duplication=="
					+ description);

			throw new OperationException(description);
		}

		// 预约状态:默认审核中
		subscribe.setSubscribeStatus(0);
		subscribe.setApplicationStartTime(nowDate);
		subscribe.setApplicant(user.getUserNum());

		int affect = mapper.insert(subscribe);

		System.err.println(
				this.getClass() + "==>addNewScuSubscribe==>affect==" + affect);
		System.err.println(
				this.getClass() + "==>addNewScuSubscribe==>TSubscribe Return== "
						+ subscribe.toString());
		return subscribe;
	}

	/**
	 * 
	 * @param applicant
	 * @param useInterval
	 * @param subscribeStatus
	 * @param applyUseDate
	 * @return
	 */
	public Boolean checkApplyDuplicate(Long applicant, Integer useInterval,
			Integer subscribeStatus, Date applyUseDate) {
		System.err.println(this.getClass()
				+ "==>addNewScuSubscribe==>checkApplyDuplicate.applicant="
				+ applicant + ",useInterval=" + useInterval + ",subscribeStatus="
				+ subscribeStatus + ",applyUseDate=" + applyUseDate);
		Boolean checks = null;

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();

		criteria.andApplicantEqualTo(applicant);
		criteria.andSubscribeStatusEqualTo(subscribeStatus);
		criteria.andApplyUseDateEqualTo(applyUseDate);
		criteria.andUseIntervalEqualTo(useInterval);

		List<TSubscribe> list = mapper.selectByExample(example);

		if (!list.isEmpty()) {// 若集合中含有元素
			for (TSubscribe tSubscribe : list) {
				System.err.println(tSubscribe.toString());
			}
			checks = false;
		} else {
			checks = true;
		}

		System.out.println(
				this.getClass() + "checkApplyDuplicate.return..checks=" + checks);
		return checks;
	}

	@Override
	public Boolean checkApplyDuplicate(Long applicant, Integer useInterval,
			Date applyUseDate) {
		System.err.println(this.getClass()
				+ "=>addNewScuSubscribe=>checkApplyDuplicate[Override].applicant="
				+ applicant + ",useInterval=" + useInterval + ",applyUseDate="
				+ applyUseDate);
		Boolean checks = null;

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();

		criteria.andApplicantEqualTo(applicant);
		criteria.andApplyUseDateEqualTo(applyUseDate);
		criteria.andUseIntervalEqualTo(useInterval);

		List<TSubscribe> list = mapper.selectByExample(example);

		if (!list.isEmpty()) {// 若集合中含有元素
			for (TSubscribe tSubscribe : list) {
				System.err.println(tSubscribe.toString());
			}
			checks = false;
		} else {
			checks = true;
		}

		System.out.println(this.getClass()
				+ "checkApplyDuplicate[Override].return..checks=" + checks);
		return checks;
	}

	@Override
	public List<TSubscribe> getSubscribeListByStatus(Integer status, Long userNum)
			throws OperationException {
		System.out.println(this.getClass() + "_getSubscribeListByStatus.status="
				+ status + ",userNum=" + userNum);

		// 检验工号是否属于教师或管理员
		TUser user = ius.checkUserExist(userNum);
		if (user.getRole() == 2) {
			String description = ExceptionsEnum.U_ACCOUNT_NOT_IT_PRIVILEGE
					.getDescription();
			
			System.out.println(this.getClass()
					+ "_getSubscribeListByStatus.description=" + description);
			logger.error(this.getClass() + "_getSubscribeListByStatus.description="
					+ description);
			throw new OperationException(description);
		}

		// 得到本周星期一和星期天的日期,确定范围
		ArrayList<Date> monAndSunList = dateTimeKits.getMonAndSunList();

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubscribeStatusEqualTo(status);
		criteria.andApplicationStartTimeBetween(monAndSunList.get(0),
				monAndSunList.get(1));

		List<TSubscribe> list = mapper.selectByExample(example);
		for (TSubscribe sub : list) {
			System.out.println(this.getClass() + "_getSubscribeListByStatus.list="
					+ sub.toString());
		}

		return list;
	}

}
