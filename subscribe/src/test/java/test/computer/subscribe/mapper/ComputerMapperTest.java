package test.computer.subscribe.mapper;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computer.subscribe.mapper.TComputerRoomMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/SpringDAO.xml")
public class ComputerMapperTest {
	@Autowired
	private TComputerRoomMapper computerRoomMapper;

	@Test
	public void selectSubscribesSizeTest() {
		Integer res[] = computerRoomMapper.getRoomNumCollection();
		for (int i = 0; i < res.length; i++) {
			System.err.println("r=" + res[i]);
		}
	}

	@Test
	public void selectMapTest() {
		List<Map<Integer, Integer>> mapList = computerRoomMapper
				.selectIdAndRoomNumMap();

		for (Map<Integer, Integer> map : mapList) {
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				System.err.println(
						"Key=" + entry.getKey() + ",value=" + entry.getValue());
			}
		}
	}

}
