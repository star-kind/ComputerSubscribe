package test.computer.subscribe.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computer.subscribe.exception.OperationException;
import com.computer.subscribe.pojo.LoginData;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.response.Pagination;
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
	public void updateUserInfoListTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			// 0-393606924700L
			// 1-3999706924700L
			userService.modifyUserInfoByAdminNum("lion.pnay", "h8848@fox.com.cn",
					"17123241037", 105170048l, 393606924700L);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void allUserListTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		userService.getAllUsers();
	}

	@Test
	public void getUserListByLimitTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		userService.getUserListByLimits(3, 3);
	}

	@Test
	public void getMembersListByOrderTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			Pagination<List<TUser>> pagination = userService.getMembersListByOrder(9,
					2, 18);

			System.err.println(pagination.toString());
			for (TUser tUser : pagination.getData()) {
				System.err.println(tUser.toString());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void reviseKwdTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			Integer effect = userService.revisePassword("2220", "7710",
					393606134700L);
			System.err.println(effect);
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void loginingTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			LoginData user = userService.login(393606134700L, "2220", 1);
			System.err.println(user.toString());
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void registerTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		TUser user = new TUser();

		user.setMailbox("30117754@fox.com");
		user.setPhone("17090380937");
		user.setUserNum(890170700L);
		user.setPassword("222");
		user.setRole(2);
		user.setUserName("student.apcount");

		try {
			Integer row = userService.regist(user);
			System.out.println("execute===" + row);
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
