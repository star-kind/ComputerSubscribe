package test.computer.subscribe.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.service.IUserService;

public class UserServiceTest {
	private ApplicationContext applicationContext;
	private IUserService userService;

	@Before
	public void initialize() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/springDAO.xml", "spring/springService.xml" });
	}

	@Test
	public void registerTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		TUser user = new TUser();

		user.setMailbox("342701684800@qq.cn");
		user.setPhone("17708030697");
		user.setUserNum(19908114);

		user.setRole(0);
		user.setUserName("keqingqq");

		try {
			Integer row = userService.regist(user);
			System.out.println("execute===" + row);
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
