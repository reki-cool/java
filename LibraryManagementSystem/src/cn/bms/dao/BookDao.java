package cn.bms.dao;

import java.util.List;

import cn.bms.bean.Book;

public interface BookDao {

	//添加图书
	void addBook(Book book);
	
	//查找单个图书信息
	List<Book> findBook(String bookname);
	
	//查找所有图书信息
	List<Book> findAllBooks();
	
	//更新图书信息
	void updateBook(Book book);
	
	//删除单本图书信息
	void deleteBook(String bkName,String auName);
	
	//分页查询图书
	/**
	 * 用来查询分页后某一页的图书列表     By  都颜汗
	 * @param page表示页码
	 * @return  List<Book> 图书列表集合
	 */
	List<Book> findBooksByPage(int page);
		
		
		
	//分页查询时获取数据库中书的总本数
	/**
	 * 获取书的总本数     By  都颜汗
	 * @return
	 */
	public int findCount();
}
