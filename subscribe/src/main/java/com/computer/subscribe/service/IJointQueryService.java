package com.computer.subscribe.service;

import java.util.List;

import com.computer.subscribe.exception.AttributeNPException;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.exception.ValidatEntityNPException;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.vo.SubscribeUserVO;

/**
 * 
 * @author user
 *
 */
public interface IJointQueryService {
	/**
	 * 分页查询<br>
	 * 一间机房本周内,所收到的预约申请列表集合<br>
	 * 含(申单ID+审核状态+审核教师工号+申请使用日+申请使用时段+机房号)<br>
	 * and(申请者姓名+申请者邮箱+申请者学号)
	 * 
	 * @param teacherNum 限教师查询
	 * @param roomOrder
	 * @param page
	 * @param limit
	 * @return
	 * @throws OperationException
	 * @throws ValidatEntityNPException
	 * @throws AttributeNPException
	 */
	Pagination<List<SubscribeUserVO>> getSubscribesOnlyTeacher(Long teacherNum,
			Integer roomOrder, Integer page, Integer limit)
			throws OperationException, ValidatEntityNPException,AttributeNPException;
}
