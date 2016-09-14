package cn.bms.service.impl;

import java.util.List;

import cn.bms.bean.Admin;
import cn.bms.bean.Book;
import cn.bms.bean.User;
import cn.bms.dao.AdminDao;
import cn.bms.dao.BookDao;
import cn.bms.dao.UserDao;
import cn.bms.dao.impl.AdminDaoImpl;
import cn.bms.dao.impl.BookDaoImpl;
import cn.bms.dao.impl.UserDaoImplSQL;
import cn.bms.exception.UserExistException;
import cn.bms.utils.ServiceUtils;

//对web层提供所有的业务服务
public class BusinessServiceImpl {

	//连接Dao层
	private UserDao dao = new UserDaoImplSQL();
	private BookDao bkDao = new BookDaoImpl();
	private AdminDao admDao = new AdminDaoImpl();
	
	//对web层提供管理员登录服务
	public Admin adminLogin(String admAcc, String admPswd){
		return admDao.login(admAcc, admPswd);
	}
	
	//对web层提供注册服务
	public void register(User user) throws UserExistException{
//		//注册前应该先判断一下用户是否存在
//		if(dao.find(user.getRdCardId())){
//			throw new UserExistException(); //发现要注册的用户已存在，则给web层抛一个编译时异常，因为运行时异常可处理也可不处理，而这一个一定要处理，所以抛编译时异常，提醒web层处理，给用户一个友好的提示
//		}
//		else{
//			//用户传过来的密码不能直接保存，要经过md5加密才行，然后再把用户数据传给数据文件
//			user.setRdPassword(ServiceUtils.md5(user.getRdPassword()));
//	 		dao.add(user);
//		}
		
		dao.add(user);
	}
	
	//对web层提供读者登录服务
	public User login(String username, String password){
		
		password = ServiceUtils.md5(password);//因为之前存入数据文件的密码是由md5包装过的，所以查找的时候也要用md5包装的密码才行
		return dao.find(username, password);
	}
	
	//对web层提供查询所有读者的服务
	public List<User> findAllUsers(){
		return dao.findAllUsers();
	}
	
	//对web层提供查询读者的服务
	public List<User> findUser(String rdName){
		return dao.findUser(rdName);
	}
	
	//对web层提供更新读者的服务
	public void updateUser(User user){
		dao.updateUser(user);
	}
	
	//对web层提供删除读者服务
	public void deleteUser(String rdCardId){
		dao.deleteUser(rdCardId);
	}
	
	//对web层提供添加图书服务
	public void addBook(Book book){
		bkDao.addBook(book);
	}
	
	//对web层提供查找单一图书服务
	public List<Book> findBook(String bookname){
		return bkDao.findBook(bookname);
	}
	
	//对web层提供查找所有图书服务
	public List<Book> findAllBooks(){
		return bkDao.findAllBooks();
	}
	
	//对web层提供更新图书服务
	public void updateBook(Book book){
		bkDao.updateBook(book);
	}
	
	//对web层提供删除单本图书服务
	public void deleteBook(String bkName, String auName){
		bkDao.deleteBook(bkName, auName);
	}
	
	//对web层提供分页查询图书服务     by都颜汗
	public List<Book> findBooksByPage(int page) {
		return bkDao.findBooksByPage(page);
	}
		
	//对web层提供查询图书总本数服务    by都颜汗
	public int findCount() {
		return bkDao.findCount();
	}
}

