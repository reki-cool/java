package pw.produ.context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Title : CountServlet.java
 * @author: Pro.DU
 * @date  : 2016年10月31日下午2:20:55
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 统计网页(此servlet)的访问次数
 */
public class CountServlet extends HttpServlet {
	/**
	 * 实例被创建就调用init方法来初始化
	 * 在域对象中存入一个统计变量，赋值为0
	 */
	public void init() throws ServletException {
		getServletContext().setAttribute("count", 0);
	}

	/**
	 * 每一次访问，都会执行这个方法
	 * 拿出count的变量，自增一，然后再存回去
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先获取ServletContext对象
		ServletContext context = getServletContext();
		//获取count的值，并自增1,然后存回到 ServletContext对象中去
		Integer count = (Integer) context.getAttribute("count");
		context.setAttribute("count", ++count);
		
		//向页面中输出内容
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("欢迎您再来！！");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
