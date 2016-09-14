package cn.bms.dao;

import java.util.List;

import cn.bms.bean.User;

public interface UserDao {

	//添加用户信息
	void add(User user);

	//查找所有读者的信息
	List<User> findAllUsers();
	
	//读者登录
	User find(String rdCardId, String rdPassword);
	
	//查找读者信息
	List<User> findUser(String rdName);
	
	//更新读者信息
	void updateUser(User user);
	
	//删除读者信息
	void deleteUser(String rdCardId);

	//查找注册的读者是否在数据库中存在
	boolean find(String rdCardId);

}

