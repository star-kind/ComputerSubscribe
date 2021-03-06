package test.computer.subscribe.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computer.subscribe.mapper.TUserMapper;
import com.computer.subscribe.pojo.TUser;

public class UserMapperTest {
	private ApplicationContext applicationContext;
	private TUserMapper userMapper;

	@Before
	public void before() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/SpringDAO.xml");
	}

	@Test
	public void countsIdColumnValueTest() {
		userMapper = (TUserMapper) applicationContext.getBean("TUserMapper");

		Integer res = userMapper.selectCountIdByUserNumValue(19908114L);

		Integer res1 = userMapper.selectCountIdByPhoneValue("18450243690");

		Integer res2 = userMapper.selectCountIdByMailBoxValue("15651606848@qq.cn");

		System.out.println(this.getClass() + "\n__res=" + res + ",res1=" + res1
				+ ",res2=" + res2);
	}

	@Test
	public void countsIdTest() {
		userMapper = (TUserMapper) applicationContext.getBean("TUserMapper");

		int counts = userMapper.selectCountId();
		System.out.println("counts==" + counts);
	}

	@Test
	public void saveUserTest() {
		userMapper = (TUserMapper) applicationContext.getBean("TUserMapper");

		TUser user = new TUser();
		user.setMailbox("100468348461@qq.cn");
		user.setPassword("10000");
		user.setPhone("0778-34617014U");
		user.setRole(2);
		user.setSalt("yakamaximaxka115318118513");
		user.setUserName("LeAnder");
		user.setUserNum(188997000L);

		int insert = userMapper.insert(user);
		System.out.println("insert==" + insert);
	}
}
