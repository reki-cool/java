package pw.produ.redirect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title : ServletDemo2.java
 * @author: Pro.DU
 * @date  : 2016年10月31日下午6:02:15
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 页面定时跳转
 */
public class ServletDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//向页面输出内容
		response.setContentType("text/html;charset=UTF-8");//设置编码格式
		response.getWriter().write("哈哈哈哈哈.......");
		
		response.setHeader("refresh", "3;url=/http_some_test/1.html");//3秒钟之后跳转到1.html去
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
