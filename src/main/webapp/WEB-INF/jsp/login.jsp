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
	
	<script src="js/business/user.js"></script>
	<script src="js/util/jquery.md5.js"></script>
  </head>
  <body ng-app="Login">
	<div class="container"  ng-app="Login"  ng-controller="login">
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
							 	<div class="col-xs-4 col-xs-offset-5">
									 <h3>投资笔记</h3>
								 </div>
							 </div>
							<form class="form-horizontal" name="loginform" ng-submit="login()" novalidate>
								 <div class="row">
									 <div class="col-xs-3 col-xs-offset-4">
									 	<label for="username">手机号:</label>
										 <input type="number"  id="username" name="username" ng-keyup="result.authError=false" ng-model="username" class="form-control"  placeholder="手机号" required/>
										 <span style="color:red" ng-show="loginform.username.$error.required && submitted">
											手机号不能为空
										  </span>
									 </div>
								 </div>
								 <div  class="row">
									 <div class="col-xs-3 col-xs-offset-4">
										 <br/>
										 <label for="password">密码:</label>
										 <input type="password"  id="password" name="password" ng-keyup="result.authError=false" ng-model="password" class="form-control" placeholder="密码" required/>
										 <span style="color:red" ng-show="loginform.password.$error.required && submitted">
											请输入密码
										  </span>
										  <span style="color:red" ng-show="result.authError">
											用户名密码错误
										  </span>
									 </div>
								 </div>
								 
								 <div class="row" >
									<div class="col-xs-1 col-xs-offset-4">
										<a href="web/user/regist/page" target="_self">找回密码</a>
									 </div>
								  </div>
	
								  <div class="row" style="margin-top:25px">
									 <div class="col-xs-1 col-xs-offset-5">
									 	<button type="submit" class="btn btn-success">登录</button>
									 </div>
									<div class="col-xs-1" style="margin-top:17px">
										<a href="web/user/regist/page" target="_self">我要注册</a>
									 </div>
							  	</div>
						</form>
						</fieldset>
					</div>
			</div>
		</div>
	</div>

  </body>
</html>