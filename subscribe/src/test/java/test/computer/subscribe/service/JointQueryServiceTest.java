package test.computer.subscribe.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computer.subscribe.service.IJointQueryService;

/**
 * 
 * @author user
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/SpringDAO.xml",
		"classpath:spring/SpringService.xml" })
public class JointQueryServiceTest {
	@Autowired
	private IJointQueryService ijqs;

	@Test
	public void retrieveSubscribesListTest() {
		try {
			ijqs.getSubscribesOnlyTeacher(13541944130L, 1, 1, 6);
		} catch (RuntimeException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

}
