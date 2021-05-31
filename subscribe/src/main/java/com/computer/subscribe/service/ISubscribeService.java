package com.computer.subscribe.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.response.Pagination;

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
	 * 教师分页获取本周收到的全部预约单,不限申请者,不限状态和机房
	 * 
	 * @param pageOrder  限教师
	 * @param limit      行数
	 * @param teacherNum 页码
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getAllSubscirbeOnWeek(@NonNull Integer pageOrder,
			@NonNull Integer limit, @NonNull Long teacherNum)
			throws OperationException;

	/**
	 * 教师分页获取,某间机房在本周内,收到的预约申请列表(指定审核状态)
	 * 
	 * @param teacherNum
	 * @param pageOrder
	 * @param limit
	 * @param roomNum
	 * @param status
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getRoomSubscribesListByTeacher(
			@NonNull Long teacherNum, @NonNull Integer pageOrder,
			@NonNull Integer limit, @NonNull Integer roomNum,
			@NonNull Integer status) throws OperationException;

	/**
	 * 教师分页获取,某间机房在本周内,收到的预约申请列表(不限定审核状态)
	 * 
	 * @param teacherNum
	 * @param pageOrder
	 * @param limit
	 * @param roomNum
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getRoomSubscribesListByTeacher(
			@NonNull Long teacherNum, @NonNull Integer pageOrder,
			@NonNull Integer limit, @NonNull Integer roomNum)
			throws OperationException;

	/**
	 * 教师分页查阅,本周内自己经手过的预约申请单,<b>不限预约状态</b>
	 * 
	 * @param reviewTeacher TSubscribe.reviewer
	 * @param pageOrder
	 * @param row
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getSubcribeByTeacherReview(
			@NonNull Long reviewTeacher, @NonNull Integer pageOrder,
			@NonNull Integer row) throws OperationException;

	/**
	 * (Override)<br>
	 * 教师分页查阅,本周内自己经办过的预约申请单,<b>指定预约状态</b>
	 * 
	 * @param reviewTeacher
	 * @param page
	 * @param limit
	 * @param status
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getSubcribeByTeacherReview(
			@NonNull Long reviewTeacher, @NonNull Integer page,
			@NonNull Integer limit, @NonNull Integer status)
			throws OperationException;

	/**
	 * 检验想要修改的预约状态[传入]是否与原先表中的状态一致,即防止重复,<br>
	 * 重复则报错中断,不重复则返真
	 * 
	 * @param newStatus 新传入的状态
	 * @param oldStatus 原先表中的状态
	 * @return
	 * @throws OperationException
	 */
	Boolean checkStatusIsDuplicated(@NonNull Integer newStatus,
			@NonNull Integer oldStatus) throws OperationException;

	/**
	 * 学生撤回自己的某份预约,限本周内
	 * 
	 * @param studentNum
	 * @param subscribeID
	 * @param status
	 * @return
	 * @throws OperationException
	 */
	TSubscribe studentCancelSubscribeById(@NonNull Long studentNum,
			@NonNull Long subscribeID, @NonNull Integer status)
			throws OperationException;

	/**
	 * 学生查询自己本周内,指定状态的全部预约
	 * 
	 * @param studentNum
	 * @param status
	 * @return
	 * @throws OperationException
	 */
	List<TSubscribe> getStudentSubscribesForMyself(@NonNull Long studentNum,
			@NonNull Integer status) throws OperationException;

	/**
	 * 学生查询自己在本周内,指定状态的<b>分页</b>预约
	 * 
	 * @param studentNum
	 * @param status
	 * @param pageOrder
	 * @param row
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getStudentSubscribeForMyPagination(
			@NonNull Long studentNum, @NonNull Integer status,
			@NonNull Integer pageOrder, @NonNull Integer row)
			throws OperationException;

	/**
	 * 学生查阅属于自己的本周内的全部预约,分页获取
	 * 
	 * @param studentNum
	 * @param rows
	 * @param pageOrder
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getWeekSubscribesListByStudent(
			@NonNull Long studentNum, @NonNull Integer rows,
			@NonNull Integer pageOrder) throws OperationException;

	/**
	 * 按申请使用日期和时段和申请者,将除传入id以外的预约单皆设为指定状态
	 * 
	 * @param applicant
	 * @param applyUseDate
	 * @param useInterval
	 * @param status
	 * @param subscribeId
	 * @param handlerNum
	 * @return
	 * @throws OperationException
	 */
	Integer reviseStatusExcludeId(@NonNull Long applicant,
			@NonNull Date applyUseDate, @NonNull Integer useInterval,
			@NonNull Integer status, @NonNull Long subscribeId,
			@NonNull Long handlerNum) throws OperationException;

	/**
	 * 获取在本周发出发起申请,及指定状态的预约单集合列表
	 * 
	 * @param userNum 仅限教师或管理员
	 * @param status  状态
	 * @return
	 * @throws OperationException
	 */
	List<TSubscribe> getSubscribeWeekListByStatus(@NonNull Long userNum,
			@NonNull Integer status) throws OperationException;

	/**
	 * 根据申请人+申请使用日+申请使用时段,获取指定状态的预约集合列表
	 * 
	 * @param conditions
	 * @param status
	 * @return
	 */
	List<TSubscribe> getSubscribesByConditions(TSubscribe conditions,
			@NonNull Integer status);

	/**
	 * 根据 subscribeID 获取预约信息
	 * 
	 * @param subscribeID
	 * @return
	 */
	TSubscribe getSubscribeByID(@NonNull Long subscribeID);

	/**
	 * 根据 subscribeID 获取预约信息,三种类型用户皆可查阅
	 * 
	 * @param subscribeID
	 * @param userNum
	 * @return
	 */
	TSubscribe getSubscribeByID(@NonNull Long subscribeID, @NonNull Long userNum)
			throws OperationException;

	/**
	 * 查询某位申请者本周内的全部预约申请,分页展示,限教师查阅
	 * 
	 * @param applicant
	 * @param teacherNum
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getThisWeekSubscribeListByTeacher(
			@NonNull Long applicant, @NonNull Long teacherNum, @NonNull Integer rows,
			@NonNull Integer pageOrder) throws OperationException;

	/**
	 * 查询某位申请者的全部预约申请,分页展示,限管理员查阅
	 * 
	 * @param pageOrder
	 * @param row
	 * @param applicant
	 * @param adminNum
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TSubscribe>> getApplicantSubscribesByAdmin(
			@NonNull Integer pageOrder, @NonNull Integer row,
			@NonNull Long applicant, @NonNull Long adminNum)
			throws OperationException;

	/**
	 * 获取某位申请者本周内的预约申请总数
	 * 
	 * @return
	 */
	Integer getCountSubscribesByApplicant(@NonNull Long applicant)
			throws OperationException;

	/**
	 * </p>
	 * 教师对一张预约申请单进行批复处理
	 * </p>
	 * <p>
	 * 仅仅允许教师操作
	 * </p>
	 * <ol>
	 * <li>教师工号</li>
	 * <li>新状态</li>
	 * <li>待处理的预约单ID</li>
	 * </ol>
	 */
	TSubscribe handleSubscribeStatus(@NonNull Integer status,
			@NonNull Long teacherNum, @NonNull Long subscribeID)
			throws OperationException;

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
	 * (学生)申请使用机房,生成一张预约单<br>
	 * </ul>
	 * 需要手动提交的参数:
	 * <li>学号</li>
	 * <li>申请使用的机房编号</li>
	 * <li>申请使用日期</li>
	 * <li>申请使用时间段</li>
	 * </ul>
	 */
	TSubscribe addNewScuSubscribe(TSubscribe subscribe) throws OperationException;

	/**
	 * 获取上一周的全部预约申请单列表,仅仅允许管理员调阅
	 * 
	 * @param adminNum
	 * @return
	 * @throws OperationException
	 */
	List<TSubscribe> getPreviousWeekSubscribes(@NonNull Long adminNum)
			throws OperationException;

	/**
	 * 获取针对某间机房,其所收到的某种预约状态的申请单数量
	 * 
	 * @param userNum
	 * @param roomNum
	 * @param status
	 * @return
	 * @throws OperationException
	 */
	Integer getCountForStatusInSomeRoom(@Valid @NonNull Long userNum,
			@Valid @NonNull Integer roomNum, @Valid @NonNull Integer status)
			throws OperationException;
}
