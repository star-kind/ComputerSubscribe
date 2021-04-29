package test.computer.subscribe.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.service.IComputerRoomService;

public class ComputerRoomServiceTest {
	private ApplicationContext applicationContext;
	private IComputerRoomService crs;

	@Before
	public void initialize() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/SpringDAO.xml", "spring/SpringService.xml" });
	}

	@Test
	public void getRoomsListTest() {
		crs = (IComputerRoomService) applicationContext
				.getBean("computerRoomServiceImpl");

		try {
			crs.getRoomListByPagination(2465944154L, 0, 2);

		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getSingleRoomTest() {
		crs = (IComputerRoomService) applicationContext
				.getBean("computerRoomServiceImpl");

		try {
			crs.getComputerRoomById(1, 2465944154L);

		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void updateRoomTest() {
		crs = (IComputerRoomService) applicationContext
				.getBean("computerRoomServiceImpl");

		try {
			TComputerRoom room = new TComputerRoom();

			room.setId(1);
			room.setActAvailableQuantity(221);
			room.setTotalSets(320);
			room.setAvailableStatus(0);
			room.setRoomNum(333);
			room.setLocation("ER-Eua-162007-FEX");
			room.setAdminNumOperated(2465944154L);

			crs.reviseRoomInfoById(room);

		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void saveNewRoomTest() {
		crs = (IComputerRoomService) applicationContext
				.getBean("computerRoomServiceImpl");

		try {
			TComputerRoom room = new TComputerRoom();

			room.setAvailableStatus(0);
			room.setLocation("xxx-yyy-xxx-zzz");
			room.setRoomNum(13);
			room.setTotalSets(86);
			room.setAdminNumOperated(393606924700L);
			room.setActAvailableQuantity(77);

			crs.saveNewRoom(room);

		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
