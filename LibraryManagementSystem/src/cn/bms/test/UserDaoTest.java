package cn.bms.test;

import java.util.Date;

import org.junit.Test;

import cn.bms.bean.User;
import cn.bms.dao.UserDao;
import cn.bms.dao.impl.UserDaoImplSQL;

public class UserDaoTest {
//	@Test
//	public void testAdd(){
//		User user = new User();
//		
//		user.setBirthday(new Date());
//		user.setEmail("bb@sina.com");
//		user.setId("123445555");
//		user.setNickname("李子");
//		user.setPassword("133");
//		user.setUsername("bbbb");
//		
//		UserDao dao = new UserDaoImplSQL();
//		dao.add(user);
//	}
	
	@Test
	public void testFind(){
		UserDao dao = new UserDaoImplSQL();
		dao.find("aaa", "123");
	}
	
//	@Test
//	public void testByFind(){
//		UserDao dao = new UserDaoImplSQL();
//		System.out.println(dao.find("sada"));
//	}
}

