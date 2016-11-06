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
		
		
		/**
		 * request获取中文的乱码
		 * 两种乱码情况
		 * 1、Post请求(经常使用的方式)
		 *		setCharacterEncoding(String env)设置request的缓冲区编码 
		 *		并且使用此方法设置缓冲区编码，一定要放在获取表单内容的操作之前！！！
		 *		
		 * 2、Get请求
		 * 		解决方法一：修改server.xml文件    -----------》》》不要使用这个解决方法，否则会影响其他项目
		 * 		<Connector port="80" protocol="HTTP/1.1"
		 * 				connectionTimeout="20000"
		 * 				redirectPort="8433" URIEncoding="UTF-8" />
		 * 		
		 * 		解决方法二：逆向编解码
		 * 		username = URLEncoder.encode(username, "ISO-8859-1");
		 * 		username = URLDecoder.decode(username, "UTF-8");
		 * 		简化，即：
		 * 		解决方法三：（推荐使用）----》如果是get提交，最好用这个方式解决
		 * 		username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		 * 		get方式提交时，假设原始数据是UTF-8编码，参数是放在请求行中的，传输过程中HTTP协议会对其进行默认编码方式编码，默认编码是ISO-8859-1，
		 * 		所以通过getBytes("ISO-8859-1")就可以得到用ISO-8859-1编码方式编码之前的原数据的二进制内容，
		 * 		再将此二进制数据使用UTF-8的格式进行编码。
		 */
		
		
		
		
		//防止POST方式提交产生的中文乱码,使用下面的语句
		//request.setCharacterEncoding("UTF-8");
		
		
		
		
		
		
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
