package pw.produ.servlet;

import javax.servlet.*;
import java.io.*;

public class HelloServlet extends GenericServlet{
	public void service (ServletRequest req, ServletResponse res) throws ServletException, java.io.IOException{
		res.getWriter().write("hello , welcome !");
	}
}