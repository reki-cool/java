package pw.produ.nocache;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCache extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置三个头信息禁用缓存：
		response.setHeader("Cache-Controle", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);  //这个和日期相关，所以用setDateHeader方法
		
		//这个例子在IE6中可以观察的很明显，如果不设置禁用缓存，浏览地址栏按回车，页面时间不变，如果设置了禁用缓存，浏览地址栏按回车，页面时间会刷新
		
		//向页面输出当前的时间
		//获取当前时间
		Date date = new Date();
		//确定时间格式     这些可以通过查看Java JDK文档来写
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化当前时间为一个字符串
		String dateString = myformat.format(date);
		//输出当前时间
		response.getWriter().write(dateString);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
