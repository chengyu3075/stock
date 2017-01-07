//chengyu created
var app = angular.module('Login', []);
app.controller("login",function($scope,$http){
	//登录
	$scope.login = function(){
		$http({
			url:urls.api.user.path+$scope.username+urls.api.user.login,
			method:"get",
			params:{'password':$scope.password} ,
		}).success(function(response){
			if(response.resultCode==20){
				window.location.href=urls.web.home;
			}
        }).error(function(){
            console.log("error");
        });
	};
});