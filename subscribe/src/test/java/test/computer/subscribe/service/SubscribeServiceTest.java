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
				new String[] { "spring/SpringDAO.xml", "spring/SpringService.xml" });
	}

	@Test
	public void getReviewerHandleSubscribeTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			subscribeService.getSubcribeByTeacherReview(3999706924700L, 0, 2);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getReviewerHandleByStatusTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			subscribeService.getSubcribeByTeacherReview(3999706924700L, 0, 2, 1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void studentCancelSubscribeTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {

			subscribeService.studentCancelSubscribeById(1889970l, 68l, 4);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getAllSubscribeListByAdminTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			int pageNum = 1;
			int row = 8;

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
			int row = 10;
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

			String timeApply = "2021-6-7";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateApply = format.parse(timeApply);

			sub.setApplicant(1889970l);
			sub.setApplyUseDate(dateApply);
			sub.setRoomNum(6);
			sub.setUseInterval(2);

			subscribeService.addNewScuSubscribe(sub);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void getWeekAllSubscribeListTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			int pageNum = 1;
			int row = 8;

			subscribeService.getAllSubscirbeOnWeek(pageNum, row, 13541944130L);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}