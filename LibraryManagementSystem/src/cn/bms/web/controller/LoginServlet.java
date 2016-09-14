package cn.bms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bms.bean.User;
import cn.bms.service.impl.BusinessServiceImpl;

//处理登陆界面的请求
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		User user = service.login(username, password);
		
		if(user != null){
			//如果user不等于null，就说明登录成功，然后跳转到首页上，为了让用户知道已经跳转到首页，所以地址栏要改变，也就是要用重定向的方法
			
			//这里不用request域来储存用户信息是因为request域是针对当前的请求，如果当前请求没了，那用户信息也就跟着没了，用户在访问网站的过程中往往会生成多个请求，每一个请求都是一个新的request，而session域是一个会话，访问一个网站就只有一个会话，只要浏览器不关闭，这个会话就会一直存在
			request.getSession().setAttribute("user", user); //将返回的用户信息放到会话中
			response.sendRedirect(request.getContextPath() + "/index.jsp"); //因为response.sendRedirect()是向浏览器输出数据的，所以它的地址也会按浏览器的来，那就会转到Tomcat的首页上，而我们要拿的是web工程下面的首页，所以要用request.getContextPath() = /项目名
			return;
		}
		
		//如果uesr等于null，就说明发生了错误，将错误信息反馈给客户
		request.setAttribute("message", "用户名或密码错误!");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
