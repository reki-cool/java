package pw.produ.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Title : LoginServlet.java
 * @author: Pro.DU
 * @date  : 2016年11月1日上午2:22:47
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 重定向到登陆页面
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 先获取用户输入的内容--》找request对象
		 * 获取对象内容，然后判断姓名密码是否是admin，
		 * 如果任意一个不正确，重定向到登陆页面，如果都正确，就登陆成功
		 */
		
		//获取用户输入的内容
		String username = request.getParameter("username");
		//获取密码
		String password = request.getParameter("password");
		//判断
		if("admin".equals(username) && "admin".equals(password)) {
			//登陆成功
			response.getWriter().write("Login Success");
		}
		else {
			//重定向到登陆页面
			//设置302状态吗
			/*response.setStatus(302);
			//设置地址
			response.setHeader("location", "/redirect/response/login.html");*/
			
			response.sendRedirect("/redirect/response/login.html");//这个方法的底层就是上面两行代码
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
