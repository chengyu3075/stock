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
	<%@include file = "basic/css_src.jsp" %>
	<%@include file = "basic/js_src.jsp" %>
	
	<script src="js/angularjs/login.js"></script>
  </head>
  <body ng-app="Login">
	<div class="container"  ng-controller="login">
		<div class="row">
			<div class="span12">
					<div class="form-group">
						<fieldset>
							<div class="row">
								<div class="col-xs-12">
									 <legend>登录</legend> 
								</div>
							</div>
							 <div class="row">
							 	<div class="col-xs-4 col-xs-offset-4">
									 <h3>Team管理系统</h3>
								 </div>
							 </div>
							 <div class="row">
								 <div class="col-xs-4 col-xs-offset-4">
									 <input type="text"  ng-model="username" class="form-control"  placeholder="用户名" required/>
								 </div>
							 </div>
							 <div  class="row">
								 <div class="col-xs-4 col-xs-offset-4">
									 <br/><input type="password" ng-model="password" class="form-control" placeholder="密码" required/>
								 </div>
							 </div>
							 <div class="row">
								 <div class="col-xs-4 col-xs-offset-4">
									 <label class="checkbox" style="margin-left:20px"><input type="checkbox" />记住我</label> 
								 </div>
							 </div>
							  <div class="row">
								 <div class="col-xs-6 col-xs-offset-4">
								 	<button type="submit" ng-click="login()" class="btn">提交</button>
								 </div>
							  </div>
						</fieldset>
					</div>
			</div>
		</div>
	</div>

  </body>
</html>