package com.computer.subscribe.service;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.TSubscribe;

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
	 * (学生)申请使用机房,生成一张预约单子<br>
	 * </ul>
	 * 需要手动提交的参数:
	 * <li>学号</li>
	 * <li>申请使用的机房编号</li>
	 * <li>申请使用日期</li>
	 * <li>申请使用时间段</li>
	 * </ul>
	 */
	TSubscribe addNewScuSubscribe(TSubscribe subscribe) throws OperationException;
}
