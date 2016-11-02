package pw.produ.download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoad extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRemoteAddr());//获取发送此请求的地址
		//获取文件的绝对磁盘路径(获取到的绝对磁盘路径在本地是这样的：E:\Install_Soft\Tomcat\webapps\download\myDownLoad\smile.gif)
		String path = getServletContext().getRealPath("/myDownLoad/笑一笑啊.gif");
		int index = path.lastIndexOf("\\");
		String filename = null;
		if(index != -1){
			filename = path.substring(index+1);
		}
		if(filename != null) {
			//判断是什么浏览器
			String agent = request.getHeader("User-Agent");
			//判断如果是IE浏览器（IE11中是like Gecko）
			if(agent.contains("MSIE")){
				filename = URLEncoder.encode(filename,"UTF-8");
			}
			//设置以附件形式打开文件的头信息
			response.setHeader("Content-Disposition", "attachment;filename="+filename);
			InputStream in = new FileInputStream(path);//将文件读入到输入流中。
			OutputStream out = response.getOutputStream();//设置输出流
			//将字节输入流中的内容通过字节数组拷贝到字节输出流中(简称IO拷贝)
			byte[] b = new byte[1024];
			int len = 0;
			while((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			//关闭输入输出流
			in.close();
			out.close();
		}
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
