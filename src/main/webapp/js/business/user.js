//chengyu created
var loginApp = angular.module('Login', []);
loginApp.controller("login",function($scope,$http){
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

var registApp = angular.module('Regist', []);
registApp.controller("regist",function($scope,$http,$interval){
	//变量初始化
	$scope.time = "获取验证码";
	//登录
	$scope.regist = function(){
		$scope.submitted = false;
		if($scope.registForm.$valid){
			$http({
				url:urls.api.user.regist,
				method:"post",
				params:{'userName':$scope.username,'password':$.md5($scope.password),'userPhone':$scope.cellphpne,
					"indentyCode":$scope.indentyCode} ,
			}).success(function(response){
				console.log("response:"+response);
			}).error(function(){
				console.log("error");
			});
		}else{
			$scope.submitted = true;
		}
	};
	//获取验证码
	$scope.getIndentifyCode = function($event){
		if($scope.registForm.cellphone.$invalid){
			return;
		}
		$event.target.disabled = true;
		
		$http({
			url:urls.api.user.indentifyCode,
			method:"get",
			params:{'userPhone':$scope.cellphpne} ,
		}).success(function(response){
			console.log("response:"+response);
		}).error(function(){
			console.log("error");
		});
		
	};
	
	//倒计时
	$scope.countDown = function($event){
		$event.target.disabled = true;
		count = 60;
		var timer = $interval(function(){
			count--;
			$scope.time = count + "s";
		},1000,59);
		
		timer.then(success);   
		function success(){
			$event.target.disabled = false;
		    $scope.time = "获取验证码";
		}
	};
});



