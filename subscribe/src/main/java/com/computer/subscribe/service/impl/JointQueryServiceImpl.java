package com.computer.subscribe.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.AttributeNPException;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.exception.ValidatEntityNPException;
import com.computer.subscribe.mapper.JointQueryMapper;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.pojo.vo.SubscribeUserVO;
import com.computer.subscribe.service.IJointQueryService;
import com.computer.subscribe.service.ISubscribeService;
import com.computer.subscribe.service.IUserService;
import com.computer.subscribe.util.PaginationUtils;
import com.computer.subscribe.util.support.DateTimeKits;

/**
 * 
 * @author user
 *
 */
@Service
public class JointQueryServiceImpl implements IJointQueryService {
	@Autowired
	private JointQueryMapper jqMapper;

	@Autowired
	private IUserService ius;

	@Autowired
	private ISubscribeService iss;

	String t = this.getClass().getCanonicalName() + "...\n";
	public static Logger log = Logger.getLogger(JointQueryServiceImpl.class);

	PaginationUtils paginate = PaginationUtils.getInstance();
	DateTimeKits dateKit = DateTimeKits.getInstance();

	@Override
	public Pagination<List<SubscribeUserVO>> getSubscribesOnlyTeacher(
			Long teacherNum, Integer roomOrder, Integer page, Integer limit)
			throws OperationException, ValidatEntityNPException,
			AttributeNPException {
		System.err.println(t + "getSubscribesOnlyTeacher---teacherNum=" + teacherNum
				+ "---roomOrder=" + roomOrder + "---page=" + page + "---limit="
				+ limit);

		ius.checkAccountIsRight(teacherNum, 1);

		// 获取本周首尾-list
		ArrayList<Date> monAndSunList = dateKit.getMonAndSunList();

		// 获取本周首尾日期-string
		String[] dateStrArr = dateKit.getStrArrFromTimeList(monAndSunList);

		// 获取查询所需的 page order
		page = paginate.getPageNum(page);

		// 获取 偏移量
		int offset = paginate.getOffsetByPage(page, limit);

		// 获取 总行数
		Integer idCount = jqMapper.selectCountOfSubscribes(roomOrder, dateStrArr[0],
				dateStrArr[1]);

		// 获取分页数据
		List<SubscribeUserVO> pageList = jqMapper.selectSubscribesList(roomOrder,
				dateStrArr[0], dateStrArr[1], offset, limit);

		Pagination<List<SubscribeUserVO>> pagination = paginate
				.assemblyGeneric(pageList, idCount, limit, page);

		return pagination;
	}

}
