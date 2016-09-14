package cn.bms.test;

import java.util.Date;
import org.junit.Test;

import cn.bms.bean.User;
import cn.bms.exception.UserExistException;
import cn.bms.service.impl.BusinessServiceImpl;
import cn.bms.utils.WebUtils;

public class ServiceTest {

//	@Test
//	public void testRegister(){
//		User user = new User();
//		
//		user.setBirthday(new Date());
//		user.setEmail("bb@sina.com");
//		user.setId(WebUtils.generateID());
//		user.setNickname("李子");
//		user.setPassword("133");
//		user.setUsername("gggg");
//		
//		BusinessServiceImpl service = new BusinessServiceImpl();
//		try {
//			service.register(user);
//			System.out.println("注册成功！！");
//		} catch (UserExistException e) {
//			System.out.println("该用户已存在，请重新输入用户名！！");
//		}		
//	}

	@Test
	public void testLogin(){
		BusinessServiceImpl service = new BusinessServiceImpl();
		
		User user = service.login("gggg", "133");
		System.out.println(user);
	}
}

