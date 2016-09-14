package cn.bms.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import cn.bms.bean.Admin;
import cn.bms.dao.AdminDao;
import cn.bms.utils.SQL_ServerUtils;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin login(String admAcc, String admPswd) {
		Connection conn = new SQL_ServerUtils().getConnection();
		
		String sql = "select * from admin";
		
		try{
			Admin adm = new Admin();
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				if(!rs.getString("admAcc").equals(admAcc)){
					continue;
				}
				
				if(!rs.getString("admPswd").equals(admPswd)){
					continue;
				}
				
				adm.setAdmName(rs.getString("admName"));
				
				break;
			}
			
			if(adm.getAdmName() == null || adm.getAdmName().equals("")){
				return null;
			}
			
			rs.close(); 
			stmt.close();
			conn.close(); 
			
			return adm; 

		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
