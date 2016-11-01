package pw.produ.refresh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Title : RefreshServlet.java
 * @author: Pro.DU
 * @date  : 2016年11月1日上午10:12:55
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 利用refresh进行页面的定时跳转
 */
public class RefreshServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//读秒操作
		
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//这种写法最标准，下面的也行
			//response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("<h1>页面将在3秒之后跳转回登陆页面</h1>");
		//通过refresh完成页面定时跳转(此处就是页面刷新,因为还是回到之前的那个页面)
		response.setHeader("refresh", "3;url=/refresh/response/login.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	//浏览器访问链接：http://localhost:8080/refresh/RefreshServlet
}
