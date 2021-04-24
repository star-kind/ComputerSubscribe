package com.computer.subscribe.controller;

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

import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.service.IComputerRoomService;

@Controller
@RequestMapping("/ComputerRoomController")
public class ComputerRoomController extends BasicController {
	@Autowired
	private IComputerRoomService crs;

	/**
	 * http://localhost:8080/subscribe/ComputerRoomController/getRoomListByPaginAction?userNum=393606924700&pageOrder=0&rows=2
	 * 
	 * @param userNum
	 * @param pageOrder
	 * @param rows
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoomListByPaginAction", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TComputerRoom>>> getRoomListByPaginAction(
			@RequestParam("userNum") @Valid @NotNull Long userNum,
			@RequestParam("pageOrder") @Valid @NotNull Integer pageOrder,
			@RequestParam("rows") @Valid @NotNull Integer rows,
			HttpServletRequest req) {
		printMethod(this.getClass(), "getRoomListByPaginAction", "userNum", userNum,
				"pageOrder", pageOrder, "rows", rows);
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		Pagination<List<TComputerRoom>> pagination = crs
				.getRoomListByPagination(userNum, pageOrder, rows);

		WebResponse<Pagination<List<TComputerRoom>>> response = new WebResponse<Pagination<List<TComputerRoom>>>(
				SUCCESS, pagination);
		return response;
	}

	/**
	 * http://localhost:8080/subscribe/ComputerRoomController/reviseRoomInfoAction?roomNum=41&totalSets=190&availableStatus=1&location=铁山峰顶1236&actAvailableQuantity=70&adminNumOperated=393606924700&id=2
	 * 
	 * @param roomNewInfo
	 * @param adminNum
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reviseRoomInfoAction", method = RequestMethod.GET)
	public WebResponse<TComputerRoom> reviseRoomInfoAction(
			@Valid @NotNull TComputerRoom roomNewInfo,
			@RequestParam("adminNum") @Valid @NotNull Long adminNum,
			HttpServletRequest req) {
		printMethod(this.getClass(), "reviseRoomInfoAction", roomNewInfo.toString());
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		TComputerRoom computerRoom = crs.reviseRoomInfoById(roomNewInfo);
		WebResponse<TComputerRoom> response = new WebResponse<TComputerRoom>(SUCCESS,
				computerRoom);
		return response;
	}

	/**
	 * http://localhost:8080/subscribe/ComputerRoomController/saveNewAction?roomNum=91&totalSets=190&availableStatus=1&location=铁山峰顶&actAvailableQuantity=90&adminNumOperated=393606924700
	 * 
	 * @param computerRoom
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveNewAction", method = RequestMethod.GET)
	public WebResponse<TComputerRoom> saveNewAction(
			@Valid @NotNull TComputerRoom computerRoom, HttpServletRequest req) {
		printMethod(this.getClass(), "saveNewAction", computerRoom.toString());
		// 后期将从令牌中获取关键数据
		// String header = req.getHeader("token");
		// System.err.println("header.token== " + header);
		TComputerRoom newRoom = crs.saveNewRoom(computerRoom);

		WebResponse<TComputerRoom> response = new WebResponse<TComputerRoom>(SUCCESS,
				newRoom);
		return response;
	}

}
