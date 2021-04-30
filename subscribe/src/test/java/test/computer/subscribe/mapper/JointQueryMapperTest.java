package test.computer.subscribe.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computer.subscribe.mapper.JointQueryMapper;
import com.computer.subscribe.pojo.vo.SubscribeUserVO;

/**
 * 
 * @author user
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/SpringDAO.xml")
public class JointQueryMapperTest {
	@Autowired
	private JointQueryMapper jqMapper;

	@Test
	public void selectSubscribesSizeTest() {
		String beginTime = "2021-02-02 00:00:00";
		String endTime = "2021-05-02 00:00:00";

		Integer rows = jqMapper.selectCountOfSubscribes(1, beginTime, endTime);
		System.err.println("rows=" + rows);
	}

	@Test
	public void retrieveSubscribesListTest() {
		String beginTime = "2021-02-02 00:00:00";
		String endTime = "2021-05-02 00:00:00";

		List<SubscribeUserVO> list = jqMapper.selectSubscribesList(1, beginTime,
				endTime, 1, 10);
		for (SubscribeUserVO vo : list) {
			System.err.println(vo);
		}
	}

}
