package cn.bms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bms.utils.SQL_ServerUtils;
import cn.bms.bean.Book;
import cn.bms.bean.User;
import cn.bms.dao.UserDao;

public class UserDaoImplSQL implements UserDao{
	
	//添加调用的方法
	public void add(User user){
		
		Connection conn = new SQL_ServerUtils().getConnection(); //获取数据库的连接
		
		try {
			
			String sql = "insert into reader(rdId, rdCardId, rdPassword, rdName, rdSex, rdPhone, rdCardNo, rdEmail, rdAddr, rdCCdTime) values(?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql); //获取PreparedStatement对象，修改信息的时候用这个
			
			ps.setString(1, user.getRdId());
			ps.setString(2, user.getRdCardId());
			ps.setString(3, user.getRdPassword());
			ps.setString(4, user.getRdName());
			ps.setString(5, user.getRdSex());
			ps.setString(6, user.getRdPhone());
			ps.setString(7, user.getRdCardNo());
			ps.setString(8, user.getRdEmail());
			ps.setString(9, user.getRdAddr());
			ps.setString(10, new Date().toLocaleString());
			
			int row = ps.executeUpdate(); //将数据更新到数据库中
			if(row > 0){
				System.out.println("成功添加了" + row + "条数据!");
			}
			
			ps.close();  //关闭PreparedStatement对象，释放资源
			conn.close();  //关闭Connection对象，释放资源
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//读者登录调用的方法
	public User find(String rdCardId, String rdPassword){
		Connection conn = new SQL_ServerUtils().getConnection();  //获取数据库的连接
		
		String sql = "select * from reader";
		
		try {
			
			User user = new User();
			
			return user; //return user 不能并不能放到if或者while循环中
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	//判断读者是否存在的方法
	public boolean find(String rdCardId){
		Connection conn = new SQL_ServerUtils().getConnection(); //获取数据库的连接
		
		boolean b = true;
		
		String sql = "select * from reader";
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("rdCardId").equals(rdCardId)){
					b = true;
				}
				else{
					b = false;
				}
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return b;
	}

	//查找所有读者的方法
	@Override
	public List<User> findAllUsers() {
		Connection conn = new SQL_ServerUtils().getConnection();
		
		String sql = "select * from reader";
		
		List<User> userList = new ArrayList<User>();
		
		try{
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				//user对象的声明一定要放到循环里面，每循环一次就分配一块内存给user对象，若是放在外面，就只分配了一块内存，也就是说，循环里面的修改都是在同一块内存里执行的，内存里的属性值都是以最后一次更新为准，而list数组的元素都是存放元素的地址的，也就是数组指针
				User user = new User();
				
				user.setRdCardId(rs.getString("rdCardId"));
				user.setRdPassword(rs.getString("rdPassword"));
				user.setRdName(rs.getString("rdName"));
				user.setRdSex(rs.getString("rdSex"));
				user.setRdPhone(rs.getString("rdPhone"));
				user.setRdCardNo(rs.getString("rdCardNo"));
				user.setRdEmail(rs.getString("rdEmail"));
				user.setRdAddr(rs.getString("rdAddr"));
				user.setRdCCdTime(rs.getString("rdCCdTime"));
				
				userList.add(user);
			}
			
			System.out.println("测试userList的大小为: " + userList.size());
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return userList;
	}

	//以读者名查找读者
	@Override
	public List<User> findUser(String rdName) {
		
		Connection conn = new SQL_ServerUtils().getConnection();
		
		String sql = "select * from reader";
		
		List<User> userList = new ArrayList<User>();
		
		try{
			
			Statement stmt = conn.createStatement();
			
			//查找图书
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				User user = new User();
				
				if(!rs.getString("rdName").equals(rdName)){
					continue;
				}
				
				user.setRdCardId(rs.getString("rdCardId"));
				user.setRdPassword(rs.getString("rdPassword"));
				user.setRdName(rs.getString("rdName"));
				user.setRdSex(rs.getString("rdSex"));
				user.setRdPhone(rs.getString("rdPhone"));
				user.setRdCardNo(rs.getString("rdCardNo"));
				user.setRdEmail(rs.getString("rdEmail"));
				user.setRdAddr(rs.getString("rdAddr"));
				user.setRdCCdTime(rs.getString("rdCCdTime"));
				
				userList.add(user);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			return userList;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//更新读者信息
	@Override
	public void updateUser(User user) {
		Connection conn = new SQL_ServerUtils().getConnection();
		String sql;
		PreparedStatement ps;
		int row;
		
		try{
			//将要修改的读者在数据库中完整的数据查询出来，将rdId封装到User里面去，以便接下来的判断
			sql = "select * from reader";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				if(user.getRdName().equals(rs.getString("rdName")) || user.getRdCardId().equals(rs.getString("rdCardId"))){
					user.setRdId(rs.getString("rdId"));
				}
			}
			
			//更新读者信息
			sql = "update reader set rdCardId=?, rdPassword=?, rdName=?, rdSex=?, rdPhone=?, rdCardNo=?, rdEmail=?, rdAddr=? where rdId=?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getRdCardId());
			ps.setString(2, user.getRdPassword());
			ps.setString(3, user.getRdName());
			ps.setString(4, user.getRdSex());
			ps.setString(5, user.getRdPhone());
			ps.setString(6, user.getRdCardNo());
			ps.setString(7, user.getRdEmail());
			ps.setString(8, user.getRdAddr());
			ps.setString(9, user.getRdId());

					
			row = ps.executeUpdate();
			
			if(row != 0){
				System.out.println("读者更新成功, row = " + row);
			}
			else{
				System.out.println("读者更新失败, row = " + row);
			}
			rs.close();
			ps.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//删除读者
	@Override
	public void deleteUser(String rdCardId) {
		Connection conn = new SQL_ServerUtils().getConnection();
		String sql;
		PreparedStatement ps;
		int row;
		
		try{
			//将要修改的书在数据库中完整的数据查询出来，将auId 和 typeId 封装到book里面去，以便接下来的判断
			sql = "select * from reader";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				if(rdCardId.equals(rs.getString("rdCardId"))){
					sql = "delete from reader where rdId=?";
					
					ps = conn.prepareStatement(sql);
					
					ps.setString(1, rs.getString("rdId"));
					
					row = ps.executeUpdate();
					
					if(row != 0){
						System.out.println("读者删除成功");
					}
					else{
						System.out.println("读者删除失败");
					}
				}				
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}