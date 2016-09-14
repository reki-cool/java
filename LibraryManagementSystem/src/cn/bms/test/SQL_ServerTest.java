package cn.bms.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class SQL_ServerTest {

	@Test
	public Connection getConnection(){
		Connection conn = null;  //一定要在这里声明一个Connection对象，不然返回的时候就会没有Connection对象，在try里面声明的Connection对象只在try里面有效
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //将SQL Server驱动加载到类装载器中
			
			String url = "jdbc:sqlserver://localhost:1433;DataBaseName=studio"; //数据库所在端口和数据库的名字
			
			String username = "sa"; //连接数据库的用户名
			String password = "13322093708"; //连接数据库的密码
			
			conn = DriverManager.getConnection(url, username, password); //得到驱动器中的Connection对象	
			
			if(conn == null){
				System.out.println("连接失败");
			}
			else{
				System.out.println("连接成功");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

