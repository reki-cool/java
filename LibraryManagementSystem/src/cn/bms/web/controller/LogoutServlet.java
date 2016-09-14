package cn.bms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//处理用户注销请求的Servlet
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session != null){
			//如果会话不为空，就说明有用户信息放在session里，所以要把它移除掉
			session.removeAttribute("user");
		}
		
		request.setAttribute("message", "注销成功！！将在3秒内自动跳转回起始页面，如需跳转请<a href='" + request.getContextPath() + "/admin.jsp'>点击此处</a> <meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/admin.jsp'>");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
