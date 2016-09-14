package cn.bms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bms.bean.Book;
import cn.bms.service.impl.BusinessServiceImpl;
import cn.bms.utils.UpdateBookUtils;
import cn.bms.web.formbean.BookForm;

public class UpdateBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //因为用来注册的jsp界面需要调用request域里的数据，而request域里的数据里有中文，所以要将request的编码设置为UTF-8不然request传过去的数据就是一堆乱码，jsp页面用的编码是UTF-8，会无法识别
		
		//对提交表单的字段进行合法性校验(把表单数据封装到 formbean里面去)
		BookForm form = UpdateBookUtils.requestToForm(request, BookForm.class);
		
		boolean b = form.validate(); //表单数据封装好以后就进行校验
		
		//2.如果校验失败，则跳回到表单页面，回显校验失败的信息
		if(!b){ //如果b == false ,就会执行这个方法
			//request.getSession().setAttribute("form", form);
			
//			request.setAttribute("form", form); //因为bean里的Map集合存有校验失败的错误信息，所以要把bean存到request域里，然后带到图书添加页面
			request.setAttribute("book", form);
			request.getRequestDispatcher("/WEB-INF/jsp/update_book.jsp").forward(request, response);
			return; //校验失败下面的代码就没有必要执行了，所以return
		}
		
		//拷贝formbean里面的数据到bookbean里面去
		Book book = new Book();
		UpdateBookUtils.copyBean(book, form);
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		try{
			service.updateBook(book);;
			
			//如果Service处理成功，就跳转到网站的全局消息显示页面，为用户显示更新成功的消息
			request.setAttribute("message", "更新成功!!!将在3秒以后自动跳转到总览图书列表<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/FindAllBooksAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}catch(Exception e){
			//如果Service处理不成功，并且不成功的原因是其他问题的话，就跳转到网站的全局消息显示页面
			e.printStackTrace();//因为其他问题而导致处理不成功产生的异常不能抛给用户，所以要把它抓起来，然后在后台显示记录，以便于将来程序员根据异常来维护和改进系统
			
			request.setAttribute("message", "服务器出现未知错误!!将在3秒以后自动返回图书信息更新界面<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/UpdateBookAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
	}

}
