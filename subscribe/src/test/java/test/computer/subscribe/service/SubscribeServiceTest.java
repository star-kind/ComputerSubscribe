package test.computer.subscribe.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.service.ISubscribeService;

public class SubscribeServiceTest {
	private ApplicationContext applicationContext;
	private ISubscribeService subscribeService;

	@Before
	public void initialize() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/springDAO.xml", "spring/springService.xml" });
	}

	@Test
	public void getAllSubscribeListByAdminTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			int pageNum = 1;
			int row = 5;

			subscribeService.getApplicantSubscribesByAdmin(pageNum, row, 1889970l,
					393606924700L);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getThisWeekListByTeacherTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			int pageNum = 1;
			int row = 3;
			subscribeService.getThisWeekSubscribeListByTeacher(1889970l,
					3999706924700L, row, pageNum);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void selectThisWeekListTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			subscribeService.getSubscribeWeekListByStatus(3999706924700L, 0);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void updateStatusTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			subscribeService.handleSubscribeStatus(1, 3999706924700L, 44l);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void selectByPreviousWeekListTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			subscribeService.getPreviousWeekSubscribes(393606924700L);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void selectByWeekListTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			subscribeService.getSubscribeListByStatus(1, 37801354154l);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void addNewTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			TSubscribe sub = new TSubscribe();

			String timeApply = "2021-4-23";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateApply = format.parse(timeApply);

			sub.setApplicant(3999706924700L);
			sub.setApplyUseDate(dateApply);
			sub.setRoomNum(2);
			sub.setUseInterval(1);

			subscribeService.addNewScuSubscribe(sub);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}