<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投资笔记</title>
    
	<%@include file = "basic/css_src.jsp" %>
	<%@include file = "basic/js_src.jsp" %>

  </head>
  
  <body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="container-fluid">
	    <div class="navbar-header">
	        <a class="navbar-brand" href="#">投资笔记</a>
	    </div>
		    <div>
		        <ul class="nav navbar-nav">
		            <li class="active"><a href="#">资讯</a></li>
		            <li><a href="#">股票</a></li>
		            <li><a href="#">笔记</a></li>
					<!-- <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                    笔记 <b class="caret"></b>
		                </a>
		                <ul class="dropdown-menu">
		                    <li><a href="#">jmeter</a></li>
		                    <li><a href="#">EJB</a></li>
		                    <li><a href="#">Jasper Report</a></li>
		                    <li class="divider"></li>
		                    <li><a href="#">分离的链接</a></li>
		                    <li class="divider"></li>
		                    <li><a href="#">另一个分离的链接</a></li>
		                </ul>
		            </li> -->
		        </ul>
		        
		        <div>
			        <form class="navbar-form navbar-left" role="search">
			            <div class="form-group">
			                <input type="text" class="form-control" placeholder="搜索">
			            </div>
			        </form>
			     </div>
		        <shiro:guest>
			         <ul class="nav navbar-nav navbar-right">
			        	<li><a href="web/user/regist/page" target="_self""><span class="glyphicon glyphicon-user"></span> 注册</a></li>
						<li><a href="web/user/login/page" target="_self"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
			        </ul>
		        </shiro:guest>
		        <shiro:user>
		        	<ul class="nav navbar-nav navbar-right">

		        		<li class="dropdown">
			        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" target="_self"><span class="glyphicon glyphicon-user"></span> <shiro:principal/>
			        			<b class="caret"></b>
			        		</a>
		        			<ul class="dropdown-menu">
		        				 <li><a href="user/logout">退出登录</a></li>
		        			</ul>
		        		</li>
		        	</ul>
		        </shiro:user>
		      </div>
		</div>
	</nav>
    
    
  </body>
</html>
