package cn.bms.dao;

import cn.bms.bean.Admin;

public interface AdminDao {
	//管理员登录
	Admin login(String admAcc, String admPswd);
}
