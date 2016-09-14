package cn.bms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQL_ServerUtils {

	/**
	 * 获取数据库连接
	 * @return Connection 对象
	 */
	public Connection getConnection(){
		Connection conn = null;  //一定要在这里声明一个Connection对象，不然返回的时候就会没有Connection对象，在try里面声明的Connection对象只在try里面有效
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //将SQL Server驱动加载到类装载器中
			
			String url = "jdbc:sqlserver://localhost:1433;DataBaseName=Library"; //数据库所在端口和数据库的名字
			
			String username = "sa"; //连接数据库的用户名
			String password = "123456"; //连接数据库的密码
			
			conn = DriverManager.getConnection(url, username, password); //得到驱动器中的Connection对象	
			
			if(conn == null){
				System.out.println("---------------数据库连接测试--------------");
				System.out.println("连接失败");
				System.out.println("---------------------------------------");
			}
			else{
				System.out.println("---------------数据库连接测试--------------");
				System.out.println("连接成功");
				System.out.println("---------------------------------------");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

