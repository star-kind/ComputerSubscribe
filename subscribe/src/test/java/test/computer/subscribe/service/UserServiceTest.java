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
			// 0--393606924700L,2465944154L
			// 1--3999706924700L,13541944130L
			// 2--54059944154L,214715547L,10548941L,19908114L
			TUser u = new TUser();
			u.setMailbox("666555444@hahaha.cn");
			u.setUserName("geedy.kano.xdxd");
			u.setPhone("19570351403");
			u.setRole(1);
			u.setId(6);
			userService.modifyUserInfoByAdminNum(u, 2465944154L);
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void retrieveMyselfTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			userService.getProfileByMySelf(11, 239915547L);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void modifyAdminMyselfTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			TUser u = new TUser();
			u.setMailbox("1517415415@qq.com.cn");
			u.setPhone("15243606940");
			u.setUserName("victories.failed");
			u.setId(18);

			userService.modifyInfoByAdminMySelf(u);
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void allUserListTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");
		userService.getAllUsers();
	}

	@Test
	public void modifyDataByNormalTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			TUser u = new TUser();
			u.setMailbox("16456680791@qq.com");
			u.setUserName("PaulKing");
			u.setPhone("16456680791");
			u.setId(1);

			userService.modifyInfoByNormalUser(u);

		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getUserListByLimitTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		userService.getUserListByLimits(1, 7);
	}

	@Test
	public void getMembersListByOrderTest() {
		userService = (IUserService) applicationContext.getBean("userServiceImpl");

		try {
			Pagination<List<TUser>> pagination = userService.getMembersListByOrder(3,
					3, 18);

			System.err.println(pagination.toString());
			for (TUser tUser : pagination.getData()) {
				System.err.println(tUser.toString());
			}
		} catch (OperationException e) {
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

		user.setMailbox("48018250@cat.com");
		user.setPhone("19354035182");
		user.setUserNum(135994030L);
		user.setPassword("12345");
		user.setRole(0);
		user.setUserName("luea.asen");

		try {
			Integer row = userService.regist(user);
			System.err.println("execute === " + row);
		} catch (OperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
