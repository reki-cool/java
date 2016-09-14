package cn.bms.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.bms.bean.Book;
import cn.bms.dao.BookDao;
import cn.bms.dao.impl.BookDaoImpl;

public class BookDaoTest {

//	@Test
//	//书籍添加测试
//	public void testAdd(){
////		private String bkNum; //条形码
////		private String bkName; //书名
////		private String auName; //作者
////		private String auInfo; //作者简介
////		private String bkPub; //出版社
////		private Date bkPubTime; //出版时间
////		private int bkPrice; //单价
////		private String bkInfo; //书籍简介
////		private String bkLang; //语种
////		private String bkTag; //标签
////		private String typeName; //类别
////		private int bkPop; //人气
////		private Date bkInTime; //入库时间
////		private int bkInv; //库存
//		
//		Book book = new Book();
//		
//		book.setBkNum("12345678");
//		book.setBkName("IOS编程");
//		book.setAuName("没有人");
//		book.setAuInfo("人在塔在");
//		book.setBkPub("清华大学出版社");
//		book.setBkPubTime(new Date());
//		book.setBkPrice(99);
//		book.setBkInfo("初学者必备");
//		book.setBkLang("中文");
//		book.setBkTag("编程");
//		book.setTypeName("编程");
//		book.setBkInTime(new Date());
//		book.setBkInv(10);
//		
//		BookDao dao = new BookDaoImpl();
//		dao.addBook(book);
//	}

	@Test
	public void textFind(){
		BookDao bkDao = new BookDaoImpl();
		
		List<Book> bookList = bkDao.findBook("IOS编程");
		
		System.out.println("成功查询了: " + bookList.size() + "条数据");
	}
}
