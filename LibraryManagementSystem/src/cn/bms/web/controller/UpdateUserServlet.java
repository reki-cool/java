package cn.bms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bms.bean.Book;
import cn.bms.bean.User;
import cn.bms.service.impl.BusinessServiceImpl;
import cn.bms.utils.UpdateBookUtils;
import cn.bms.web.formbean.BookForm;
import cn.bms.web.formbean.RegisterForm;

public class UpdateUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		//对提交表单的字段进行合法性校验(把表单数据封装到 formbean里面去)
		RegisterForm form = UpdateBookUtils.requestToForm(request, RegisterForm.class);
		
		boolean b = form.validate(); //表单数据封装好以后就进行校验
		
		//2.如果校验失败，则跳回到表单页面，回显校验失败的信息
		if(!b){ //如果b == false ,就会执行这个方法
			//request.getSession().setAttribute("form", form);
			
//			request.setAttribute("form", form); //因为bean里的Map集合存有校验失败的错误信息，所以要把bean存到request域里，然后带到图书添加页面
			request.setAttribute("user", form);
			request.getRequestDispatcher("/WEB-INF/jsp/update_user.jsp").forward(request, response);
			return; //校验失败下面的代码就没有必要执行了，所以return
		}
		
		//拷贝formbean里面的数据到bookbean里面去
		User user = new User();
		UpdateBookUtils.copyBean(user, form);
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		try{
			service.updateUser(user);
			
			//如果Service处理成功，就跳转到网站的全局消息显示页面，为用户显示更新成功的消息
			request.setAttribute("message", "更新成功!!!将在3秒以后自动跳转到读者总览列表<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/FindAllUsersAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}catch(Exception e){
			//如果Service处理不成功，并且不成功的原因是其他问题的话，就跳转到网站的全局消息显示页面
			e.printStackTrace();//因为其他问题而导致处理不成功产生的异常不能抛给用户，所以要把它抓起来，然后在后台显示记录，以便于将来程序员根据异常来维护和改进系统
			
			request.setAttribute("message", "服务器出现未知错误!!将在3秒以后自动返回读者信息更新界面<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/UpdateUserAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
	}
}
