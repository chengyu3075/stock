//chengyu created
var app = angular.module('Login', []);
app.controller("login",function($scope,$http){
	//登录
	$scope.login = function(){
		$http({
			url:urls.web.user.path+$scope.username+urls.web.user.login,
			method:"get",
			params:{'password':$scope.password} ,
		    headers: {
		        'Content-Type': 'application/json;charset=utf-8'
		   }
		}).success(function(){
            console.log("success!");
        }).error(function(){
            console.log("error");
        });
	};
});