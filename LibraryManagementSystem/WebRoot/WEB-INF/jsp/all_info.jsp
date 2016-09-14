<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta charset="utf-8">
    <title>江理图书管理系统</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="图书馆管理系统">
    <meta name="author" content="都颜汗">

    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    
    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
        .glyphicon-book:before{content:"\e043"}
    </style>

    <!-- Le jsp5 shim, for IE6-8 support of jsp5 elements -->
    <!--[if lt IE 9]>
      <script src="http://jsp5shim.googlecode.com/svn/trunk/jsp5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
    <div class="navbar">
       <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                    <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">设置</a></li>
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i>
                            	<c:if test="${admin != null }">
                            		${admin.admName }
                            	</c:if>
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">个人账户</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" class="visible-phone" href="#">设置</a></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="${pageContext.request.contextPath }/LogoutServlet">退出登录</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="${pageContext.request.contextPath }/IndexAdminServlet"><span class="first">江理图书馆</span> <span class="second">管理系统</span></a>
        </div>
    </div>
    


    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>





    <div class="sidebar-nav">
        <form class="search form-inline">
            <input type="text" placeholder="搜索...">
        </form>

		<a href="${pageContext.request.contextPath }/AllInfoAdminServlet" class="nav-header" ><i class="icon-home"></i>概览面板</a>
       
        <a href="#book-menu" class="nav-header" data-toggle="collapse"><i class="icon-book"></i>图书管理<i class="icon-chevron-up"></i></a>
        <ul id="book-menu" class="nav nav-list collapse in">
            <li ><a href="${pageContext.request.contextPath }/FindAllBooksAdminServlet">总览图书列表</a></li>
            <li ><a href="${pageContext.request.contextPath }/FindBookAdminServlet">查询删改图书</a></li>
            <li ><a href="${pageContext.request.contextPath }/AddBookAdminServlet">添加图书信息</a></li>
        </ul>

        <a href="#reader-menu" class="nav-header" data-toggle="collapse"><i class="icon-user"></i>读者管理<i class="icon-chevron-up"></i></a>
        <ul id="reader-menu" class="nav nav-list collapse in">
            <li ><a href="${pageContext.request.contextPath }/FindAllUsersAdminServlet">总览读者列表</a></li>
            <li ><a href="${pageContext.request.contextPath }/FindUserAdminServlet">查询删改读者</a></li>
            <li ><a href="${pageContext.request.contextPath }/AddUserAdminServlet">添加读者信息</a></li>
        </ul>

        <a href="#borrow-menu" class="nav-header" data-toggle="collapse"><i class="icon-retweet"></i>借阅管理<i class="icon-chevron-up"></i></a>
        <ul id="borrow-menu" class="nav nav-list collapse in">
            <li ><a href="borrows_list.jsp">总览借阅列表</a></li>
            <li ><a href="rud_borrow.jsp">借阅信息查询</a></li>
            <li ><a href="add_borrow.jsp">修改借阅信息</a></li>
        </ul>

        <a href="reset-password.jsp" class="nav-header" ><i class="icon-edit"></i>重置密码</a>

    </div>

    
    <div class="content">
        
        <div class="header">
            <div class="stats">   <!-- 右浮动 -->
                <p class="stat"><span class="number" id="t_day"></span>日</p>
                <p class="stat"><span class="number" id="t_month"></span>月</p>
                <p class="stat"><span class="number" id="t_year"></span>年</p>
            </div>
           <!--  <div class="stats">  
                <p class="stat"><span class="number">23</span>秒</p>
                <p class="stat"><span class="number">4</span>分</p>
                <p class="stat"><span class="number">17</span>时</p>
            </div> -->

            <h1 class="page-title">概览面板</h1>  <!--  左浮动 -->
        </div>
        
        <ul class="breadcrumb">
            <li><a href="all-info.jsp">管理主页</a><span class="divider">/</span></li>
            <li class="active">概览面板</li>
        </ul>

<!--*********************************************************************-->

<div class="container-fluid">
    <div class="row-fluid">

        <div class="row-fluid">  <!-- 行①包含两部分 -->

            <!-- <div class="alert alert-info">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <strong>Just a quick note:</strong> Hope you like the theme!
            </div>
 -->
            <div class="block">
                <a href="#page-stats" class="block-heading" data-toggle="collapse">当前状态</a>
                <div id="page-stats" class="block-body collapse in">

                    <div class="stat-widget-container">
                        <div class="stat-widget">
                            <div class="stat-button">
                                <p class="title">2,500</p>
                                <p class="detail">当前在库图书数量</p>
                            </div>
                        </div>

                        <div class="stat-widget">
                            <div class="stat-button">
                                <p class="title">3,299</p>
                                <p class="detail">在库读者数量</p>
                            </div>
                        </div>

                        <div class="stat-widget">
                            <div class="stat-button">
                                <p class="title">1,500</p>
                                <p class="detail">总借阅记录</p>
                            </div>
                        </div>

                        <div class="stat-widget">
                            <div class="stat-button">
                                <p class="title">￥12,675</p>
                                <p class="detail">总逾期罚金</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

<div class="row-fluid">
    <div class="block span6">
        <a href="#tablewidget" class="block-heading" data-toggle="collapse">读者列表概览</a>
        <div id="tablewidget" class="block-body collapse in">
            <table class="table">
              <thead>
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Username</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Mark</td>
                  <td>Tompson</td>
                  <td>the_mark7</td>
                </tr>
                <tr>
                  <td>Ashley</td>
                  <td>Jacobs</td>
                  <td>ash11927</td>
                </tr>
                <tr>
                  <td>Audrey</td>
                  <td>Ann</td>
                  <td>audann84</td>
                </tr>
                <tr>
                  <td>John</td>
                  <td>Robinson</td>
                  <td>jr5527</td>
                </tr>
                <tr>
                  <td>Aaron</td>
                  <td>Butler</td>
                  <td>aaron_butler</td>
                </tr>
                <tr>
                  <td>Chris</td>
                  <td>Albert</td>
                  <td>cab79</td>
                </tr>
              </tbody>
            </table>
            <p><a href="users.jsp">查看更多...</a></p>
        </div>
    </div>
    <div class="block span6">
        <a href="#widget1container" class="block-heading" data-toggle="collapse">图书列表概览 </a>
        <div id="widget1container" class="block-body collapse in">
           <table class="table">
              <thead>
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Username</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Mark</td>
                  <td>Tompson</td>
                  <td>the_mark7</td>
                </tr>
                <tr>
                  <td>Ashley</td>
                  <td>Jacobs</td>
                  <td>ash11927</td>
                </tr>
                <tr>
                  <td>Audrey</td>
                  <td>Ann</td>
                  <td>audann84</td>
                </tr>
                <tr>
                  <td>John</td>
                  <td>Robinson</td>
                  <td>jr5527</td>
                </tr>
                <tr>
                  <td>Aaron</td>
                  <td>Butler</td>
                  <td>aaron_butler</td>
                </tr>
                <tr>
                  <td>Chris</td>
                  <td>Albert</td>
                  <td>cab79</td>
                </tr>
              </tbody>
            </table>
            <p><a href="users.jsp">查看更多...</a></p>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="block span12">
        <div class="block-heading">
            <span class="block-icon pull-right">
                <a href="#" class="demo-cancel-click" rel="tooltip" title="Click to refresh"><i class="icon-refresh"></i></a>
            </span>

            <a href="#widget2container" data-toggle="collapse">历史借阅记录概览</a>
        </div>
        <div id="widget2container" class="block-body collapse in">
            <table class="table list">
              <tbody>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Mark Otto</p>
                      </td>
                      <td>
                          <p>Amount: $1,247</p>
                      </td>
                      <td>
                          <p>Date: 7/19/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Audrey Ann</p>
                      </td>
                      <td>
                          <p>Amount: $2,793</p>
                      </td>
                      <td>
                          <p>Date: 7/12/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Mark Tompson</p>
                      </td>
                      <td>
                          <p>Amount: $2,349</p>
                      </td>
                      <td>
                          <p>Date: 3/10/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Ashley Jacobs</p>
                      </td>
                      <td>
                          <p>Amount: $1,192</p>
                      </td>
                      <td>
                          <p>Date: 1/19/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                    
              </tbody>
            </table>
            <p><a href="users.jsp">查看更多...</a></p>
        </div>
    </div>
    <!-- <div class="block span6">
        <p class="block-heading">Not Collapsible</p>
        <div class="block-body">
            <h2>Tip of the Day</h2>
            <p>Fava bean jícama seakale beetroot courgette shallot amaranth pea garbanzo carrot radicchio peanut leek pea sprouts arugula brussels sprout green bean. Spring onion broccoli chicory shallot winter purslane pumpkin gumbo cabbage squash beet greens lettuce celery. Gram zucchini swiss chard mustard burdock radish brussels sprout groundnut. Asparagus horseradish beet greens broccoli brussels sprout bitterleaf groundnut cress sweet pepper leek bok choy shallot celtuce scallion chickpea radish pea sprouts.</p>
            <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
        </div>
    </div> -->
</div>


                    
                    <footer>
                        <hr>
                        
                        <p class="pull-right">Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
                        

                        <p>&copy; 2012 <a href="#" target="_blank">Portnine</a></p>
                    </footer>
                    
            </div>
        </div>
<!--*********************************************************************-->
</div>
    


    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        window.onload = function(){
        	var date_time = new Date();
        	var year = date_time.getFullYear();
        	var month = date_time.getMonth()+1;
        	if(month < 10) month = "0" + month;
        	var day = date_time.getDate();
        	if(day < 10) day = "0" + day;
        	document.getElementById("t_year").innerHTML= year;
        	document.getElementById("t_month").innerHTML= month;
        	document.getElementById("t_day").innerHTML= day;
        }
    </script>
    
  </body>
</html>