package com.computer.subscribe.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.mapper.TSubscribeMapper;
import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.TSubscribeExample;
import com.computer.subscribe.pojo.TSubscribeExample.Criteria;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.service.IComputerRoomService;
import com.computer.subscribe.service.ISubscribeService;
import com.computer.subscribe.service.IUserService;
import com.computer.subscribe.util.PaginationUtils;
import com.computer.subscribe.util.support.DateTimeKits;

@Service
public class SubscribeServiceImpl implements ISubscribeService {
	String ts = this.getClass().getCanonicalName() + "------\n";
	public static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private TSubscribeMapper mapper;

	@Autowired
	private IUserService ius;

	@Autowired
	private IComputerRoomService icrs;

	DateTimeKits dateTimeKits = DateTimeKits.getInstance();

	PaginationUtils paginationUtil = PaginationUtils.getInstance();

	@Override
	public TSubscribe addNewScuSubscribe(TSubscribe subscribe)
			throws OperationException {
		System.err.println(
				ts + "==>addNewScuSubscribe==>TSubscribe==" + subscribe.toString());

		// useInterval不得小于0,大于2
		if (subscribe.getUseInterval() < 0 || subscribe.getUseInterval() > 2) {
			String description = ExceptionsEnum.SUBSCRIBE_USEINTERVAL_OUT_RANGE
					.getDescription();
			System.err.println(ts + "==>addNewScuSubscribe==>" + description);
			throw new OperationException(description);
		}

		Date nowDate = new Date();

		// 检查预约申请者是否为学生
		TUser user = ius.checkAccountIsRight(subscribe.getApplicant(), 2);

		// 判断申请使用之日期是否符合规定,是否为当前发起时间的下一周的周一至周五
		Boolean isBelong = dateTimeKits.judgeCentral(subscribe.getApplyUseDate());
		if (!isBelong) {
			String description = ExceptionsEnum.SUBSCRIBE_DATE_INVALID
					.getDescription();

			logger.info(description);
			System.err.println(
					ts + "==>addNewScuSubscribe==>isBelong== " + description);
			throw new OperationException(description);
		}

		// 判断申请发起当日是否为周末
		Boolean isWeekend = dateTimeKits.judgeIsWeekend(nowDate);
		if (isWeekend) {
			String description = ExceptionsEnum.INVALID_DATE_WEEKEND
					.getDescription();

			logger.info("isWeekEnd==" + description);
			System.err.println(
					ts + "==>addNewScuSubscribe==>isWeekEnd==" + description);
			throw new OperationException(description);
		}

		// 校验申请者是否存在已经通过批准的,同日同时段,任意机房预约
		Boolean duplicate = this.checkApplyDuplicate(subscribe.getApplicant(),
				subscribe.getUseInterval(), 1, subscribe.getApplyUseDate());

		if (!duplicate) {
			String description = ExceptionsEnum.SUCCESS_SUBSCRIBE_DUPLICATION
					.getDescription();

			logger.info("subscribe_duplication==" + description);
			System.err.println(ts + "==>addNewScuSubscribe==>subscribe_duplication=="
					+ description);

			throw new OperationException(description);
		}

		// 校验之前是否对同一日期,同一时段的同一机房,重复提交了申请
		Boolean duplicate2 = this.checkApplyDuplicate(subscribe.getApplicant(),
				subscribe.getUseInterval(), subscribe.getApplyUseDate());
		if (!duplicate2) {
			String description = ExceptionsEnum.WAITTING_APPLY_DUPLICATION
					.getDescription();

			logger.info("WAITTING_APPLY_DUPLICATION==" + description);
			System.err.println(
					ts + "==>addNewScuSubscribe==>WAITTING_APPLY_DUPLICATION=="
							+ description);

			throw new OperationException(description);
		}

		TComputerRoom room = icrs.getComputerRoomByOrder(subscribe.getRoomNum());
		if (room == null) {// 是否存在此间机房
			String description = ExceptionsEnum.COMPUTER_ROOM_NOT_EXIST
					.getDescription();
			logger.info("computer_room_not_exist==" + description);
			System.err
					.println(ts + "==>addNewScuSubscribe==>computer_room_not_exist=="
							+ description);
			throw new OperationException(description);
		}

		// 获悉机房是否可用=1
		if (room.getAvailableStatus() != 1) {
			String description = ExceptionsEnum.COMPUTER_ROOM_UNAVAILABLE
					.getDescription();
			logger.info("computer_room_unavailable==" + description);
			System.err.println(
					ts + "==>addNewScuSubscribe==>computer_room_unavailable=="
							+ description);
			throw new OperationException(description);
		}

		// 预约状态:默认审核中
		subscribe.setSubscribeStatus(0);
		subscribe.setApplicationStartTime(nowDate);
		subscribe.setApplicant(user.getUserNum());

		int affect = mapper.insert(subscribe);

		System.err.println(ts + "==>addNewScuSubscribe==>affect==" + affect
				+ ",==>TSubscribe Return== " + subscribe.toString());
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
		System.err.println(ts
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

		System.out.println(ts + "checkApplyDuplicate.return..checks=" + checks);
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
		System.err.println(ts + "_getSubscribeListByStatus.status=" + status
				+ ",userNum=" + userNum);

		// 检验工号是否属于教师或管理员
		ius.checkBanStudent(userNum);

		// 得到本周星期一和星期天的日期,确定范围
		ArrayList<Date> monAndSunList = dateTimeKits.getMonAndSunList();

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubscribeStatusEqualTo(status);
		criteria.andApplicationStartTimeBetween(monAndSunList.get(0),
				monAndSunList.get(1));

		List<TSubscribe> list = mapper.selectByExample(example);
		for (TSubscribe sub : list) {
			System.err.println(
					ts + "_getSubscribeListByStatus.list=" + sub.toString());
		}

		return list;
	}

	@Override
	public List<TSubscribe> getPreviousWeekSubscribes(Long adminNum)
			throws OperationException {
		// 检验管理员帐号
		ius.checkAccountIsRight(adminNum, 0);

		// 获取本周星期一和星期天
		ArrayList<Date> list = dateTimeKits.getMonAndSunList();
		HashMap<String, Date> dateMap = dateTimeKits
				.getPreviousWeekDateMap(list.get(0), list.get(1));

		Date monDate = dateMap.get(DateTimeKits.monday_key);
		Date sunDate = dateMap.get(DateTimeKits.sunday_key);

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andApplicationStartTimeBetween(monDate, sunDate);

		List<TSubscribe> subscribeList = mapper.selectByExample(example);
		for (TSubscribe sub : subscribeList) {
			System.err.println(this.getClass()
					+ "_getPreviousWeekSubscribes.subscribeList=" + sub.toString());
		}

		return subscribeList;
	}

	@Override
	public TSubscribe handleSubscribeStatus(Integer status, Long teacherNum,
			Long subscribeID) throws OperationException {
		System.err.println(ts + "__handleSubscribeStatus.status=" + status
				+ ",teacherNum=" + teacherNum + ",subscribeID=" + subscribeID);

		// 新的审核状态不得超过指定范围
		if (status < 0 || status > 3) {
			String description = ExceptionsEnum.INVALID_SUBSCRIBE_STATUS
					.getDescription();
			System.err.println(ts + "handleSubscribeStatus==" + description);
			throw new OperationException(description);
		}

		// 校验是否属于教师帐号
		ius.checkAccountIsRight(teacherNum, 1);

		TSubscribe subscribe = this.getSubscribeByID(subscribeID);

		// 校验:预约申请发起日期是否属于本周的周一至七;
		Date startTime = subscribe.getApplicationStartTime();
		Boolean isInThisWeek = dateTimeKits.judgeDayIsInThisWeek(startTime);
		if (!isInThisWeek) {
			String description = ExceptionsEnum.SUBSCRIBE_NOT_IN_THIS_WEEK
					.getDescription();

			System.err.println(
					ts + "__handleSubscribeStatus__description=" + description);
			logger.error(ts + "__handleSubscribeStatus__description=" + description);
			throw new OperationException(description);
		}

		// 若与原状态一致,就不要再更改
		this.checkStatusIsDuplicated(subscribe.getSubscribeStatus(), status);

		// 根据申请者学生+预约使用日期,检查在同一天,同一时段是否已有通过的预约单
		List<TSubscribe> list2 = this.getSubscribesByConditions(subscribe, 1);
		if (!list2.isEmpty()) {
			String description = ExceptionsEnum.SUBSCRIBE_HAS_SUCCESS_DUPLICATION
					.getDescription();

			System.err.println(this.getClass()
					+ "__handleSubscribeStatus__description=" + description);
			logger.error(ts + "__handleSubscribeStatus__description=" + description);
			throw new OperationException(description);
		}

		// 更改状态,写入新的处理日期时间,和处理者
		TSubscribeExample example4 = new TSubscribeExample();
		Criteria criteria4 = example4.createCriteria();
		criteria4.andIdEqualTo(subscribeID);

		TSubscribe subscribe2 = new TSubscribe();
		subscribe2.setSubscribeStatus(status);
		subscribe2.setHandleTime(new Date());
		subscribe2.setReviewer(teacherNum);

		int affect = mapper.updateByExampleSelective(subscribe2, example4);
		System.err.println(ts + "__handleSubscribeStatus__affect=" + affect);

		// 若批准本单通过,则处于同日同时段的,属于该申请人的其它预约,皆变为失败:2
		this.reviseStatusExcludeId(subscribe.getApplicant(),
				subscribe.getApplyUseDate(), subscribe.getUseInterval(), 2,
				subscribeID, teacherNum);

		TSubscribe subscribe6 = this.getSubscribeByID(subscribeID);
		System.err.println(
				ts + "__handleSubscribeStatus__subscribe6=" + subscribe6.toString());

		return subscribe6;
	}

	@Override
	public TSubscribe getSubscribeByID(Long subscribeID) {
		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		TSubscribe subscribe = new TSubscribe();

		criteria.andIdEqualTo(subscribeID);
		List<TSubscribe> list = mapper.selectByExample(example);

		if (list.isEmpty()) {
			String description = ExceptionsEnum.SUBSCRIBE_NOT_EXIST.getDescription();
			System.err.println(this.getClass()
					+ "__getSubscribeByID.__HAVE_NOT_SUBSCRIBE=" + description);

			throw new OperationException(description);
		} else {
			for (TSubscribe tSubscribe : list) {
				System.err.println(ts + "__getSubscribeByID.list.tSubscribe="
						+ tSubscribe.toString());
			}

			subscribe = list.get(0);
		}
		return subscribe;
	}

	@Override
	public List<TSubscribe> getSubscribesByConditions(TSubscribe conditions,
			Integer status) {
		System.err.println(ts + "__getSubscribesByConditions__="
				+ conditions.toString() + ",_status=" + status);

		TSubscribeExample example2 = new TSubscribeExample();
		Criteria criteria2 = example2.createCriteria();

		criteria2.andApplicantEqualTo(conditions.getApplicant());
		criteria2.andApplyUseDateEqualTo(conditions.getApplyUseDate());
		criteria2.andUseIntervalEqualTo(conditions.getUseInterval());
		criteria2.andSubscribeStatusEqualTo(status);

		List<TSubscribe> list2 = mapper.selectByExample(example2);
		if (!list2.isEmpty()) {
			for (TSubscribe tSubscribe : list2) {
				System.err.println(
						ts + "__getSubscribesByConditions=" + tSubscribe.toString());
			}
		}

		return list2;
	}

	@Override
	public List<TSubscribe> getSubscribeWeekListByStatus(Long userNum,
			Integer status) throws OperationException {
		// 校验工号
		ius.checkBanStudent(userNum);

		ArrayList<Date> dateList = dateTimeKits.getMonAndSunList();

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubscribeStatusEqualTo(status);
		criteria.andApplicationStartTimeBetween(dateList.get(0), dateList.get(1));

		List<TSubscribe> subscribeList = mapper.selectByExample(example);
		for (TSubscribe tSubscribe : subscribeList) {
			System.err.println(ts + "__getSubscribeWeekListByStatus__.tSubscribe="
					+ tSubscribe.toString());
		}

		return subscribeList;
	}

	@Override
	public Integer reviseStatusExcludeId(Long applicant, Date applyUseDate,
			Integer useInterval, Integer status, Long subscribeId, Long handlerNum)
			throws OperationException {
		System.out.println(ts + "__reviseStatusExcludeId__applicant=" + applicant
				+ ",applyUseDate=" + applyUseDate + ",useInterval=" + useInterval
				+ ",status=" + status + ",subscribeId=" + subscribeId
				+ ",handlerNum=" + handlerNum);

		// 校验用户工号
		ius.checkBanStudent(handlerNum);

		TSubscribeExample example6 = new TSubscribeExample();
		Criteria criteria6 = example6.createCriteria();

		criteria6.andApplicantEqualTo(applicant);
		criteria6.andApplyUseDateEqualTo(applyUseDate);
		criteria6.andUseIntervalEqualTo(useInterval);
		criteria6.andIdNotEqualTo(subscribeId);

		TSubscribe subscribe4 = new TSubscribe();
		subscribe4.setSubscribeStatus(status);

		int affect4 = mapper.updateByExampleSelective(subscribe4, example6);
		System.err.println(ts + "__reviseStatusExcludeId__affect4=" + affect4);

		return affect4;
	}

	@Override
	public TSubscribe getSubscribeByID(Long subscribeID, Long userNum)
			throws OperationException {
		// 校验用户的帐号
		ius.checkUserExist(userNum);

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		TSubscribe subscribe = new TSubscribe();

		criteria.andIdEqualTo(subscribeID);
		List<TSubscribe> list = mapper.selectByExample(example);

		if (list.isEmpty()) {
			String description = ExceptionsEnum.SUBSCRIBE_NOT_EXIST.getDescription();
			System.err.println(this.getClass()
					+ "__getSubscribeByID__[Override].__HAVE_NOT_SUBSCRIBE="
					+ description);

			throw new OperationException(description);
		} else {
			for (TSubscribe tSubscribe : list) {
				System.err.println(this.getClass()
						+ "__getSubscribeByID__[Override].list.tSubscribe="
						+ tSubscribe.toString());
			}

			subscribe = list.get(0);
		}
		return subscribe;
	}

	@Override
	public Pagination<List<TSubscribe>> getThisWeekSubscribeListByTeacher(
			Long applicant, Long teacherNum, Integer rows, Integer pageOrder)
			throws OperationException {
		System.err.println(ts + "__getThisWeekSubscribeListByTeacher__applicant="
				+ applicant + ",teacherNum=" + teacherNum + ",rows=" + rows
				+ ",pageOrder=" + pageOrder);

		// 判断是否存在,是否为指定类型的帐户
		ius.checkAccountIsRight(teacherNum, 1);
		ius.checkUserExist(applicant);

		ArrayList<Date> monAndSunList = dateTimeKits.getMonAndSunList();
		String[] arr = dateTimeKits.getStrArrFromTimeList(monAndSunList);

		// 分页获取本周内的预约申请
		pageOrder = paginationUtil.getPageNum(pageOrder);
		int offset = paginationUtil.getOffsetByPage(pageOrder, rows);

		List<TSubscribe> listLimit = mapper.selectByTimeApplicantLimit(arr[0],
				arr[1], offset, rows, applicant);
		for (TSubscribe tSubscribe : listLimit) {
			System.err
					.println(ts + "-getThisWeekSubscribeListByTeacher-list-element:"
							+ tSubscribe.toString());
		}

		Integer totalRows = this.getCountSubscribesByApplicant(applicant);
		System.err.println(
				ts + "-getThisWeekSubscribeListByTeacher--totalRows:" + totalRows);

		Pagination<List<TSubscribe>> pagination = paginationUtil
				.assemblySubscribe(listLimit, totalRows, rows, pageOrder);
		System.err.println(pagination.toString());

		return pagination;
	}

	@Override
	public Pagination<List<TSubscribe>> getApplicantSubscribesByAdmin(
			Integer pageOrder, Integer row, Long applicant, Long adminNum)
			throws OperationException {
		System.err.println(ts + "--getApplicantSubscribesByAdmin..applicant="
				+ applicant + "..adminNum=" + adminNum + "..pageOrder=" + pageOrder
				+ "..row=" + row);

		ius.checkAdminPrivilege(adminNum);
		ius.checkUserExist(applicant);

		pageOrder = paginationUtil.getPageNum(pageOrder);
		int offset = paginationUtil.getOffsetByPage(pageOrder, row);

		List<TSubscribe> limitList = mapper.selectByApplicantAndLimit(offset, row,
				applicant);

		Integer counts = mapper.getCountByApplicant(applicant);
		System.err.println(this.getClass()
				+ "--getApplicantSubscribesByAdmin--counts:" + counts);

		Pagination<List<TSubscribe>> pagination = paginationUtil
				.assemblySubscribe(limitList, counts, row, pageOrder);
		System.err.println(pagination.toString());

		return pagination;
	}

	@Override
	public Integer getCountSubscribesByApplicant(Long applicant)
			throws OperationException {
		ius.checkUserExist(applicant);

		ArrayList<Date> monAndSunList = dateTimeKits.getMonAndSunList();
		String[] arrStr = dateTimeKits.getStrArrFromTimeList(monAndSunList);

		Integer countRows = mapper.selectCountsByApplicantAndTime(arrStr[0],
				arrStr[1], applicant);
		System.err.println(this.getClass()
				+ "__getCountSubscribesByApplicant__countRows=" + countRows);

		return countRows;
	}

	@Override
	public Pagination<List<TSubscribe>> getWeekSubscribesListByStudent(
			Long studentNum, Integer rows, Integer pageOrder)
			throws OperationException {
		System.err.println(ts + "__getWeekSubscribesListByStudent__studentNum="
				+ studentNum + ",rows=" + rows + ",pageOrder=" + pageOrder);

		// 判断是否存在,是否为指定类型的帐户
		ius.checkAccountIsRight(studentNum, 2);

		ArrayList<Date> monAndSunList = dateTimeKits.getMonAndSunList();
		String[] arr = dateTimeKits.getStrArrFromTimeList(monAndSunList);

		pageOrder = paginationUtil.getPageNum(pageOrder);
		int offset = paginationUtil.getOffsetByPage(pageOrder, rows);

		List<TSubscribe> listLimit = mapper.selectByTimeApplicantLimit(arr[0],
				arr[1], offset, rows, studentNum);
		for (TSubscribe tSubscribe : listLimit) {
			System.err.println(ts + "--getWeekSubscribesListByStudent--list-element:"
					+ tSubscribe.toString());
		}

		Integer totalRows = this.getCountSubscribesByApplicant(studentNum);
		System.err.println(
				ts + "--getWeekSubscribesListByStudent--totalRows:" + totalRows);

		Pagination<List<TSubscribe>> pagination = paginationUtil
				.assemblySubscribe(listLimit, totalRows, rows, pageOrder);
		System.err.println(pagination.toString());

		return pagination;
	}

	@Override
	public List<TSubscribe> getStudentSubscribesForMyself(Long studentNum,
			Integer status) throws OperationException {
		System.err.println(ts + "--getStudentSubscribesForMyself--studentNum:"
				+ studentNum + "--status=" + status);
		// 校验学号
		ius.checkAccountIsRight(studentNum, 2);

		ArrayList<Date> dateList = dateTimeKits.getMonAndSunList();

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andApplicantEqualTo(studentNum);
		criteria.andSubscribeStatusEqualTo(status);
		criteria.andApplicationStartTimeBetween(dateList.get(0), dateList.get(1));

		List<TSubscribe> subscribeList = mapper.selectByExample(example);
		for (TSubscribe tSubscribe : subscribeList) {
			System.err.println(ts + "__getStudentSubscribesForMyself__.tSubscribe="
					+ tSubscribe.toString());
		}

		return subscribeList;
	}

	@Override
	public TSubscribe studentCancelSubscribeById(Long studentNum, Long subscribeID,
			Integer status) throws OperationException {
		System.err.println(this.getClass()
				+ "--studentCancelSubscribeById--studentNum:" + studentNum
				+ "--subscribeID=" + subscribeID + "--status=" + status);
		TUser user = ius.checkAccountIsRight(studentNum, 2);

		if (status != 3) {// 状态范围限定在0-3,在此只能是:3
			String description = ExceptionsEnum.INVALID_SUBSCRIBE_STATUS
					.getDescription();

			System.err.println(
					ts + "--studentCancelSubscribeById--description=" + description);
			throw new OperationException(description);
		}

		TSubscribe subscribe = this.getSubscribeByID(subscribeID, studentNum);

		System.err.println(ts
				+ "--studentCancelSubscribeById__subscribe.getApplicant() : user.getUserNum()="
				+ subscribe.getApplicant() + "--" + user.getUserNum());

		// 检验预约单中的申请者是否与操作者学号一致,即是否是本人
		if (!subscribe.getApplicant().equals(user.getUserNum())) {
			String description = ExceptionsEnum.NOT_THIS_SUBSCRIBE_APPLIER
					.getDescription();
			System.err.println(
					ts + "--studentCancelSubscribeById--description=" + description);

			throw new OperationException(description);
		}

		// 检验预约单中的"状态"是否已经是"已取消",是则报错
		this.checkStatusIsDuplicated(subscribe.getSubscribeStatus(), status);

		// 检验预约单中的申请发起日期是否在本周内
		Boolean isInThisWeek = dateTimeKits
				.judgeDayIsInThisWeek(subscribe.getApplicationStartTime());

		if (!isInThisWeek) {
			String description = ExceptionsEnum.SUBSCRIBE_NOT_IN_THIS_WEEK
					.getDescription();
			System.err.println(
					ts + "--studentCancelSubscribeById--description=" + description);

			throw new OperationException(description);
		}

		// 执行
		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(subscribeID);

		TSubscribe subscribe2 = new TSubscribe();
		subscribe2.setSubscribeStatus(status);

		int effect = mapper.updateByExampleSelective(subscribe2, example);
		System.err.println(ts + "--studentCancelSubscribeById--effect=" + effect);

		// 返还数据
		TSubscribe subscribe4 = this.getSubscribeByID(subscribeID);
		return subscribe4;
	}

	@Override
	public Pagination<List<TSubscribe>> getStudentSubscribeForMyPagination(
			Long studentNum, Integer status, Integer pageOrder, Integer row)
			throws OperationException {
		System.err.println(this.getClass()
				+ "--getStudentSubscribeForMyPagination--studentNum:" + studentNum
				+ "--status=" + status + ",pageOrder=" + pageOrder + ",row=" + row);

		pageOrder = paginationUtil.getPageNum(pageOrder);
		int offset = paginationUtil.getOffsetByPage(pageOrder, row);

		ArrayList<Date> dateList = dateTimeKits.getMonAndSunList();
		String[] strArr = dateTimeKits.getStrArrFromTimeList(dateList);

		// Total Data Line Count
		Integer totalSize = mapper.selectCountsByApplicantAndTime(strArr[0],
				strArr[1], studentNum);
		System.err.println(
				ts + "--getStudentSubscribeForMyPagination--totalSize=" + totalSize);

		TSubscribeExample example = new TSubscribeExample();
		example.setOffset(offset);
		example.setLimit(row);

		Criteria criteria = example.createCriteria();
		criteria.andApplicantEqualTo(studentNum);
		criteria.andSubscribeStatusEqualTo(status);
		criteria.andApplicationStartTimeBetween(dateList.get(0), dateList.get(1));

		// page data
		List<TSubscribe> pageList = mapper.selectByExample(example);
		for (TSubscribe sub : pageList) {
			System.err.println(sub.toString());
		}

		Pagination<List<TSubscribe>> paginationSubscribe = paginationUtil
				.assemblySubscribe(pageList, totalSize, row, pageOrder);

		System.err.println(this.getClass()
				+ "--getStudentSubscribeForMyPagination--paginationSubscribe="
				+ paginationSubscribe);

		return paginationSubscribe;
	}

	@Override
	public Boolean checkStatusIsDuplicated(Integer newStatus, Integer oldStatus)
			throws OperationException {
		System.err.println(ts + "--checkStatusIsDuplicated--newStatus=" + newStatus
				+ "--oldStatus=" + oldStatus);

		// 若与原状态一致,就不要再更改
		if (newStatus == oldStatus) {
			String description = ExceptionsEnum.HANDLE_STATUS_DUPLICATION
					.getDescription();

			System.err.println(this.getClass()
					+ "__handleSubscribeStatus__description=" + description);
			logger.error(ts + "__handleSubscribeStatus__description=" + description);
			throw new OperationException(description);
		}
		return true;
	}

	@Override
	public Integer getCountForStatusInSomeRoom(Long userNum, Integer roomNum,
			Integer status) throws OperationException {
		System.err.println(ts + "__\n--getCountForStatusInSomeRoom__userNum="
				+ userNum + "--roomNum=" + roomNum + "--status=" + status);

		ius.checkUserExist(userNum);

		Integer counts = mapper.getCountIdByStatusAndRoom(status, roomNum);
		System.err.println(
				ts + "__\n--getCountForStatusInSomeRoom__return_counts=" + counts);
		return counts;
	}

	@Override
	public Pagination<List<TSubscribe>> getSubcribeByTeacherReview(
			Long reviewTeacher, Integer pageOrder, Integer row)
			throws OperationException {
		System.err.println(ts + "--getSubcribeByTeacherReview__reviewTeacher="
				+ reviewTeacher + "--pageOrder=" + pageOrder + "--row=" + row);

		ius.checkAccountIsRight(reviewTeacher, 1);

		pageOrder = paginationUtil.getPageNum(pageOrder);
		int offset = paginationUtil.getOffsetByPage(pageOrder, row);

		// 获取本周首尾日期
		ArrayList<Date> monAndSunList = dateTimeKits.getMonAndSunList();

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andReviewerEqualTo(reviewTeacher);
		criteria.andHandleTimeBetween(monAndSunList.get(0), monAndSunList.get(1));

		// 根据审核者工号,获取本周内全部数据==>总行数
		List<TSubscribe> weekTotalList = mapper.selectByExample(example);
		int totalSize = weekTotalList.size();
		System.err.println(
				ts + "--getSubcribeByTeacherReview--totalSize=" + totalSize);

		// 获取本周内分页数据
		TSubscribeExample limitExample = new TSubscribeExample();
		limitExample.setLimit(row);
		limitExample.setOffset(offset);

		Criteria limitCriteria = limitExample.createCriteria();
		limitCriteria.andReviewerEqualTo(reviewTeacher);
		limitCriteria.andHandleTimeBetween(monAndSunList.get(0),
				monAndSunList.get(1));
		List<TSubscribe> limitListData = mapper.selectByExample(limitExample);

		Pagination<List<TSubscribe>> pagination = paginationUtil
				.assemblySubscribe(limitListData, totalSize, row, pageOrder);

		return pagination;
	}

	@Override
	public Pagination<List<TSubscribe>> getSubcribeByTeacherReview(
			Long reviewTeacher, Integer page, Integer limit, Integer status)
			throws OperationException {
		System.err.println(
				ts + "--getSubcribeByTeacherReview__(Override)__reviewTeacher="
						+ reviewTeacher + "--page=" + page + "--limit=" + limit);

		ius.checkAccountIsRight(reviewTeacher, 1);

		page = paginationUtil.getPageNum(page);
		int offset = paginationUtil.getOffsetByPage(page, limit);

		// 获取本周首尾日期
		ArrayList<Date> monAndSunList = dateTimeKits.getMonAndSunList();

		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andReviewerEqualTo(reviewTeacher);
		criteria.andHandleTimeBetween(monAndSunList.get(0), monAndSunList.get(1));
		criteria.andSubscribeStatusEqualTo(status);

		// 根据审核者工号,获取本周内全部数据==>总行数
		List<TSubscribe> weekTotalList = mapper.selectByExample(example);
		int totalSize = weekTotalList.size();
		System.err.println(
				ts + "--getSubcribeByTeacherReview--totalSize=" + totalSize);

		// 获取本周内分页数据
		TSubscribeExample limitExample = new TSubscribeExample();
		limitExample.setLimit(limit);
		limitExample.setOffset(offset);

		Criteria limitCriteria = limitExample.createCriteria();
		limitCriteria.andReviewerEqualTo(reviewTeacher);
		limitCriteria.andHandleTimeBetween(monAndSunList.get(0),
				monAndSunList.get(1));
		limitCriteria.andSubscribeStatusEqualTo(status);
		List<TSubscribe> limitListData = mapper.selectByExample(limitExample);

		Pagination<List<TSubscribe>> pagination = paginationUtil
				.assemblySubscribe(limitListData, totalSize, limit, page);

		return pagination;
	}

	@Override
	public Pagination<List<TSubscribe>> getRoomSubscribesListByTeacher(
			Long teacherNum, Integer pageOrder, Integer limit, Integer roomNum)
			throws OperationException {
		System.err.println(ts + "--getRoomSubscribesListByTeacher--pageOrder="
				+ pageOrder + ",limit=" + limit + ",roomNum=" + roomNum);

		ius.checkAccountIsRight(teacherNum, 1);

		pageOrder = paginationUtil.getPageNum(pageOrder);
		int offset = paginationUtil.getOffsetByPage(pageOrder, limit);

		// 获取本周首尾
		ArrayList<Date> monSunList = dateTimeKits.getMonAndSunList();

		// 获取符合参数条件的全部数据
		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoomNumEqualTo(roomNum);
		criteria.andApplicationStartTimeBetween(monSunList.get(0),
				monSunList.get(1));

		// 总行数
		int totalSize = mapper.selectByExample(example).size();
		System.err.println(
				ts + "getRoomSubscribesListByTeacher..totalSize=" + totalSize);

		// 获取符合参数条件的分页数据
		TSubscribeExample limitExample = new TSubscribeExample();
		Criteria limitCriteria = limitExample.createCriteria();
		limitCriteria.andRoomNumEqualTo(roomNum);
		limitCriteria.andApplicationStartTimeBetween(monSunList.get(0),
				monSunList.get(1));

		limitExample.setLimit(limit);
		limitExample.setOffset(offset);
		List<TSubscribe> pageData = mapper.selectByExample(limitExample);

		Pagination<List<TSubscribe>> pagination = paginationUtil
				.assemblySubscribe(pageData, totalSize, limit, pageOrder);
		return pagination;
	}

	@Override
	public Pagination<List<TSubscribe>> getRoomSubscribesListByTeacher(
			Long teacherNum, Integer pageOrder, Integer limit, Integer roomNum,
			Integer status) throws OperationException {
		System.err.println(ts + "--getRoomSubscribesListByTeacher--pageOrder="
				+ pageOrder + ",limit=" + limit + ",roomNum=" + roomNum + ",status="
				+ status);

		ius.checkAccountIsRight(teacherNum, 1);

		pageOrder = paginationUtil.getPageNum(pageOrder);
		int offset = paginationUtil.getOffsetByPage(pageOrder, limit);

		// 获取本周首尾
		ArrayList<Date> monSunList = dateTimeKits.getMonAndSunList();

		// 获取符合参数条件的全部数据
		TSubscribeExample example = new TSubscribeExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoomNumEqualTo(roomNum);
		criteria.andApplicationStartTimeBetween(monSunList.get(0),
				monSunList.get(1));
		criteria.andSubscribeStatusEqualTo(status);

		// 总行数
		int totalSize = mapper.selectByExample(example).size();
		System.err.println(
				ts + "getRoomSubscribesListByTeacher..totalSize=" + totalSize);

		// 获取符合参数条件的分页数据
		TSubscribeExample limitExample = new TSubscribeExample();
		Criteria limitCriteria = limitExample.createCriteria();
		limitCriteria.andRoomNumEqualTo(roomNum);
		limitCriteria.andApplicationStartTimeBetween(monSunList.get(0),
				monSunList.get(1));
		limitCriteria.andSubscribeStatusEqualTo(status);

		limitExample.setLimit(limit);
		limitExample.setOffset(offset);
		List<TSubscribe> pageData = mapper.selectByExample(limitExample);

		Pagination<List<TSubscribe>> pagination = paginationUtil
				.assemblySubscribe(pageData, totalSize, limit, pageOrder);
		return pagination;
	}

}
