package pw.produ.reg;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先获取内容才能做其他的操作
		//下面的getParameter和getParameterValues方法中的参数都是表单中的name属性的内容，通过name属性获取到的是表单中的value属性的内容(有的value的内容有多个，比如checkbox)
		//---------------------
		String name = request.getParameter("username");  //获取姓名
		String pswd = request.getParameter("password");  //获取密码
		
		//获取性别(单选框)  返回一个String字串
		String sex = request.getParameter("sex");  //因为是单选框(radio)，所以值也有且仅有一个，所以直接用getParameter方法
		
		
		//获取城市(下拉列表)  返回一个String字串
		String city = request.getParameter("city");  //因为是下拉列表(select)，所以只能有一个选项值(option中的value),所以使用getParameter方法
		
		//获取爱好(多选框)  返回一个String字串数组
		String [] hobby = request.getParameterValues("hobby");  //因为是多选框(checkbox)，所以可能有多个值，使用getParameterValues方法
				
		//打印以上：
		System.out.println("name = "+name+";pswd = "+pswd+";sex = "+sex+";city = "+city+";hobby = "+Arrays.toString(hobby));
		
		System.out.println("------------------------------------------------------------------------------------");
		
		//通过map集合获取request中所有通过表单传过来的内容
		//使用getParameterMap这个方法，返回的是一个Map集合，Map集合的key对应表单传过来的name的值，Map集合的value对应表单传过来的value的值(值可能有多个)
		//所以返回的Map集合形式为Map<String, String []>
		Map<String, String []> map = request.getParameterMap(); 
		//循环遍历
		Set<String> keys = map.keySet(); //此方法返回一个Set集合,里面装着所有的key
		for(String key : keys) {
			String [] values = map.get(key);
			System.out.println(key + " = " + Arrays.toString(values));
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
