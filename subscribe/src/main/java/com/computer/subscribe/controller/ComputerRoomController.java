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

import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.service.IComputerRoomService;

@Controller
@RequestMapping("/ComputerRoomController")
public class ComputerRoomController extends BasicController {
	String t = this.getClass().getCanonicalName() + "......\n";

	@Autowired
	private IComputerRoomService crs;

	// TODO
	// 联合查询,一间机房本周内,所收到的预约申请列表集合,含(审核状态+审核教师工号+申请者学号+申请使用日+申请使用时段)+(申请者姓名+申请者邮箱+审核人姓名+审核人邮箱)

	/**
	 * 分页列表,获取全部机房信息,不限制帐号类型<br>
	 * 
	 * http://localhost:8080/subscribe/ComputerRoomController/getRoomListByPaginAction?pageOrder=0&rows=2
	 * 
	 * @param userNum
	 * @param pageOrder
	 * @param rows
	 * @param req       userNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoomListByPaginAction", method = RequestMethod.GET)
	public WebResponse<Pagination<List<TComputerRoom>>> getRoomListByPaginAction(
			@RequestParam("pageOrder") @Valid @NotNull Integer pageOrder,
			@RequestParam("rows") @Valid @NotNull Integer rows,
			HttpServletRequest req) {
		LoginData loginData = getLoginDataByToken(req);
		Long userNum = loginData.getUserNum();

		printMethod(t, "getRoomListByPaginAction", "userNum", userNum, "pageOrder",
				pageOrder, "rows", rows);

		Pagination<List<TComputerRoom>> pagination = crs
				.getRoomListByPagination(userNum, pageOrder, rows);

		WebResponse<Pagination<List<TComputerRoom>>> response = new WebResponse<Pagination<List<TComputerRoom>>>(
				SUCCESS, pagination);
		return response;
	}

	/**
	 * 
	 * 管理员修改机房数据,根据房间ID
	 * 
	 * <ol>
	 * 可修改:
	 * <li>机房编号</li>
	 * <li>是否可用</li>
	 * <li>机位总数</li>
	 * <li>实际可用数</li>
	 * <li>地址</li>
	 * </ol>
	 * 
	 * http://localhost:8080/subscribe/ComputerRoomController/reviseRoomInfoAction?roomNum=41&totalSets=190&availableStatus=1&location=铁山峰顶1236&actAvailableQuantity=70&id=2
	 * 
	 * @param roomNewInfo
	 * @param req         AdminNumOperated
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reviseRoomInfoAction", method = RequestMethod.GET)
	public WebResponse<TComputerRoom> reviseRoomInfoAction(
			@Valid @NotNull TComputerRoom roomNewInfo, HttpServletRequest req) {
		LoginData loginData = getLoginDataByToken(req);
		roomNewInfo.setAdminNumOperated(loginData.getUserNum());

		// 检验实体字段NPE
		validFieldNPExUtil.centralize(roomNewInfo, "roomNum", "totalSets",
				"availableStatus", "adminNumOperated", "actAvailableQuantity",
				"location");

		printMethod(t, "reviseRoomInfoAction", roomNewInfo);
		TComputerRoom computerRoom = crs.reviseRoomInfoById(roomNewInfo);

		WebResponse<TComputerRoom> response = new WebResponse<TComputerRoom>(SUCCESS,
				computerRoom);
		return response;
	}

	/**
	 * <p>
	 * 新增一间电脑机房
	 * </p>
	 * <ul>
	 * <li>机房房间编号</li>
	 * <li>机位位置总数</li>
	 * <li>实际可用电脑数量</li>
	 * <li>是否可用</li>
	 * <li>地点位置</li>
	 * <li>管理员工号[long-adminNumOperated]-隐式</li>
	 * </ul>
	 * 
	 * http://localhost:8080/subscribe/ComputerRoomController/saveNewAction?roomNum=91&totalSets=190&availableStatus=1&location=铁山峰顶&actAvailableQuantity=90
	 * 
	 * @param computerRoom
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveNewAction", method = RequestMethod.GET)
	public WebResponse<TComputerRoom> saveNewAction(
			@Valid @NotNull TComputerRoom computerRoom, HttpServletRequest req) {
		LoginData loginData = getLoginDataByToken(req);
		computerRoom.setAdminNumOperated(loginData.getUserNum());

		printMethod(t, "saveNewAction", computerRoom.toString());

		// 检验实体字段NPE
		validFieldNPExUtil.centralize(computerRoom, "roomNum", "totalSets",
				"availableStatus", "adminNumOperated", "actAvailableQuantity",
				"location");

		TComputerRoom newRoom = crs.saveNewRoom(computerRoom);

		WebResponse<TComputerRoom> response = new WebResponse<TComputerRoom>(SUCCESS,
				newRoom);
		return response;
	}

}
