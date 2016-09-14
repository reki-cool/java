package cn.bms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bms.bean.Admin;
import cn.bms.bean.User;
import cn.bms.service.impl.BusinessServiceImpl;

public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String admAcc = request.getParameter("admAcc").toString();
		String admPswd = request.getParameter("admPswd").toString();
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		Admin adm = service.adminLogin(admAcc, admPswd);
		
		if(adm != null){
			//如果adm不等于null，就说明登录成功，允许进入管理界面
			System.out.println(adm);
			
			//这里不用request域来储存用户信息是因为request域是针对当前的请求，如果当前请求没了，那用户信息也就跟着没了，用户在访问网站的过程中往往会生成多个请求，每一个请求都是一个新的request，而session域是一个会话，访问一个网站就只有一个会话，只要浏览器不关闭，这个会话就会一直存在
			request.getSession().setAttribute("admin", adm); //将返回的用户信息放到会话中
			request.getRequestDispatcher("/IndexAdminServlet").forward(request, response);;
			return;
		}
		
		//如果adm等于null，就说明发生了错误，将错误信息反馈给客户
		request.setAttribute("message", "用户名或密码错误!将在3秒后自动返回登录页面<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/admin.jsp'");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
