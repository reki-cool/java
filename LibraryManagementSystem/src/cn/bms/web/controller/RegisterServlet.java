package cn.bms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bms.bean.User;
import cn.bms.exception.UserExistException;
import cn.bms.service.impl.BusinessServiceImpl;
import cn.bms.utils.WebUtils;
import cn.bms.web.formbean.RegisterForm;

//处理注册界面的请求
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); //因为用来注册的jsp界面需要调用request域里的数据，而request域里的数据里有中文，所以要将request的编码设置为UTF-8不然request传过去的数据就是一堆乱码，jsp页面用的编码是UTF-8，会无法识别
		
		//1.对提交表单的字段进行合法性校验(把表单数据封装到 formbean里面去)
		RegisterForm form = WebUtils.requestToForm(request, RegisterForm.class);
		
		boolean b = form.validate(); //表单数据封装好以后就进行校验
		
		//2.如果校验失败，则跳回到表单页面，回显校验失败的信息
		if(!b){ //如果b == false ,就会执行这个方法
			//request.getSession().setAttribute("form", form);
			
			request.setAttribute("form", form); //因为bean里的Map集合存有校验失败的错误信息，所以要把bean存到request域里，然后带到读者添加页面
			request.setAttribute("user", form);
			request.getRequestDispatcher("/AddUserAdminServlet").forward(request, response);
			//response.sendRedirect("http://localhost:8080/studio/RegisterUIServlet");
			return; //校验失败下面的代码就没有必要执行了，所以return
		}
		
		//3.如果校验成功，则调用Service处理注册请求
		User user = new User();
		WebUtils.copyBean(user, form);//将formbean里的的数据都复制到user里去
		user.setRdId(WebUtils.generateID());//对每个用户生成一个唯一的ID
		BusinessServiceImpl service = new BusinessServiceImpl(); //BusinessServiceImpl是处理注册请求的Service
		
		try {
			service.register(user);
			
			//4.如果Service处理成功，就跳转到网站的全局消息显示页面，为用户显示注册成功的消息
			request.setAttribute("message", "恭喜您，读者添加成功!!!网站将在3秒以后自动跳转到读者总览页面<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/FindAllUsersAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		} catch (UserExistException e) {
			//5.如果Service处理不成功，并且不成功的原因是因为用户已存在的话，则跳回到读者添加页面，显示读者已存在的消息
			
			request.setAttribute("message", "该读者已存在，请重新添加!!!网站将在3秒以后自动跳转到添加页面<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/AddUserAdminServlet'");
			request.setAttribute("user", form);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
//			form.getErrors().put("username", "该用户已存在，请重新注册!");
//			request.setAttribute("form", form);
//			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return;
		}catch(Exception e){
			//6.如果Service处理不成功，并且不成功的原因是其他问题的话，就跳转到网站的全局消息显示页面，为用户显示友好错误信息
			
			e.printStackTrace();//因为其他问题而导致处理不成功产生的异常不能抛给用户，所以要把它抓起来，然后在后台显示记录，以便于将来程序员根据异常来维护和改进系统
			
			request.setAttribute("message", "服务器出现未知错误!!将在3秒以后自动返回添加界面<meta http-equiv='refresh' content='3; url="+ request.getContextPath() +"/AddUserAdminServlet'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
