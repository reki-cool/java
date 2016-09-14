<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册界面</title>
    
    <script type="text/javascript">
    	function refreshImg(){
    		document.getElementById("image").src = document.getElementById("image").src + "?nocecha=" + new Date().getTime(); //改变验证码图片的地址，浏览器发现图片的地址改变以后就会重新加载这个图片
    	}
    </script>
    
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/RegisterServlet" method="post">
    	<div>
			&nbsp;用户名: &nbsp;
			<input name="username" type="text" placeholder="请输入3-8位的英文字母" />
			<span>${form.errors.username }</span>
		</div>
		<br>
		<div>
			&nbsp;密&nbsp;码: &nbsp;
			<input name="password" type="password" placeholder="请输入3-8位的数字" />
			<span>${form.errors.password }</span>
		</div>
		<br>
		<div>
			确认密码: &nbsp;
			<input type="password" name="password2" placeholder="请再一次输入密码"/>
			<span>${form.errors.password2 }</span>
		</div>
		<br>
		<div>
			&nbsp;邮&nbsp;箱: &nbsp;
			<input type="text" name="email" placeholder="请输入正确格式的邮箱"/>
			<span>${form.errors.email }</span>
		</div>
		<br>
		<div>
			&nbsp;生&nbsp;日: &nbsp;
			<input type="text" name="birthday" placeholder="生日的格式为2008-08-08"/>
			<span>${form.errors.birthday }</span>
		</div>
		<br>
		<div>
			&nbsp;昵&nbsp;称: &nbsp;
			<input type="text" name="nickname" placeholder="请输入3-6位的中文昵称"/>
			<span>${form.errors.nickname }</span>
		</div>
		<br>
		<div>
			&nbsp;验证码: &nbsp;
			<input type="text" name="verification" placeholder="请输入验证码"/>
    		<img src="RandomImageServlet" id="image" align="middle"/>
    		<a href="" onclick="refreshImg()">看不清，换一张</a>
			<span>${form.errors.verification }</span>
		</div>
    	
    	<div>
    		<span><input type="submit" name="submit" value="注册" /></span>
    		
    		<span><input type="reset" name="reset" value="重置" /></span>
    	</div>
    </form>
  </body>
</html>
