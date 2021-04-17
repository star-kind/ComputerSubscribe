package com.computer.subscribe.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.service.ISubscribeService;

import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/SubscribeController")
public class SubscribeController extends BasicController {

	@Autowired
	private ISubscribeService iss;

	/**
	 * 学生撤回自己的预约(:4) <br>
	 * http://localhost:8080/subscribe/SubscribeController/studentCancelSubscribeAction?studentNum=1889970&status=4&subscribeID=71
	 * 
	 * @param studentNum
	 * @param status
	 * @param subscribeID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/studentCancelSubscribeAction", method = RequestMethod.GET)
	public WebResponse<TSubscribe> studentCancelSubscribeAction(
			@RequestParam("studentNum") Long studentNum,
			@RequestParam("status") Integer status,
			@RequestParam("subscribeID") Long subscribeID, HttpServletRequest req) {
		System.err.println(this.getClass()
				+ "__studentCancelSubscribeAction__studentNum=" + studentNum
				+ ",status=" + status + ",subscribeID=" + subscribeID);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		TSubscribe subscribe = iss.studentCancelSubscribeById(studentNum,
				subscribeID, status);

		return new WebResponse<TSubscribe>(SUCCESS, subscribe);
	}

	/**
	 * 学生分页查询,本周内指定状态的预约列表 <br>
	 * 
	 * http://localhost:8080/subscribe/SubscribeController/getStudentSubscribeMyAction?studentNum=1889970&status=1&pageOrder=0&row=3
	 * 
	 * @param studentNum
	 * @param status
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getStudentSubscribeMyAction", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TSubscribe>>> getStudentSubscribeMyAction(
			@RequestParam("studentNum") Long studentNum,
			@RequestParam("status") Integer status,
			@RequestParam("pageOrder") Integer pageOrder,
			@RequestParam("row") Integer row, HttpServletRequest req) {
		System.err.println(this.getClass()
				+ "__getStudentSubscribeMyAction__studentNum=" + studentNum
				+ ",status=" + status + ",pageOrder=" + pageOrder + ",row=" + row);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		Pagination<List<TSubscribe>> pagination = iss
				.getStudentSubscribeForMyPagination(studentNum, status, pageOrder,
						row);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryWeekListByStudentAction?studentNum=105170048&pageOrder=0&rows=3
	 * 
	 * @param studentNum
	 * @param rows
	 * @param pageOrder
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryWeekListByStudentAction", method = RequestMethod.GET)
//	@ApiOperation(value = "学生获取本周内自己全部的预约申请单", notes = "参数为:学号,每页展示行数,页码", httpMethod = "GET")
	public WebResponse<Pagination<List<TSubscribe>>> queryWeekListByStudentAction(
			@RequestParam("studentNum") @ApiParam("学号") @Valid Long studentNum,
			@RequestParam("rows") @ApiParam("每页展示行数") @Valid Integer rows,
			@RequestParam("pageOrder") @ApiParam("页码") @Valid Integer pageOrder,
			HttpServletRequest req) {
		System.err.println(
				this.getClass() + "__queryWeekListByTeacherAction__studentNum="
						+ studentNum + ",pageOrder=" + pageOrder + ",rows=" + rows);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		Pagination<List<TSubscribe>> pagination = iss
				.getWeekSubscribesListByStudent(studentNum, rows, pageOrder);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryAllSubscribesByAdminAction?applicant=105170048&adminNum=393606924700&pageOrder=0&rows=3
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
//	@ApiOperation(value = "获取某位学生全部的预约申请单", notes = "参数为:工号[限管理员],预约者的学号", httpMethod = "GET")
	public WebResponse<Pagination<List<TSubscribe>>> queryAllSubscribesByAdminAction(
			@RequestParam("applicant") @ApiParam("学号") @Valid Long applicant,
			@RequestParam("adminNum") @ApiParam("管理员工号") @Valid Long adminNum,
			@RequestParam("rows") @ApiParam("每页展示行数") @Valid Integer rows,
			@RequestParam("pageOrder") @ApiParam("页码") @Valid Integer pageOrder,
			HttpServletRequest req) {
		System.err.println(
				this.getClass() + "__queryWeekListByTeacherAction__applicant="
						+ applicant + ",adminNum=" + adminNum + ",pageOrder="
						+ pageOrder + ",rows=" + rows);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		Pagination<List<TSubscribe>> pagination = iss
				.getApplicantSubscribesByAdmin(pageOrder, rows, applicant, adminNum);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryWeekListByTeacherAction?applicant=1889970&teacherNum=41105048&pageOrder=1&rows=4
	 * 
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
//	@ApiOperation(value = "获取本周内某位学生全部的预约申请单", notes = "参数为:工号[限教师],预约者的学号", httpMethod = "GET")
	public WebResponse<Pagination<List<TSubscribe>>> queryWeekListByTeacherAction(
			@RequestParam("applicant") @ApiParam("学号") @Valid Long applicant,
			@RequestParam("teacherNum") @ApiParam("教师工号") @Valid Long teacherNum,
			@RequestParam("rows") @ApiParam("每页展示行数") @Valid Integer rows,
			@RequestParam("pageOrder") @ApiParam("页码") @Valid Integer pageOrder,
			HttpServletRequest req) {
		System.err.println(
				this.getClass() + "__queryWeekListByTeacherAction__applicant="
						+ applicant + ",teacherNum=" + teacherNum + ",pageOrder="
						+ pageOrder + ",rows=" + rows);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		Pagination<List<TSubscribe>> pagination = iss
				.getThisWeekSubscribeListByTeacher(applicant, teacherNum, rows,
						pageOrder);
		return new WebResponse<Pagination<List<TSubscribe>>>(SUCCESS, pagination);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/getSubscribeByIdAction?userNum=15165156051&subscribeID=112
	 * 
	 * @param userNum
	 * @param subscribeID
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSubscribeByIdAction", method = RequestMethod.GET)
//	@ApiOperation(value = "获取某张预约申请单的信息", notes = "参数为:工号/学号,预约单id", httpMethod = "GET")
	public WebResponse<TSubscribe> getSubscribeByIdAction(
			@RequestParam("userNum") @ApiParam("工号/学号") @Valid Long userNum,
			@RequestParam("subscribeID") @ApiParam("预约单id") @Valid Long subscribeID,
			HttpServletRequest req) {
		System.err.println(this.getClass() + "__getSubscribeByIdAction__userNum="
				+ userNum + ",subscribeID=" + subscribeID);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		TSubscribe subscribe = iss.getSubscribeByID(subscribeID, userNum);
		return new WebResponse<TSubscribe>(SUCCESS, subscribe);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/handleSubscribeStatusAction?status=0&teacherNum=3999706924700&subscribeID=12
	 * 
	 * @param status
	 * @param teacherNum
	 * @param subscribeID
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/handleSubscribeStatusAction", method = RequestMethod.GET)
//	@ApiOperation(value = "审核处理某张预约申请单", notes = "参数为:状态,工号[仅限教师],预约单id", httpMethod = "GET")
	public WebResponse<TSubscribe> handleSubscribeStatusAction(
			@RequestParam("status") @ApiParam("状态") @Valid Integer status,
			@RequestParam("teacherNum") @ApiParam("工号,限教师") @Valid Long teacherNum,
			@RequestParam("subscribeID") @ApiParam("预约单id") @Valid Long subscribeID,
			HttpServletRequest req) {
		System.err.println(this.getClass()
				+ "__handleSubscribeStatusAction__teacherNum=" + teacherNum
				+ ",status=" + status + ",subscribeID=" + subscribeID);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		TSubscribe subscribe = iss.handleSubscribeStatus(status, teacherNum,
				subscribeID);
		return new WebResponse<TSubscribe>(SUCCESS, subscribe);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/getPrevWeekSubscribesListAction?adminNum=393606924700
	 * 
	 * @param adminNum
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPrevWeekSubscribesListAction", method = RequestMethod.GET)
//	@ApiOperation(value = "查询上本周全部的预约申请单列表", notes = "参数为:工号[仅限管理员]", httpMethod = "GET")
	public WebResponse<List<TSubscribe>> getPrevWeekSubscribesListAction(
			@RequestParam("adminNum") @ApiParam("工号,限管理员") @Valid Long adminNum,
			HttpServletRequest req) {
		System.err.println(
				this.getClass() + "__queryByStatusAction_adminNum=" + adminNum);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.out.println("header.token== " + header);
		List<TSubscribe> list = iss.getPreviousWeekSubscribes(adminNum);
		return new WebResponse<List<TSubscribe>>(SUCCESS, list);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/queryByStatusAction?userNum=156168541&status=0
	 * 
	 * @param userNum
	 * @param status
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryByStatusAction", method = RequestMethod.GET)
//	@ApiOperation(value = "查询本周指定状态的预约申请单列表", notes = "参数为:工号[仅限教师和管理员],审核状态", httpMethod = "GET")
	public WebResponse<List<TSubscribe>> queryByStatusAction(
			@RequestParam("userNum") @ApiParam("工号,限教师和管理员") @Valid Long userNum,
			@RequestParam("status") @ApiParam("预约的审核状态") @Valid Integer status,
			HttpServletRequest req) {
		System.err.println(this.getClass() + "__queryByStatusAction_userNum="
				+ userNum + ",status=" + status);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.out.println("header.token== " + header);
		List<TSubscribe> list = iss.getSubscribeListByStatus(status, userNum);
		return new WebResponse<List<TSubscribe>>(SUCCESS, list);
	}

	/**
	 * http://localhost:8080/subscribe/SubscribeController/addNewApplyAction?applicant=156168541&useInterval=3&roomNum=11&applyUseDate=2019-12-11
	 * 
	 * 
	 * 
	 * @param subscribe
	 * @param req
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/addNewApplyAction", method = RequestMethod.GET)
//	@ApiOperation(value = "新增一张机房的使用申请单", notes = "参数为:学号,机房编号,具体使用日期,使用时间段", httpMethod = "GET")
	public WebResponse<TSubscribe> addNewApplyAction(
			@RequestParam("applyUseDate") @ApiParam("具体使用日期") @Valid String applyUseDate,
			@RequestParam("useInterval") @ApiParam("使用时间段") @Valid Integer useInterval,
			@RequestParam("roomNum") @ApiParam("机房编号") @Valid Integer roomNum,
			@RequestParam("applicant") @ApiParam("学号") @Valid Long applicant,
			HttpServletRequest req) throws ParseException {
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.out.println("header.token== " + header);

		System.err.println(this.getClass() + "+++useDate==" + applyUseDate
				+ ",useInterval==" + useInterval + ",roomNum==" + roomNum
				+ ",studentNum==" + applicant);

		logger.info("useDate==" + applyUseDate + ",useInterval==" + useInterval
				+ ",roomNum==" + roomNum + ",studentNum==" + applicant);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date useTimeDate = format.parse(applyUseDate);

		TSubscribe sub = new TSubscribe();
		sub.setApplicant(applicant);
		sub.setApplyUseDate(useTimeDate);
		sub.setUseInterval(useInterval);
		sub.setRoomNum(roomNum);

		TSubscribe subscribe1 = iss.addNewScuSubscribe(sub);
		return new WebResponse<TSubscribe>(SUCCESS, subscribe1);
	}

}
