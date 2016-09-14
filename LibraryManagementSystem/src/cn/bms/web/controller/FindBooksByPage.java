package cn.bms.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bms.bean.Book;
import cn.bms.service.impl.BusinessServiceImpl;

public class FindBooksByPage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currPage = 1; 
		if(request.getParameter("page") != null) {
			currPage = Integer.parseInt(request.getParameter("page"));
		}
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Book> list = service.findBooksByPage(currPage);
		request.setAttribute("bookList", list);
		int pages;
		int count = service.findCount();
		if(count % 5 == 0) {
			pages = count / 5;
		}
		else {
			pages = count / 5+1;
		}
		StringBuffer sb = new StringBuffer();
//	<ul>
//        <li><a href="#">Prev</a></li>
//        <li><a href="#">1</a></li>
//        <li><a href="#">2</a></li>
//        <li><a href="#">3</a></li>
//        <li><a href="#">4</a></li>
//        <li><a href="#">Next</a></li>
//    </ul>
		sb.append("<ul>");
		if(currPage == 1) sb.append("<li><a style=\"color:#000;\">Prev</a></li>");
		else sb.append("<li><a href='FindBooksByPage?page=" + (currPage-1) + "'>Prev</a></li>");
		for(int i = 1; i <= pages; i++) {
			if(i == currPage) {
				sb.append("<li><a style=\"color:#000;\">" + i + "</a></li>");
			}
			else {
				sb.append("<li><a href='FindBooksByPage?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if(currPage == pages) sb.append("<li><a style=\"color:#000;\">Next</a></li>");
		else sb.append("<li><a href='FindBooksByPage?page=" + (currPage+1) + "'>Next</a></li>");
		sb.append("</ul>");
		request.setAttribute("bar", sb.toString());
		request.getRequestDispatcher("/WEB-INF/jsp/books_list.jsp").forward(request, response);
	}

}
