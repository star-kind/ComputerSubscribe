package com.computer.subscribe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.response.WebResponse;
import com.computer.subscribe.pojo.vo.SubscribeUserVO;
import com.computer.subscribe.service.IJointQueryService;

/**
 * 
 * @author user
 *
 */
@Controller
@RequestMapping("/JointQueryController")
public class JointQueryController extends BasicController {
	String t = this.getClass().getCanonicalName() + "......\n";

	@Autowired
	private IJointQueryService ijqs;

	// TODO
	/**
	 * http://localhost:8080/subscribe/JointQueryController/retrieveSubscribesOnlyTeacher?page=0&limit=6&roomNum=1
	 * 
	 * 联合查询,一间机房本周内,所收到的预约申请列表集合<br>
	 * 含(审核状态+审核教师工号+申请者学号+申请使用日+申请使用时段)<br>
	 * (申请者姓名+申请者邮箱+审核人姓名+审核人邮箱)
	 * 
	 * @param request
	 * @param page
	 * @param limit
	 * @param roomNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/retrieveSubscribesOnlyTeacher")
	public WebResponse<Pagination<List<SubscribeUserVO>>> retrieveSubscribesOnlyTeacher(
			HttpServletRequest request, @RequestParam("page") Integer page,
			@RequestParam("limit") Integer limit,
			@RequestParam("roomNum") Integer roomNum) {
		System.err.println(t + "retrieveSubscribesOnlyTeacher---page=" + page
				+ "---limit=" + limit + "---room num=" + roomNum);
		Long teacherNum = getLoginDataByToken(request).getUserNum();

		Pagination<List<SubscribeUserVO>> paginationData = ijqs
				.getSubscribesOnlyTeacher(teacherNum, roomNum, page, limit);

		return new WebResponse<Pagination<List<SubscribeUserVO>>>(SUCCESS,
				paginationData);
	}

}
