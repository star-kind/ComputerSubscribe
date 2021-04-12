package com.computer.subscribe.service;

import java.util.Date;
import java.util.List;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.TSubscribe;

import lombok.NonNull;

/**
 * 机房使用预约申请业务<br>
 * 
 * 主体:学生;<br>
 * 目标:机房(的使用)<br>
 * 过程掌控:教师<br>
 * 
 * @author user
 *
 */
public interface ISubscribeService {
	/**
	 * 获取本周内<b>(按发起申请时间)</b>的指定状态<b>(如0-待审,1-通过...etc)</b>的预约申请单列表<br>
	 * <p>
	 * 调阅者:管理员或教师
	 * </p>
	 * 
	 * @param status
	 * @param userNum
	 * @return
	 * @throws OperationException
	 */
	List<TSubscribe> getSubscribeListByStatus(@NonNull Integer status,
			@NonNull Long userNum) throws OperationException;

	/**
	 * 根据参数,检索指定状态的预约申请单,获取申请单集合,如果集合为空,返真,不为空则反之
	 * 
	 * @param applicant
	 * @param useInterval
	 * @param subscribeStatus
	 * @param applyUseDate
	 * @return
	 */
	Boolean checkApplyDuplicate(@NonNull Long applicant,
			@NonNull Integer useInterval, @NonNull Integer subscribeStatus,
			@NonNull Date applyUseDate);

	/**
	 * 根据参数,检索指定状态的预约申请单,获取申请单集合,如果集合为空,返真,不为空则反之
	 * 
	 * @Override
	 * @param applicant
	 * @param useInterval
	 * @param applyUseDate
	 * @return
	 */
	Boolean checkApplyDuplicate(@NonNull Long applicant,
			@NonNull Integer useInterval, @NonNull Date applyUseDate);

	/**
	 * (学生)申请使用机房,生成一张预约单子<br>
	 * </ul>
	 * 需要手动提交的参数:
	 * <li>学号</li>
	 * <li>申请使用的机房编号</li>
	 * <li>申请使用日期</li>
	 * <li>申请使用时间段</li>
	 * </ul>
	 */
	TSubscribe addNewScuSubscribe(TSubscribe subscribe) throws OperationException;
}
