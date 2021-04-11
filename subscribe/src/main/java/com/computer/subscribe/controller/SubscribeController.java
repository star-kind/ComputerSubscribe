package com.computer.subscribe.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.service.ISubscribeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "/SubscribeController")
@Controller
@RequestMapping("/SubscribeController")
public class SubscribeController extends BasicController {

	@Autowired
	private ISubscribeService iss;

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
	@ApiOperation(value = "新增一张机房的使用申请单", notes = "参数为:学号,机房编号,具体使用日期,使用时间段", httpMethod = "GET")
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
