package pw.produ.context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Title : ShowCount.java
 * @author: Pro.DU
 * @date  : 2016年10月31日下午2:22:25
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 显示网页(CountServlet)被访问的次数
 */
public class ShowCount extends HttpServlet {
	
	/**
	 * 获取网站的访问次数，输出到客户端
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer count = (Integer) getServletContext().getAttribute("count");
		//向页面输出
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("CountServlet这个网页一共被访问了 " + count + " 次！");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
