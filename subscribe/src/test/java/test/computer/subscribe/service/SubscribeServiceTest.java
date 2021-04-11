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
	public void addNewTest() {
		subscribeService = (ISubscribeService) applicationContext
				.getBean("subscribeServiceImpl");

		try {
			TSubscribe sub = new TSubscribe();

			String timeApply = "2029-1-14";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateApply = format.parse(timeApply);

			sub.setApplicant(890170700L);
			sub.setApplyUseDate(dateApply);
			sub.setRoomNum(0);
			sub.setUseInterval(0);

			subscribeService.addNewScuSubscribe(sub);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}