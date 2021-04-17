package com.computer.subscribe.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computer.subscribe.exception.ExceptionsEnum;
import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.mapper.TComputerRoomMapper;
import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.pojo.TComputerRoomExample;
import com.computer.subscribe.pojo.TComputerRoomExample.Criteria;
import com.computer.subscribe.pojo.response.Pagination;
import com.computer.subscribe.service.IComputerRoomService;
import com.computer.subscribe.service.ISubscribeService;
import com.computer.subscribe.service.IUserService;
import com.computer.subscribe.util.PaginationUtils;
import com.computer.subscribe.util.support.DateTimeKits;

@Service
public class ComputerRoomServiceImpl implements IComputerRoomService {
	public static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private TComputerRoomMapper roomMapper;

	@Autowired
	private IUserService ius;

	@Autowired
	private ISubscribeService iss;

	DateTimeKits dateTimeKits = DateTimeKits.getInstance();

	PaginationUtils paginationUtil = PaginationUtils.getInstance();

	@Override
	public TComputerRoom saveNewRoom(TComputerRoom computerRoom)
			throws OperationException {
		System.err.println(this.getClass() + "--saveNewRoom--computerRoom--"
				+ computerRoom.toString());

		// 校验管理员的存在与权限
		ius.checkAdminPrivilege(computerRoom.getAdminNumOperated());

		TComputerRoom room = this.getComputerRoomByOrder(computerRoom.getRoomNum());
		TComputerRoom room2 = this
				.getComputerRoomByLocation(computerRoom.getLocation());

		// 机房编号是否重复
		if (room != null) {
			String description = ExceptionsEnum.ROOM_NUM_DUPLICATION_ERROR
					.getDescription();
			logger.info(description);
			System.err.println(this.getClass()
					+ "--saveNewRoom--actual_cannot_more_than_toal--description--"
					+ description);
			throw new OperationException(description);
		}

		// 机房地点是否重复
		if (room2 != null) {
			String description = ExceptionsEnum.LOCATION_DUPLICATION_ERROR
					.getDescription();
			logger.info(description);
			System.err.println(this.getClass()
					+ "--saveNewRoom--actual_cannot_more_than_toal--description--"
					+ description);
			throw new OperationException(description);
		}

		// 实际可用电脑数不得大于电脑总数
		this.comparisonActualAndTotalComputer(computerRoom.getActAvailableQuantity(),
				computerRoom.getTotalSets());

		computerRoom.setOperatedTime(new Date());

		int affect = roomMapper.insert(computerRoom);

		System.err.println(this.getClass() + "--saveNewRoom--affect--" + affect);
		System.err.println(this.getClass() + "--saveNewRoom--ReTurn--computerRoom--"
				+ computerRoom);

		return computerRoom;
	}

	@Override
	public TComputerRoom getComputerRoomByOrder(Integer roomNum, Long accountNum)
			throws OperationException {
		System.err.println(this.getClass() + "--getComputerRoomByOrder--roomNum="
				+ roomNum + "--accountNum=" + accountNum);

		ius.checkUserExist(accountNum);

		TComputerRoomExample example = new TComputerRoomExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoomNumEqualTo(roomNum);
		List<TComputerRoom> list = roomMapper.selectByExample(example);

		if (list.isEmpty()) {
			String description = ExceptionsEnum.COMPUTER_ROOM_NOT_EXIST
					.getDescription();
			logger.info(description);
			System.err.println(this.getClass()
					+ "--saveNewRoom--COMPUTER_ROOM_NOT_EXIST--description--"
					+ description);
			throw new OperationException(description);
		}

		TComputerRoom computerRoom = list.get(0);
		System.err.println(this.getClass()
				+ "--getComputerRoomByOrder--computerRoom=" + computerRoom);

		return computerRoom;
	}

	@Override
	public TComputerRoom getComputerRoomByLocation(String location, Long accountNum)
			throws OperationException {
		System.err.println(this.getClass() + "--getComputerRoomByLocation--location="
				+ location + "--accountNum=" + accountNum);

		ius.checkUserExist(accountNum);

		TComputerRoomExample example = new TComputerRoomExample();
		Criteria criteria = example.createCriteria();
		criteria.andLocationEqualTo(location);
		List<TComputerRoom> list = roomMapper.selectByExample(example);

		if (list.isEmpty()) {
			String description = ExceptionsEnum.COMPUTER_ROOM_NOT_EXIST
					.getDescription();
			logger.info(description);
			System.err.println(this.getClass()
					+ "--getComputerRoomByLocation--COMPUTER_ROOM_NOT_EXIST--description--"
					+ description);
			throw new OperationException(description);
		}

		TComputerRoom computerRoom = list.get(0);
		System.err.println(this.getClass()
				+ "--getComputerRoomByLocation--computerRoom=" + computerRoom);

		return computerRoom;
	}

	@Override
	public TComputerRoom getComputerRoomByOrder(Integer roomNum) {
		System.err.println(this.getClass()
				+ "--getComputerRoomByOrder-[Override]--roomNum=" + roomNum);

		TComputerRoomExample example = new TComputerRoomExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoomNumEqualTo(roomNum);
		List<TComputerRoom> list = roomMapper.selectByExample(example);

		if (!list.isEmpty()) {
			TComputerRoom computerRoom = list.get(0);
			System.err.println(this.getClass()
					+ "--getComputerRoomByOrder-[Override]--computerRoom="
					+ computerRoom);
			return computerRoom;
		}

		return null;
	}

	@Override
	public TComputerRoom getComputerRoomByLocation(String location) {
		System.err.println(this.getClass()
				+ "--getComputerRoomByLocation-[Override]--location=" + location);

		TComputerRoomExample example = new TComputerRoomExample();
		Criteria criteria = example.createCriteria();
		criteria.andLocationEqualTo(location);
		List<TComputerRoom> list = roomMapper.selectByExample(example);

		if (!list.isEmpty()) {
			TComputerRoom computerRoom = list.get(0);
			System.err.println(this.getClass()
					+ "--getComputerRoomByLocation-[Override]--computerRoom="
					+ computerRoom);

			return computerRoom;
		}

		return null;
	}

	@Override
	public TComputerRoom reviseRoomInfoById(TComputerRoom roomNewInfo, Long adminNum)
			throws OperationException {
		System.err.println(this.getClass() + "--reviseRoomInfoById--roomNewInfo="
				+ roomNewInfo.toString() + ",adminNum=" + adminNum);

		ius.checkAdminPrivilege(adminNum);

		// 获取机房原信息 roomNewInfo.getId()
		TComputerRoom oldRoomInfo = this.getComputerRoomById(roomNewInfo.getId());

		this.comparisonActualAndTotalComputer(roomNewInfo.getActAvailableQuantity(),
				roomNewInfo.getTotalSets());

		// 如果提交的某个参数与原先在数据表中的某个列值一致,就不再构造那个属性了
		TComputerRoom roomData = this.getConstructDataForRevise(oldRoomInfo,
				roomNewInfo);

		roomData.setOperatedTime(new Date());
		roomData.setAdminNumOperated(adminNum);

		TComputerRoomExample example = new TComputerRoomExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(oldRoomInfo.getId());

		int effect = roomMapper.updateByExampleSelective(roomData, example);
		System.err
				.println(this.getClass() + "--reviseRoomInfoById--effect=" + effect);

		TComputerRoom latestRoom = this.getComputerRoomById(oldRoomInfo.getId());
		System.err.println(this.getClass() + "--reviseRoomInfoById--latestRoom="
				+ latestRoom.toString());

		return latestRoom;
	}

	@Override
	public TComputerRoom getComputerRoomById(Integer id) throws OperationException {
		System.err.println(this.getClass() + "--getComputerRoomById--id=" + id);

		TComputerRoomExample example = new TComputerRoomExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<TComputerRoom> list = roomMapper.selectByExample(example);

		if (list.isEmpty()) {
			String description = ExceptionsEnum.COMPUTER_ROOM_NOT_EXIST
					.getDescription();
			logger.info(description);
			System.err.println(this.getClass()
					+ "--getComputerRoomById--COMPUTER_ROOM_NOT_EXIST--description--"
					+ description);
			throw new OperationException(description);
		}

		TComputerRoom room = list.get(0);
		System.err.println(this.getClass() + "--getComputerRoomById--return__room="
				+ room.toString());

		return room;
	}

	@Override
	public void comparisonActualAndTotalComputer(Integer actualAvailable,
			Integer totalQuantity) throws OperationException {
		System.err.println(this.getClass()
				+ "__comparisonActualAndTotalComputer__actualAvailable="
				+ actualAvailable + ",totalQuantity=" + totalQuantity);

		if (actualAvailable > totalQuantity) {
			String description = ExceptionsEnum.ACTUAL_CANNOT_MORE_THAN_TOAL
					.getDescription();
			logger.info(description);
			System.err.println(this.getClass()
					+ "--comparisonActualAndTotalComputer--actual_cannot_more_than_toal--description--"
					+ description);
			throw new OperationException(description);
		}
	}

	@Override
	public TComputerRoom getConstructDataForRevise(TComputerRoom oldRoomInfo,
			TComputerRoom newRoomInfo) {
		System.err.println(
				this.getClass() + "--getConstructDataForRevise--oldRoomInfo="
						+ oldRoomInfo + "___newRoomInfo=" + newRoomInfo);

		TComputerRoom roomData = new TComputerRoom();

		/* 原有之表中 */
		Integer oldRoomInfoRoomNum = oldRoomInfo.getRoomNum();
		Integer oldRoomInfoActAvailableQuantity = oldRoomInfo
				.getActAvailableQuantity();
		String oldRoomInfoLocation = oldRoomInfo.getLocation();
		Integer oldRoomInfoTotalSets = oldRoomInfo.getTotalSets();
		Integer oldRoomInfoAvailableStatus = oldRoomInfo.getAvailableStatus();

		/* 提交之新参 */
		Integer newRoomInfoRoomNum = newRoomInfo.getRoomNum();
		Integer newRoomInfoActAvailableQuantity = newRoomInfo
				.getActAvailableQuantity();
		String newRoomInfoLocation = newRoomInfo.getLocation();
		Integer newRoomInfoTotalSets = newRoomInfo.getTotalSets();
		Integer newRoomInfoAvailableStatus = newRoomInfo.getAvailableStatus();

		/*
		 * 如果提交的某个参数与原先在数据表中的某个列值一致,就不再构造那个属性了
		 */
		if (newRoomInfoRoomNum != null) {

			if (oldRoomInfoRoomNum != newRoomInfoRoomNum) {
				System.err.println("有效----");

				roomData.setRoomNum(newRoomInfoRoomNum);
			}

		} else if (newRoomInfoActAvailableQuantity == null) {
			// TODO ???
			boolean x = (oldRoomInfoActAvailableQuantity == newRoomInfoActAvailableQuantity);
			System.err.println("放放放放x==" + x);

			if (oldRoomInfoActAvailableQuantity != newRoomInfoActAvailableQuantity) {

				System.err.println("雅雅雅雅放放放放x==" + x);

				roomData.setActAvailableQuantity(newRoomInfoActAvailableQuantity);
			}

		} else if (!"".equals(newRoomInfoLocation)) {

			if (!oldRoomInfoLocation.equals(newRoomInfoLocation)) {
				roomData.setLocation(newRoomInfoLocation);
			}

		} else if (newRoomInfoTotalSets != null) {

			if (oldRoomInfoTotalSets != newRoomInfoTotalSets) {
				roomData.setTotalSets(newRoomInfoTotalSets);
			}

		} else if (newRoomInfoAvailableStatus != null) {

			if (oldRoomInfoAvailableStatus != newRoomInfoAvailableStatus) {
				roomData.setAvailableStatus(newRoomInfoAvailableStatus);
			}

		}

		System.err.println(this.getClass() + "--getConstructDataForRevise--roomData="
				+ roomData);
		return roomData;
	}

	@Override
	public Pagination<List<TComputerRoom>> getRoomListByPagination(Long userNum,
			Integer pageOrder, Integer rows) throws OperationException {
		System.err.println(this.getClass() + "--getRoomListByPagination--userNum="
				+ userNum + "--pageOrder=" + pageOrder + "--rows=" + rows);

		ius.checkUserExist(userNum);

		// 房间的数量
		Integer idCounts = roomMapper.selectCountAllComputerRoomByIDs();

		TComputerRoomExample example = new TComputerRoomExample();
		example.setLimit(rows);
		example.setOffset(pageOrder * rows);

		// 分页数据
		List<TComputerRoom> pageList = roomMapper.selectByExample(example);
		if (pageList.isEmpty()) {
			for (TComputerRoom tComputerRoom : pageList) {
				System.err.println(
						this.getClass() + "--getRoomListByPagination.tComputerRoom="
								+ tComputerRoom.toString());
			}
		}

		Pagination<List<TComputerRoom>> pagination = paginationUtil
				.assemblyComputerRoom(pageList, idCounts, rows, pageOrder);

		return pagination;
	}

	@Override
	public TComputerRoom getComputerRoomById(Integer id, Long userNum)
			throws OperationException {
		System.err.println(this.getClass() + "__getComputerRoomById[Override]__id"
				+ id + "__userNum=" + userNum);

		ius.checkUserExist(userNum);

		TComputerRoom computerRoom = this.getComputerRoomById(id);

		return computerRoom;
	}

}
