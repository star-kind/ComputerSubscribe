package com.computer.subscribe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.TUser;

public interface JointQueryMapper {
	/**
	 * 
	 * @return
	 */
	List<TUser> getAllUsers();

	/**
	 * 左连分页查询<br>
	 * 一间机房本周内,所收到的预约申请列表集合<br>
	 * 含(申单ID+审核状态+审核教师工号+申请使用日+申请使用时段+机房号)<br>
	 * and(申请者姓名+申请者邮箱+申请者学号)
	 * 
	 * @param roomOrder
	 * @param beginTime
	 * @param endTime
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<TSubscribe> retrieveSubscribesList(@Param("roomOrder") Integer roomOrder,
			@Param("beginTime") String beginTime, @Param("endTime") String endTime,
			@Param("offset") Integer offset, @Param("limit") Integer limit);
}
