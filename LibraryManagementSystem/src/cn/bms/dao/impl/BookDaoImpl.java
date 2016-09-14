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

import cn.bms.bean.Book;
import cn.bms.dao.BookDao;
import cn.bms.utils.SQL_ServerUtils;

public class BookDaoImpl implements BookDao {

	//添加图书
	@Override
	public void addBook(Book book) {
		Connection conn = new SQL_ServerUtils().getConnection();
		String sql;
		PreparedStatement ps;
		int row;
		try{
			//添加作者信息
			sql = "insert into auther(auId, auName, auInfo) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, book.getAuId());
			ps.setString(2, book.getAuName());
			ps.setString(3, book.getAuInfo());
			
			row = ps.executeUpdate();
			
			if(row != 0){
				System.out.println("作者插入成功, row = " + row);
			}
			else{
				System.out.println("作者插入失败, row = " + row);
			}
			
			//添加类别信息
			sql = "insert into bookType(typeId, typeName) values(?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, book.getTypeId());
			ps.setString(2, book.getTypeName());
			
			row = ps.executeUpdate();
			
			if(row != 0){
				System.out.println("类别插入成功, row = " + row);
			}
			else{
				System.out.println("类别插入失败, row = " + row);
			}
			
			//添加图书信息
			sql = "insert into book(bkId, bkNum, bkName, bkPub, bkPubTime, bkPrice, bkInv, bkInTime, bkLang, bkTag, bkPop, bkInfo, auId, typeId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, book.getBkId());
			ps.setString(2, book.getBkNum());
			ps.setString(3, book.getBkName());
			ps.setString(4, book.getBkPub());
//			ps.setString(5, book.getBkPubTime().toLocaleString()); //出版时间
			ps.setString(5, book.getBkPubTime());  //String版的出版时间
			ps.setInt(6, book.getBkPrice());
			ps.setInt(7, book.getBkInv());
//			ps.setString(8, book.getBkPubTime().toLocaleString());  //入库时间
			ps.setString(8, new Date().toLocaleString());  //入库时间
			ps.setString(9, book.getBkLang());
			ps.setString(10, book.getBkTag());
			ps.setInt(11, book.getBkPop());
			ps.setString(12, book.getBkInfo());
			ps.setString(13, book.getAuId());
			ps.setString(14, book.getTypeId());
			
			row = ps.executeUpdate();
			
			if(row != 0){
				System.out.println("图书插入成功, row = " + row);
			}
			else{
				System.out.println("图书插入失败, row = " + row);
			}
			
			ps.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//以书名查找图书
	@Override
	public List<Book> findBook(String bookname) {
		Connection conn = new SQL_ServerUtils().getConnection();
		
		String sql = "select * from book,auther,bookType where book.auId=auther.auId and book.typeId=bookType.typeId";
		
		List<Book> bookList = new ArrayList<Book>();
		
		try{
			
			Statement stmt = conn.createStatement();
			
			//查找图书
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Book book = new Book();
				
				if(!rs.getString("bkName").equals(bookname)){
					continue;
				}
				
				//将数据库中的bkPubTime 转换为Date类型,并封装到bookbean里去
				String bkPubTime = rs.getString("bkPubTime").toString();
				
				if(bkPubTime == null || bkPubTime.equals("")){
					book.setBkPubTime(null);
				}
				else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					book.setBkPubTime(sdf.format(sdf.parse(bkPubTime)));
				}

				book.setBkInTime(rs.getString("bkInTime"));
				book.setBkNum(rs.getString("bkNum").toString());
				book.setBkName(rs.getString("bkName").toString());
				book.setAuName(rs.getString("auName").toString());
				book.setAuInfo(rs.getString("auInfo").toString());
				book.setBkPub(rs.getString("bkPub").toString());
				book.setBkPrice(rs.getInt("bkPrice"));
				book.setBkInfo(rs.getString("bkInfo").toString());
				book.setBkLang(rs.getString("bkLang").toString());
				book.setBkTag(rs.getString("bkTag").toString());
				book.setTypeName(rs.getString("typeName").toString());
				book.setBkPop(rs.getInt("bkPop"));
				book.setBkInv(rs.getInt("bkInv"));
				
				bookList.add(book);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			return bookList;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//查找所有图书
	@Override
	public List<Book> findAllBooks() {

		Connection conn = new SQL_ServerUtils().getConnection();
		
		String sql = "select * from book,auther,bookType where book.auId=auther.auId and book.typeId=bookType.typeId";
		
		List<Book> bookList = new ArrayList<Book>();
		
		try{
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				//book对象的声明一定要放到循环里面，每循环一次就分配一块内存给book对象，若是放在外面，就只分配了一块内存，也就是说，循环里面的修改都是在同一块内存里执行的，内存里的属性值都是以最后一次更新为准，而list数组的元素都是存放元素的地址的，也就是数组指针
				Book book = new Book();
				
				//将数据库中的bkPubTime 转换为Date类型,并封装到bookbean里去
				String bkPubTime = rs.getString("bkPubTime").toString();
				
				if(bkPubTime == null || bkPubTime.equals("")){
					book.setBkPubTime(null);
				}
				else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					book.setBkPubTime(sdf.format(sdf.parse(bkPubTime)));
				}

				book.setBkInTime(rs.getString("bkInTime"));	
				book.setBkNum(rs.getString("bkNum").toString());
				book.setBkName(rs.getString("bkName").toString());
				book.setAuName(rs.getString("auName").toString());
				book.setAuInfo(rs.getString("auInfo").toString());
				book.setBkPub(rs.getString("bkPub").toString());
				book.setBkPrice(rs.getInt("bkPrice"));
				book.setBkInfo(rs.getString("bkInfo").toString());
				book.setBkLang(rs.getString("bkLang").toString());
				book.setBkTag(rs.getString("bkTag").toString());
				book.setTypeName(rs.getString("typeName").toString());
				book.setBkPop(rs.getInt("bkPop"));
				book.setBkInv(rs.getInt("bkInv"));
				
				bookList.add(book);
			}
			
			System.out.println("测试bookList的大小为: " + bookList.size());
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return bookList;
	}
	
	//更新图书
	@Override
	public void updateBook(Book book) {
		Connection conn = new SQL_ServerUtils().getConnection();
		String sql;
		PreparedStatement ps;
		int row;
		
		try{
			//将要修改的书在数据库中完整的数据查询出来，将auId 和 typeId 封装到book里面去，以便接下来的判断
			sql = "select * from book, auther where book.auId=auther.auId";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				if(book.getBkName().equals(rs.getString("bkName")) || book.getAuName().equals(rs.getString("auName"))){
					book.setBkId(rs.getString("bkId"));
					book.setAuId(rs.getString("auId"));
					book.setTypeId(rs.getString("typeId"));
				}
			}
			
			//更新作者信息
			sql = "update auther set auName=?, auInfo=? where auId=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, book.getAuName());
			ps.setString(2, book.getAuInfo());
			ps.setString(3, book.getAuId());
			
			row = ps.executeUpdate();
			
			if(row != 0){
				System.out.println("作者更新成功, row = " + row);
			}
			else{
				System.out.println("作者更新失败, row = " + row);
			}
			
			//更新类别信息
			sql = "update bookType set typeName=? where typeId=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, book.getTypeName());
			ps.setString(2, book.getTypeId());
				
			row = ps.executeUpdate();
			
			if(row != 0){
				System.out.println("类别更新成功, row = " + row);
			}
			else{
				System.out.println("类别更新失败, row = " + row);
			}
			
			//更新图书信息
			sql = "update book set bkNum=?, bkName=?, bkPub=?, bkPubTime=?, bkPrice=?, bkInv=?, bkLang=?, bkTag=?, bkPop=?, bkInfo=? where bkId=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, book.getBkNum());
			ps.setString(2, book.getBkName());
			ps.setString(3, book.getBkPub());
			ps.setString(4, book.getBkPubTime());  //String版的出版时间
			ps.setInt(5, book.getBkPrice());
			ps.setInt(6, book.getBkInv());
			ps.setString(7, book.getBkLang());
			ps.setString(8, book.getBkTag());
			ps.setInt(9, book.getBkPop());
			ps.setString(10, book.getBkInfo());
			ps.setString(11, book.getBkId());

			
			row = ps.executeUpdate();
			
			if(row != 0){
				System.out.println("图书更新成功, row = " + row);
			}
			else{
				System.out.println("图书更新失败, row = " + row);
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

	//删除图书
	@Override
	public void deleteBook(String bkName, String auName) {
		Connection conn = new SQL_ServerUtils().getConnection();
		String sql;
		PreparedStatement ps;
		int row;
		
		try{
			//将要修改的书在数据库中完整的数据查询出来，将auId 和 typeId 封装到book里面去，以便接下来的判断
			sql = "select * from book, auther where book.auId=auther.auId";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				if(bkName.equals(rs.getString("bkName")) || auName.equals(rs.getString("auName"))){
					
					int bkInv = rs.getInt("bkInv");  //获得数据库中的没删除图书之前的库存
					
					System.out.println("---------测试bkInv--------");
					System.out.println(bkInv);
					System.out.println("-------------------------");
					
					//当图书的库存为1的时候将此图书从数据库中移除
					if(bkInv == 1){
						
						//删除作者
						sql = "delete from auther where auId=?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, rs.getString("auId"));
						row = ps.executeUpdate();
						
						if(row != 0){
							System.out.println("作者删除成功");
						}
						else{
							System.out.println("作者删除失败");
						}
						
						//删除类别
						sql = "delete from bookType where typeId=?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, rs.getString("typeId"));
						row = ps.executeUpdate();
						
						if(row != 0){
							System.out.println("类别删除成功");
						}
						else{
							System.out.println("类别删除失败");
						}
						
						//删除图书
						sql = "delete from book where bkId=?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, rs.getString("bkId"));
						row = ps.executeUpdate();
						
						if(row != 0){
							System.out.println("图书删除成功");
						}
						else{
							System.out.println("图书删除失败");
						}
						
						ps.close();
					}
					else{
						//删除单本图书的时候库存 -1
						sql = "update book set bkInv=? where bkId=?";
						
						ps = conn.prepareStatement(sql);
						
						ps.setInt(1, --bkInv);
						ps.setString(2, rs.getString("bkId"));
						
						row = ps.executeUpdate();
						
						if(row != 0){
							System.out.println("成功删除：" + row + "本书");
						}
						else{
							System.out.println("删除失败");
						}
						
						ps.close();
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

	//图书的分页处理
	@Override
	public List<Book> findBooksByPage(int page) {
		Connection conn = new SQL_ServerUtils().getConnection();
		
		String sql = "select top 5 * from book,auther,bookType where book.auId=auther.auId and book.typeId=bookType.typeId and bkNum not in (select top " + (page-1)*5 + " bkNum from book,auther,bookType where book.auId=auther.auId and book.typeId=bookType.typeId  order by bkNum asc) order by bkNum asc";
		
		List<Book> list = new ArrayList<Book>();
		
		try{
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Book book = new Book();
				
				//将数据库中的bkPubTime 转换为Date类型,并封装到bookbean里去
				String bkPubTime = rs.getString("bkPubTime").toString();
				
				if(bkPubTime == null || bkPubTime.equals("")){
					book.setBkPubTime(null);
				}
				else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					book.setBkPubTime(sdf.format(sdf.parse(bkPubTime)));
				}

				book.setBkInTime(rs.getString("bkInTime"));
				
				book.setBkNum(rs.getString("bkNum").toString());
				book.setBkName(rs.getString("bkName").toString());
				book.setAuName(rs.getString("auName").toString());
				book.setAuInfo(rs.getString("auInfo").toString());
				book.setBkPub(rs.getString("bkPub").toString());
				book.setBkPrice(rs.getInt("bkPrice"));
				book.setBkInfo(rs.getString("bkInfo").toString());
				book.setBkLang(rs.getString("bkLang").toString());
				book.setBkTag(rs.getString("bkTag").toString());
				book.setTypeName(rs.getString("typeName").toString());
				book.setBkPop(rs.getInt("bkPop"));
				book.setBkInv(rs.getInt("bkInv"));

				list.add(book);
			}
			
			System.out.println("测试bookList的大小为: " + list.size());
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	//图书的分页页数统计
	@Override
	public int findCount() {
		int count = 0;
		Connection conn = new SQL_ServerUtils().getConnection();
		String sql = "select count(*) from book,auther,bookType where book.auId=auther.auId and book.typeId=bookType.typeId";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
