package com.computer.subscribe.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.service.ISubscribeService;

@Controller
@RequestMapping("/SubscribeController")
public class SubscribeController extends BasicController {
	String t = this.getClass().getName() + "---\n";

	@Autowired
	private ISubscribeService iss;

	/**
	 * 教师分页获取本周全部预约单,不限申请者,不限状态和机房
	 * http://localhost:8080/subscribe/SubscribeController/retrieveAllSubscirbeOnWeek?pageOrder=1&rows=10
	 * 
	 * @param req
	 * @param pageOrder
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/retrieveAllSubscirbeOnWeek", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> retrieveAllSubscirbeOnWeek(
			HttpServletRequest req, @RequestParam("pageOrder") Integer pageOrder,
			@RequestParam("rows") Integer rows) {
		LoginData loginData = getLoginDataByToken(req);
		Long teacherNum = loginData.getUserNum();

		printMethod(t, "--getCountByStatusInRoomAction--teacherNum=" + teacherNum,
				",pageOrder=" + pageOrder, ",rows=" + rows);

		Pagination<List<TSubscribe>> pagination = iss
				.getAllSubscirbeOnWeek(pageOrder, rows, teacherNum);

		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * 获取某间机房,其所收到的某种预约状态的申请单统计数量<br>
	 * 
	 * http://localhost:8080/subscribe/SubscribeController/getCountByStatusInRoomAction?status=0&roomNum=3
	 * 
	 * @param userNum 任一帐户
	 * @param status  预约单状态
	 * @param roomNum 机房编号
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCountByStatusInRoomAction", method = RequestMethod.GET)
	public WebResponse<Integer> getCountByStatusInRoomAction(
			@RequestParam("status") @Valid Integer status,
			@RequestParam("roomNum") @Valid Integer roomNum,
			HttpServletRequest req) {

		LoginData loginData = getLoginDataByToken(req);
		Long userNum = loginData.getUserNum();

		printMethod(t, "--getCountByStatusInRoomAction--userNum=" + userNum
				+ ",status=" + status + ",roomNum=" + roomNum);

		Integer result = iss.getCountForStatusInSomeRoom(userNum, roomNum, status);

		return new WebResponse<Integer>(SUCCESS, result);
	}

	/**
	 * 学生撤回自己的预约(:3) <br>
	 * http://localhost:8080/subscribe/SubscribeController/studentCancelSubscribeAction?status=4&subscribeID=71
	 * 
	 * @param studentNum
	 * @param status
	 * @param subscribeID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/studentCancelSubscribeAction", method = RequestMethod.GET)
	public WebResponse<TSubscribe> studentCancelSubscribeAction(
			@RequestParam("status") Integer status,
			@RequestParam("subscribeID") Long subscribeID, HttpServletRequest req) {

		LoginData loginData = getLoginDataByToken(req);
		Long studentNum = loginData.getUserNum();

		printMethod(t, "__studentCancelSubscribeAction__studentNum=" + studentNum
				+ ",status=" + status + ",subscribeID=" + subscribeID);

		TSubscribe subscribe = iss.studentCancelSubscribeById(studentNum,
				subscribeID, status);

		return new WebResponse<TSubscribe>(SUCCESS, subscribe);
	}

	/**
	 * 学生分页查询,本周内自己的指定状态的预约列表 <br>
	 * http://localhost:8080/subscribe/SubscribeController/getStudentSubscribeMyAction?status=1&pageOrder=0&row=3
	 * 
	 * @param studentNum
	 * @param status
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getStudentSubscribeMyAction", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> getStudentSubscribeMyAction(
			@RequestParam("status") Integer status,
			@RequestParam("pageOrder") Integer pageOrder,
			@RequestParam("row") Integer row, HttpServletRequest req) {

		LoginData loginData = getLoginDataByToken(req);
		Long studentNum = loginData.getUserNum();

		printMethod(t, "__getStudentSubscribeMyAction__studentNum=" + studentNum
				+ ",status=" + status + ",pageOrder=" + pageOrder + ",row=" + row);

		Pagination<List<TSubscribe>> pagination = iss
				.getStudentSubscribeForMyPagination(studentNum, status, pageOrder,
						row);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryWeekListByStudentAction?pageOrder=0&rows=3
	 * <br>
	 * 
	 * 学生分页获取本周内自己全部的预约申请单
	 * 
	 * <br>
	 * <ol>
	 * <li>studentNum--学号,限学生</li>
	 * <li>rows--每页展示行数"</li>
	 * <li>pageOrder--页码</li>
	 * </ol>
	 * 
	 * @param studentNum
	 * @param rows
	 * @param pageOrder
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryWeekListByStudentAction", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> queryWeekListByStudentAction(
			@RequestParam("rows") @Valid Integer rows,
			@RequestParam("pageOrder") @Valid Integer pageOrder,
			HttpServletRequest req) {
		LoginData loginData = getLoginDataByToken(req);
		Long studentNum = loginData.getUserNum();

		printMethod(t, "__queryWeekListByTeacherAction__studentNum=" + studentNum
				+ ",pageOrder=" + pageOrder + ",rows=" + rows);

		Pagination<List<TSubscribe>> pagination = iss
				.getWeekSubscribesListByStudent(studentNum, rows, pageOrder);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryAllSubscribesByAdminAction?applicant=105170048&pageOrder=0&rows=3
	 * <br>
	 * 管理员分页获取某位学生全部的预约申请单 <br>
	 * <ol>
	 * <li>applicant--预约者学号,限学生</li>
	 * <li>teacherNum--管理员工号,限管理员</li>
	 * <li>rows--每页展示行数"</li>
	 * <li>pageOrder--页码</li>
	 * </ol>
	 * 
	 * @param applicant
	 * @param adminNum
	 * @param rows
	 * @param pageOrder
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAllSubscribesByAdminAction", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> queryAllSubscribesByAdminAction(
			@RequestParam("applicant") @Valid Long applicant,
			@RequestParam("rows") @Valid Integer rows,
			@RequestParam("pageOrder") @Valid Integer pageOrder,
			HttpServletRequest req) {
		LoginData loginData = getLoginDataByToken(req);
		Long adminNum = loginData.getUserNum();

		printMethod(t,
				"__queryWeekListByTeacherAction__applicant=" + applicant
						+ ",adminNum=" + adminNum + ",pageOrder=" + pageOrder
						+ ",rows=" + rows);

		Pagination<List<TSubscribe>> pagination = iss
				.getApplicantSubscribesByAdmin(pageOrder, rows, applicant, adminNum);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryWeekListByTeacherAction?applicant=1889970&pageOrder=1&rows=4
	 *
	 * <br>
	 * 分页获取本周内某位学生全部的预约申请单 <br>
	 * <ol>
	 * <li>applicant--学号[限学生]</li>
	 * <li>teacherNum--教师工号[限教师]</li>
	 * <li>rows--每页展示行数"</li>
	 * <li>pageOrder--页码</li>
	 * </ol>
	 * 
	 * @param applicant
	 * @param teacherNum
	 * @param rows
	 * @param pageOrder
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryWeekListByTeacherAction", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> queryWeekListByTeacherAction(
			@RequestParam("applicant") @Valid Long applicant,
			@RequestParam("rows") @Valid Integer rows,
			@RequestParam("pageOrder") @Valid Integer pageOrder,
			HttpServletRequest req) {
		LoginData loginData = getLoginDataByToken(req);
		Long teacherNum = loginData.getUserNum();

		printMethod(t,
				"__queryWeekListByTeacherAction__applicant=" + applicant
						+ ",teacherNum=" + teacherNum + ",pageOrder=" + pageOrder
						+ ",rows=" + rows);

		Pagination<List<TSubscribe>> pagination = iss
				.getThisWeekSubscribeListByTeacher(applicant, teacherNum, rows,
						pageOrder);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * 教师分页获取,某间机房在本周内,收到的预约申请列表(不限定审核状态)<br>
	 * http://localhost:8080/subscribe/SubscribeController/retrieveSubscribeByRoomReceived?roomNum=1&pageOrder=1&limit=2
	 * 
	 * @param roomNum
	 * @param pageOrder
	 * @param limit
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/retrieveSubscribeByRoomReceived", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> retrieveSubscribeByRoomReceived(
			@RequestParam("roomNum") @Valid Integer roomNum,
			@RequestParam("pageOrder") @Valid Integer pageOrder,
			@RequestParam("limit") @Valid Integer limit, HttpServletRequest req) {
		System.err.println(t + "retrieveSubscribeByRoomReceived..pageOrder="
				+ pageOrder + "..limit=" + limit + "..roomNum=" + roomNum);

		Long teacherNum = getLoginDataByToken(req).getUserNum();

		Pagination<List<TSubscribe>> pagination = iss.getRoomSubscribesListByTeacher(
				teacherNum, pageOrder, limit, roomNum);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/retriSubscribeByRoomAndStatusReceiv?roomNum=1&pageOrder=1&limit=2&status=0
	 * 
	 * 教师分页获取,某间机房在本周内,收到的预约申请列表(指定审核状态)
	 * 
	 * @param roomNum
	 * @param pageOrder
	 * @param limit
	 * @param status
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/retriSubscribeByRoomAndStatusReceiv", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> retriSubscribeByRoomAndStatusReceiv(
			@RequestParam("roomNum") @Valid Integer roomNum,
			@RequestParam("pageOrder") @Valid Integer pageOrder,
			@RequestParam("limit") @Valid Integer limit,
			@RequestParam("status") @Valid Integer status, HttpServletRequest req) {
		System.err.println(t + "retriSubscribeByRoomAndStatusReceiv..pageOrder="
				+ pageOrder + "..limit=" + limit + "..status=" + status
				+ "..roomNum=" + roomNum);

		Long teacherNum = getLoginDataByToken(req).getUserNum();

		Pagination<List<TSubscribe>> pagination = iss.getRoomSubscribesListByTeacher(
				teacherNum, pageOrder, limit, roomNum, status);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/getSubscribeByIdAction?subscribeID=112
	 * 
	 * <p>
	 * 获取某张预约申请单的信息
	 * </p>
	 * 
	 * <br>
	 * <ol>
	 * <li>userNum--工号/学号</li>
	 * <li>subscribeID--预约单id</li>
	 * </ol>
	 * 
	 * @param userNum
	 * @param subscribeID
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSubscribeByIdAction", method = RequestMethod.GET)
	public WebResponse<TSubscribe> getSubscribeByIdAction(
			@RequestParam("subscribeID") @Valid Long subscribeID,
			HttpServletRequest req) {

		LoginData loginData = getLoginDataByToken(req);
		Long userNum = loginData.getUserNum();

		printMethod(t, "__getSubscribeByIdAction__userNum=" + userNum
				+ ",subscribeID=" + subscribeID);

		TSubscribe subscribe = iss.getSubscribeByID(subscribeID, userNum);
		return new WebResponse<TSubscribe>(SUCCESS, subscribe);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/handleSubscribeStatusAction?status=0&subscribeID=12
	 * 
	 * <p>
	 * 教师审核处理某张预约申请单
	 * </p>
	 * 
	 * <br>
	 * <ol>
	 * <li>status--状态</li>
	 * <li>teacherNum--工号,限教师</li>
	 * <li>subscribeID--预约单id</li>
	 * </ol>
	 * 
	 * @param status
	 * @param teacherNum
	 * @param subscribeID
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/handleSubscribeStatusAction", method = RequestMethod.GET)
	public WebResponse<TSubscribe> handleSubscribeStatusAction(
			@RequestParam("status") @Valid Integer status,
			@RequestParam("subscribeID") @Valid Long subscribeID,
			HttpServletRequest req) {

		LoginData loginData = getLoginDataByToken(req);
		Long teacherNum = loginData.getUserNum();

		printMethod(t, "__handleSubscribeStatusAction__teacherNum=" + teacherNum
				+ ",status=" + status + ",subscribeID=" + subscribeID);

		TSubscribe subscribe = iss.handleSubscribeStatus(status, teacherNum,
				subscribeID);
		return new WebResponse<TSubscribe>(SUCCESS, subscribe);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/getPrevWeekSubscribesListAction
	 * <br>
	 * 查询上周全部的预约申请单列表 <br>
	 * <ol>
	 * <li>adminNum--工号,限管理员</li>
	 * </ol>
	 * 
	 * @param adminNum
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPrevWeekSubscribesListAction", method = RequestMethod.GET)
	public WebResponse<List<TSubscribe>> getPrevWeekSubscribesListAction(
			HttpServletRequest req) {
		LoginData loginData = getLoginDataByToken(req);
		Long adminNum = loginData.getUserNum();

		printMethod(t, "__queryByStatusAction_adminNum=" + adminNum);

		List<TSubscribe> list = iss.getPreviousWeekSubscribes(adminNum);
		return new WebResponse<List<TSubscribe>>(SUCCESS, list);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryByStatusAction?status=0
	 * 
	 * 查询本周指定状态的预约申请单列表 <br>
	 * <ol>
	 * <li>userNum--工号,限教师和管理员</li>
	 * <li>status--预约的审核状态</li>
	 * </ol>
	 * 
	 * @param userNum
	 * @param status
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryByStatusAction", method = RequestMethod.GET)
	public WebResponse<List<TSubscribe>> queryByStatusAction(
			@RequestParam("status") @Valid Integer status, HttpServletRequest req) {

		LoginData loginData = getLoginDataByToken(req);
		Long userNum = loginData.getUserNum();

		printMethod(t, "__queryByStatusAction_userNum=" + userNum,
				",status=" + status);

		List<TSubscribe> list = iss.getSubscribeListByStatus(status, userNum);
		return new WebResponse<List<TSubscribe>>(SUCCESS, list);
	}

	/**
	 * 教师分页查阅,本周内自己经手过的预约申请单,不限预约状态<br>
	 * http://localhost:8080/subscribe/SubscribeController/retrieveHandleByTeacher?pageOrder=0&rows=3
	 * 
	 * @param req
	 * @param pageOrder
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/retrieveHandleByTeacher", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> retrieveHandleByTeacher(
			HttpServletRequest req, @RequestParam("pageOrder") Integer pageOrder,
			@RequestParam("rows") Integer rows) {
		System.err.println(t + "--retrieveHandleByTeacherAction--pageOrder="
				+ pageOrder + ",rows=" + rows);
		LoginData loginData = getLoginDataByToken(req);
		Long teacherReviewer = loginData.getUserNum();

		Pagination<List<TSubscribe>> pagination = iss
				.getSubcribeByTeacherReview(teacherReviewer, pageOrder, rows);

		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * 教师分页查阅,本周内自己经手过的预约申请单,指定预约状态<br>
	 * http://localhost:8080/subscribe/SubscribeController/retrieveHandleByTeacherAndStatus?page=0&limit=3&status=0
	 * 
	 * @param req
	 * @param page
	 * @param limit
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/retrieveHandleByTeacherAndStatus", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> retrieveHandleByTeacherAndStatus(
			HttpServletRequest req, @RequestParam("page") Integer page,
			@RequestParam("limit") Integer limit,
			@RequestParam("status") Integer status) {
		System.err.println(t + "--retrieveHandleByTeacherAndStatus--page=" + page
				+ ",limit=" + limit + ",status=" + status);
		LoginData loginData = getLoginDataByToken(req);
		Long teacherReviewer = loginData.getUserNum();

		Pagination<List<TSubscribe>> pagination = iss
				.getSubcribeByTeacherReview(teacherReviewer, page, limit, status);

		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/addNewApplyAction?useInterval=3&roomNum=11&applyUseDate=2019-12-11
	 * 
	 * <p>
	 * 新增一张机房的使用申请单
	 * </p>
	 * <p>
	 * 参数为:学号,机房编号,具体使用日期,使用时间段
	 * </p>
	 * <br>
	 * <ul>
	 * <li>applyUseDate--具体使用日期</li>
	 * <li>useInterval--使用时间段</li>
	 * <li>roomNum--机房编号</li>
	 * <li>applicant--学号[不用显式输入]</li>
	 * </ul>
	 * 
	 * @param useInterval
	 * @param roomNum
	 * @param applyUseDate
	 * @param req
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/addNewApplyAction", method = RequestMethod.GET)
	public WebResponse<TSubscribe> addNewApplyAction(
			@RequestParam("useInterval") @Valid @NotNull Integer useInterval,
			@RequestParam("roomNum") @Valid @NotNull Integer roomNum,
			@RequestParam("applyUseDate") @Valid @NotNull String applyUseDate,
			HttpServletRequest req) throws ParseException {
		printMethod(t, "addNewApplyAction..useInterval=" + useInterval,
				"roomNum=" + roomNum, "applyUseDate=" + applyUseDate);

		TSubscribe sub = new TSubscribe();
		sub.setUseInterval(useInterval);
		sub.setRoomNum(roomNum);

		// 转换为时间类型
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date useDate = format.parse(applyUseDate);
		sub.setApplyUseDate(useDate);

		// 有选择检验参数的NPE
		validFieldNPExUtil.centralize(sub, "applyUseDate", "useInterval", "roomNum");

		LoginData loginData = getLoginDataByToken(req);
		sub.setApplicant(loginData.getUserNum());

		TSubscribe res = iss.addNewScuSubscribe(sub);
		return new WebResponse<TSubscribe>(SUCCESS, res);
	}

}
