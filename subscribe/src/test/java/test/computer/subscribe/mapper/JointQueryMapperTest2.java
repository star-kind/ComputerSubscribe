package test.computer.subscribe.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computer.subscribe.mapper.JointQueryMapper;
import com.computer.subscribe.pojo.TSubscribe;

/**
 * 
 * @author user
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/SpringDAO.xml")
public class JointQueryMapperTest2 {
	@Autowired
	private JointQueryMapper jqMapper;

	@Test
	public void retrieveSubscribesListTest() {
		String beginTime = "2021-02-02 00:00:00";
		String endTime = "2021-05-02 00:00:00";

		List<TSubscribe> list = jqMapper.retrieveSubscribesList(1, beginTime,
				endTime, 1, 10);
		for (TSubscribe tSubscribe : list) {
			System.err.println(tSubscribe);
		}
	}

}
