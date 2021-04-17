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
				new String[] { "spring/springDAO.xml", "spring/springService.xml" });
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
			room.setActAvailableQuantity(102);
			room.setTotalSets(150);
			room.setAvailableStatus(1);
			room.setRoomNum(31);
			room.setLocation("EMR-Eula-155200647-FE");

			crs.reviseRoomInfoById(room, 2465944154L);

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
