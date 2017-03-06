<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>股票笔记-注册</title>
	
	<%@include file = "basic/css_src.jsp" %>
	<%@include file = "basic/js_src.jsp" %>
	
	<script src="js/business/user.js"></script>
	<script src="js/util/jquery.md5.js"></script>
	
	 <style type="text/css">
		span {font-size: 10px}
	</style>

  </head>
  

  
  <body>
 	<div class="container" ng-app="Regist"  ng-controller="regist">
			<fieldset>
				<div class="row">
					<div class="col-xs-12">
						 <legend>注册</legend> 
					</div>
				</div>
				 <div class="row">
				 	<div class="col-xs-6 col-xs-offset-4">
						 <h3>股票笔记</h3>
					 </div>
				 </div>
					 <div class="row">
							<form class="form-horizontal" name="registForm" ng-submit="regist()" novalidate>
							  <div class="form-group">
							    <label for="username" class="col-sm-2 col-sm-offset-2 control-label">用户名:</label>
							    <div class="col-sm-4">
							        <input type="text" class="form-control" id="username" ng-keyup="user.usernameUsed=false" name="username" ng-model="username"  placeholder="用户名(6-24数字字母)"
							        ng-pattern="/^[A-Za-z0-9]{6,16}$/" required>
								    <span style="color:red " ng-show="registForm.username.$invalid && submitted">
										请输入正确的用户名
									 </span>
									<span style="color:red" ng-show="user.usernameUsed">
										用户名已被占用
								  	</span>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="cellphone" class="col-sm-2 col-sm-offset-2 control-label">手&nbsp;&nbsp;&nbsp;&nbsp;机:</label>
							    <div class="col-sm-4">
							      <input type="text" class="form-control" ng-keyup="user.phoneUsed=false"  name="cellphone" id="cellphone" ng-model="cellphpne" placeholder="手机号码"
							     	 ng-pattern="/(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/" required>
							      <span style="color:red" ng-show="registForm.cellphone.$invalid && submitted">
										<span ng-show="registForm.cellphone.$invalid && submitted">请输入正确的手机号码</span>
								  </span>
								 <span style="color:red" ng-show="user.phoneUsed">
										手机号已被占用
								  </span>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="password" class="col-sm-2 col-sm-offset-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
							    <div class="col-sm-4">
							      <input type="password" class="form-control" id="password" name="password" ng-model="password" placeholder="8~20位字母、数字或字符，至少包含两种"
							     	 ng-pattern="/^[A-Za-z0-9]{6,16}$/" required>
							      <span style="color:red" ng-show="registForm.password.$invalid && submitted">
										<span ng-show="registForm.password.$invalid">密码过于简单</span>
								  </span>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="indentyCode" class="col-sm-2 col-sm-offset-2 control-label">验证码:</label>
							    <div class="col-sm-2">
							      <input type="text" ng-keyup="user.indentifyError=false,user.indentifyTimeout=false" class="form-control" id="indentyCode" name="indentyCode" ng-model="indentyCode" placeholder="手机验证码" required>
							      <span style="color:red" ng-show="registForm.indentyCode.$invalid ">
										<span ng-show="registForm.indentyCode.$error.required && submitted">验证码不能为空</span>
								  </span>
								  <span style="color:red" ng-show="user.indentifyError">
										验证码错误
								  </span>
								 <span style="color:red" ng-show="user.indentifyTimeout">
										验证码已过期
								  </span>
							    </div>
							    <div class="col-sm-2">
							    	<button type="button" ng-click="getIndentifyCode($event)" class="btn btn-info">{{time}}</button>
							  	</div>
							  </div>
							  <div class="form-group">
							    <div class="col-sm-offset-4 col-sm-10">
							      <div class="checkbox">
							        <label>
							          <input type="checkbox" name="aggreed" ng-model="aggreed" required>我已阅读并接受<a href="#">《股票笔记用户服务协议》</a>
							        </label>
							      </div>
							    </div>
							  </div>
							  <div class="form-group">
							    <div class="col-sm-offset-4 col-sm-6">
							        <button type="submit" ng-disabled= "registForm.aggreed.$error.required" class="btn btn-success btn-lg">注册</button>
							    </div>
							  </div>
						</form>
					  </div>
			</fieldset>
		</div>
  </body>
</html>
