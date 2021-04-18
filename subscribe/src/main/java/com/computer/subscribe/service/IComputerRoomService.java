package com.computer.subscribe.service;

import java.util.List;

import javax.validation.Valid;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.pojo.response.Pagination;

import lombok.NonNull;

/**
 * 机房业务管理接口
 * 
 * @author user
 *
 */
public interface IComputerRoomService {
	/**
	 * 分页列表,获取全部机房信息
	 * 
	 * @param userNum   任意用户
	 * @param pageOrder 页码
	 * @param rows      每页行数
	 * @return
	 * @throws OperationException
	 */
	Pagination<List<TComputerRoom>> getRoomListByPagination(
			@Valid @NonNull Long userNum, @Valid @NonNull Integer pageOrder,
			@Valid @NonNull Integer rows) throws OperationException;

	/**
	 * 为更新信息而构造一个机房对象 <br>
	 * 在2层及以上if-else-if代码块内赋值,会导致NULL<br>
	 * <b>两个包装类引用数据类型之间,最好不要用 == 或 != 比较值是否相等,应当使用equals</b>
	 * 
	 * @param oldRoomInfo
	 * @param roomNewInfo
	 * @return
	 */
	TComputerRoom getConstructDataForRevise(TComputerRoom oldRoomInfo,
			TComputerRoom roomNewInfo);

	/**
	 * 比较实际可用电脑数与电脑总数的大小,如果实际可用电脑数小于电脑总数,则报错,否则无事
	 * 
	 * @param actualAvailable 实际可用电脑数
	 * @param totalQuantity   电脑总数
	 * @throws OperationException
	 */
	void comparisonActualAndTotalComputer(@Valid @NonNull Integer actualAvailable,
			@Valid @NonNull Integer totalQuantity) throws OperationException;

	/**
	 * <b>管理员</b>修改机房数据根据ID<br>
	 * <ul>
	 * <li>机房房间编号</li>
	 * <li>机位位置总数</li>
	 * <li>实际可用电脑数量</li>
	 * <li>是否可用</li>
	 * <li>地点位置</li>
	 * </ul>
	 * 
	 * @param roomNewInfo 新设定之机房数据
	 * @return
	 * @throws OperationException
	 */
	TComputerRoom reviseRoomInfoById(@Valid @NonNull TComputerRoom roomNewInfo)
			throws OperationException;

	/**
	 * 新增一间电脑机房<br>
	 * 机房房间编号;机位位置总数;实际可用电脑数量;是否可用;地点位置;管理员工号[long]
	 * 
	 * @param computerRoom
	 * @return
	 * @throws OperationException
	 */
	TComputerRoom saveNewRoom(@Valid @NonNull TComputerRoom computerRoom)
			throws OperationException;

	/**
	 * 获取机房信息,据机房编号
	 * 
	 * @param roomNum
	 * @param accountNum
	 * @return
	 * @throws OperationException
	 */
	TComputerRoom getComputerRoomByOrder(@NonNull Integer roomNum,
			@NonNull Long accountNum) throws OperationException;

	/**
	 * 获取机房信息,据机房编号<b>[Override]</b>
	 * 
	 * @param roomNum
	 * @return
	 */
	TComputerRoom getComputerRoomByOrder(@NonNull Integer roomNum);

	/**
	 * 根据id获取机房数据
	 * 
	 * @param id
	 * @return
	 * @throws OperationException
	 */
	TComputerRoom getComputerRoomById(@NonNull Integer id) throws OperationException;

	/**
	 * 用户根据id获取机房数据<b>[Override]</b>
	 * 
	 * @param id
	 * @param userNum
	 * @return
	 * @throws OperationException
	 */
	TComputerRoom getComputerRoomById(@NonNull Integer id, @NonNull Long userNum)
			throws OperationException;

	/**
	 * 获取机房信息,据机房地点<b>[Override]</b>
	 * 
	 * @param location
	 * @return
	 */
	TComputerRoom getComputerRoomByLocation(@NonNull String location);

	/**
	 * 获取机房信息,据机房地点
	 * 
	 * @param location
	 * @param accountNum
	 * @return
	 * @throws OperationException
	 */
	TComputerRoom getComputerRoomByLocation(@NonNull String location,
			@NonNull Long accountNum) throws OperationException;
}
