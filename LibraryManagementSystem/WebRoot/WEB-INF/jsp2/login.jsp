<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title></title>
        
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" />
    </head>
    
    <body>
    	<div id="container">
        	<div id="login">
            	<div id="form">
                	<form action="${pageContext.request.contextPath }/LoginServlet" method="post">
                    	<div id="input">
                            <div>用户:<input type="text" name="username" /></div>
                            <div>密码:<input type="password" name="password" /></div>
                        </div>
                        <div id="button">
                            <input type="submit" value="登录" />
                            <input type="button" value="注册" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>