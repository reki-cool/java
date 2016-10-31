package pw.produ.redirect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title : ServletDemo1.java
 * @author: Pro.DU
 * @date  : 2016年10月31日下午5:24:35
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 实验目的：使用location和302一起完成重定向
 */
public class ServletDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//向页面输出内容
		response.setContentType("text/html;charset=UTF-8");//设置编码格式
		//response.getWriter().write("向班长借钱");
		//假设班长没钱
		response.setStatus(302);
		//告诉我副班长的地址
		response.setHeader("location", "/http_some_test/1.html");//重定向中的路径默认都是客户端的绝对路径
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
