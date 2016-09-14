package cn.bms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bms.service.impl.BusinessServiceImpl;

public class DeleteUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		
		try{
			service.deleteUser(request.getParameter("rdCardId"));
			
			//如果Service处理成功，就跳转到网站的全局消息显示页面，为用户显示删除成功的消息
			request.setAttribute("message", "删除成功!!!将在3秒以后自动跳转到读者总览列表<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/FindAllUsersAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}catch(Exception e){
			//如果Service处理不成功，并且不成功的原因是其他问题的话，就跳转到网站的全局消息显示页面
			e.printStackTrace();//因为其他问题而导致处理不成功产生的异常不能抛给用户，所以要把它抓起来，然后在后台显示记录，以便于将来程序员根据异常来维护和改进系统
			
			request.setAttribute("message", "服务器出现未知错误!!将在3秒以后自动返回读者总览列表界面<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/FindAllUsersAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
	}

}
