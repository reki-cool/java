package pw.produ.context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Title : ReadServlet.java
 * @author: Pro.DU
 * @date  : 2016年10月31日下午3:13:21
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 读取资源文件
 */
public class ReadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//read1();//传统的读取文件的方法
		read2();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 传统方式读取资源文件
	 * 这种传统方式，理论是正确的，但是交给服务器运行后，路径将会无法找到！！！！！疯狂报错！！！
	 * @throws IOException 
	 *//*
	public void read1() throws IOException {
		//获取输入流
		InputStream in = new FileInputStream("src/db.properties");//注意这里的路径是错误的
		print(in);
	}*/
	
	/**
	 * 获取src目录下的db.properties文件
	 * @throws IOException
	 */
	public void read2() throws IOException{
		//利用ServletContext读取文件
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");//这里的路径要写服务器端的路径，而不是客户端的路径，注意区别就在于前者不用项目名，后者需要！！！
		//打印方式
		print(in);
	}
	
	/**
	 * 在控制台中打印读取到的文件的内容
	 * @param in
	 * @throws IOException
	 */
	public void print(InputStream in) throws IOException {
		//创建properties对象
		Properties pro = new Properties();
		//将输入流加载到properties对象中
		pro.load(in);
		//获取文件中的内容
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");
		String desc = pro.getProperty("desc");
		
		System.out.println("用户名：" + username);
		System.out.println("密    码：" + password);
		System.out.println("描    述：" + desc);
	}
}
