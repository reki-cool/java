package pw.produ.context;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Title : ContextDemo1.java
 * @author: Pro.DU
 * @date  : 2016年10月31日上午8:29:24
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 获取全局初始化参数
 */
public class ContextDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先获取ServletContext对象
		ServletContext context = getServletContext();
		//获取初始化参数
		String encoding = context.getInitParameter("encoding");
		System.out.println("编码方式为："+encoding);
		
		Enumeration<String> e = context.getInitParameterNames();
		while(e.hasMoreElements()){
			String name = e.nextElement();
			//通过name获取name的值
			String value = context.getInitParameter(name);
			System.out.println(name + " : " + value);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
