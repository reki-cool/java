package pw.produ.bean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test_daolian_fandaolian extends HttpServlet {

	public Test_daolian_fandaolian() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		/*
		 * 向页面输出文本内容： out.write("<h1>开始播放啦~</h1>");
		 * out.write("<a href='http://www.kacat.pw/233.mp3'>思念是一种病</a>");
		 */

		String referer = request.getHeader("Referer");
		// out.write(referer);

		// 反盗链：
		if (referer.startsWith("http://localhost:8080/daolian_fandaolian/goodweb/")) {
			response.getWriter().write("<h1>开始播放啦~</h1><br><a href='http://www.kacat.pw/233.mp3'>思念是一种病</a>");
		} else {
			response.getWriter().write("滚，臭不要脸~，别想盗链！");
		}

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {

	}

}
