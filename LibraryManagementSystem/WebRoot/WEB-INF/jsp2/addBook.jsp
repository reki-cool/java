<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/AddBookServlet" method="post">
    	图书编号:
    	<input name="bkNum" type="text" />
    	<span>${form.errors.bkNum }</span><br><br>
    	
    	图书书名:
    	<input name="bkName" type="text" />
    	<span>${form.errors.bkName }</span><br><br>
    	
    	&nbsp;作者:&nbsp;
    	<input name="auName" type="text" />
    	<span>${form.errors.auName }</span><br><br>
    	
    	作者简介:
    	<input name="auInfo" type="text" /><br><br>
    	
    	&nbsp;出版社:
    	<input name="bkPub" type="text" />
    	<span>${form.errors.bkPub }</span><br><br>
    	
    	出版时间:
    	<input name="bkPubTime" type="text" />
    	<span>${form.errors.bkPubTime }</span><br><br>
    	
    	&nbsp;单价:&nbsp;
    	<input name="bkPrice" type="text" />
    	<span>${form.errors.bkPrice }</span><br><br>
    	
    	书籍简介:
    	<input name="bkInfo" type="text" /><br><br>
    	
    	&nbsp;语种:&nbsp;
    	<input name="bkLang" type="text" />
    	<span>${form.errors.bkLang }</span><br><br>
    	
    	&nbsp;标签:&nbsp;
    	<input name="bkTag" type="text" />
    	<span>${form.errors.bkTag }</span><br><br>
    	
    	&nbsp;类别:&nbsp;
    	<input name="typeName" type="text" />
    	<span>${form.errors.typeName }</span><br><br>
    	
    	&nbsp;人气:&nbsp;
    	<input name="bkPop" type="text" />
    	<span>${form.errors.bkPop }</span><br><br>
    	
    	<%--
    		入库时间:
    		<input name="bkInTime" type="text" />
    		<span>${form.errors.bkInTime }</span><br><br>
    	 --%>
    	
    	&nbsp;库存:&nbsp;
    	<input name="bkInv" type="text" />
    	<span>${form.errors.bkInv }</span><br><br>
    	
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input name="submit" type="submit" value="添加图书"/>
    </form>
  </body>
</html>
