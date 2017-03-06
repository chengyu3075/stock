//chengyu created
var loginApp = angular.module('Login', []);
loginApp.controller("login",function($scope,$http){
	//登录
	$scope.login = function(){
		$scope.result = {
				authError:false
		};
		if($scope.loginform.$valid){
			$http({
				url:urls.api.user.path+$scope.username+urls.api.user.login,
				method:"get",
				params:{'password':$.md5($scope.password)} ,
			}).success(function(response){
				if(response.resultCode==20){
					window.location.href=urls.web.home;
				}else{
					$scope.result.authError = true;
				}
			}).error(function(){
				console.log("error");
			});
		}else{
			$scope.submitted = true;
		}
	};
});

var registApp = angular.module('Regist', []);
registApp.controller("regist",function($scope,$http,$interval){
	//变量初始化
	$scope.time = "获取验证码";
	$scope.user = {	
			indentifyError:false,
			phoneUsed:false,
			usernameUsed:false,
			indentifyTimeout:false,
	};
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
				console.log(response.resultCode);
				switch (response.resultCode){
					case 10:{    //成功
						//TODO 跳转到主页并登陆
					}
					case 11:{    //用户已注册
						$scope.user.usernameUsed = true;
					}
					case 12:{    //验证码错误
						$scope.user.indentifyError = true;
					}
					case 32:{    //验证码已过期
						$scope.user.indentifyTimeout = true;
					}
					case 15:{    //手机号被占用
						$scope.user.phoneUsed = true;
					}
					break;
				}
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
		
		}).error(function(){
			console.log("error");
		});
		var count = 60;
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



