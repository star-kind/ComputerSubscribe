package test.computer.subscribe.mapper;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computer.subscribe.mapper.TSubscribeMapper;
import com.computer.subscribe.pojo.TSubscribe;

public class SubscribeMapperTest {
	private ApplicationContext applicationContext;
	private TSubscribeMapper subscribeMapper;

	@Before
	public void before() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/SpringDAO.xml");
	}

	@Test
	public void getCountByStatusAndRoomNumTest() {
		subscribeMapper = (TSubscribeMapper) applicationContext
				.getBean("TSubscribeMapper");

		Integer result = subscribeMapper.getCountIdByStatusAndRoom(0, 2);
		System.out.println("result==" + result);
	}

	@Test
	public void selectByTimeApplicantLimitTest() {
		subscribeMapper = (TSubscribeMapper) applicationContext
				.getBean("TSubscribeMapper");

		int pageNum = 1;
		int row = 3;

		String timeBegin = "2021-04-12 00:00:00";
		String timeEnd = "2021-04-18 00:00:00";

		List<TSubscribe> list = subscribeMapper.selectByTimeApplicantLimit(timeBegin,
				timeEnd, pageNum, row, 1889970l);
		for (TSubscribe tSubscribe : list) {
			System.err.println(tSubscribe.toString());
		}
	}

	@Test
	public void selectByApplicantLimitTest() {
		subscribeMapper = (TSubscribeMapper) applicationContext
				.getBean("TSubscribeMapper");

		List<TSubscribe> list = subscribeMapper.selectByApplicantAndLimit(0, 4,
				301354154l);

		for (TSubscribe tSubscribe : list) {
			System.err.println(tSubscribe.toString());
		}
	}

	@Test
	public void selectCountsTest() {
		subscribeMapper = (TSubscribeMapper) applicationContext
				.getBean("TSubscribeMapper");
		String timeBegin = "2021-04-12 00:00:01";
		String timeEnd = "2021-04-19 00:00:01";
		long applicant = 1889970l;
		Integer lines = subscribeMapper.selectCountsByApplicantAndTime(timeBegin,
				timeEnd, applicant);
		System.err.println("lines:" + lines);
	}

}
