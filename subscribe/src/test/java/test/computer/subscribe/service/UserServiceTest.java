package test.computer.subscribe.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.service.IUserService;

/**
 * 用户业务测试模块
 * 
 * @author user
 *
 */
public class UserServiceTest {
	private ApplicationContext applicationContext;
	private IUserService userService;

	@Before
	public void initialize() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/springDAO.xml", "spring/springService.xml" });
	}

	@Test
	public void loginingTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			LoginData user = userService.login(393690147, "314", 1);
			System.err.println(user.toString());
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void registerTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		TUser user = new TUser();

		user.setMailbox("3134822135401@ffox.cn");
		user.setPhone("17088340694");
		user.setUserNum(393690147000L);

		user.setRole(1);
		user.setUserName("teacher.dinolain");

		try {
			Integer row = userService.regist(user);
			System.out.println("execute===" + row);
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
