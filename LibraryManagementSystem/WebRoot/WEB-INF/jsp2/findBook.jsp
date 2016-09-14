<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/FindBookServlet" method="post">
    	<input name="bkName" type="text" placeholder="请输入书名" />
    	<input name="submit" type="submit" value="搜索" />
    	<input name="submit2" type="submit" value="全部查找" />
    </form>
    <table>
    	<tr>
    		<th>图书编号</th>
    		<th>图书书名</th>
    		<th>作者</th>
    		<th>作者简介</th>
    		<th>出版社</th>
    		<th>出版时间</th>
    		<th>单价</th>
    		<th>书籍简介</th>
    		<th>语种</th>
    		<th>标签</th>
    		<th>类别</th>
    		<th>人气</th>
    		<th>入库时间</th>
    		<th>库存</th>
    	</tr>
    	<c:forEach items="${bookList }" var="book" varStatus="status">
    		<tr>
    			<td>${book.bkNum }</td>
    			<td>${book.bkName }</td>
    			<td>${book.auName }</td>
    			<td>${book.auInfo }</td>
    			<td>${book.bkPub }</td>
    			<td>${book.bkPubTime }</td>
    			<td>${book.bkPrice }</td>
    			<td>${book.bkInfo }</td>
    			<td>${book.bkLang }</td>
    			<td>${book.bkTag }</td>
    			<td>${book.typeName }</td>
    			<td>${book.bkPop }</td>
    			<td>${book.bkInTime }</td>
    			<td>${book.bkInv }</td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
