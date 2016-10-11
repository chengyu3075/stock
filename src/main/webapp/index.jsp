<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>team project</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file = "css_src.jsp" %>
	<%@include file = "js_src.jsp" %>
	
	<script src="js/angularjs/login.js"></script>
  </head>
  <body ng-app="Login">
	<div class="container"  ng-controller="login">
		<div class="row">
			<div class="span12">
					<div class="form-group">
						<fieldset>
							 <legend>登录</legend> 
							 <div class="col-xs-9">
								 <input type="text" ng-model="username" class="form-control"  placeholder="用户名"/>
							 </div>
							 <div class="col-xs-9">
								 <br/><input type="password" ng-model="password" class="form-control" placeholder="密码"/>
							 </div>
							 <div class="col-xs-9">
								 <label class="checkbox" style="margin-left:20px"><input type="checkbox" />记住我</label> 
							 </div>
							 <div class="col-xs-6">
							 	<button type="submit" ng-click="login()" class="btn">提交</button>
							 </div>
						</fieldset>
					</div>
			</div>
		</div>
	</div>
    
    
    
    
    
    
    
    
    
    
    
    
    

  </body>
</html>
