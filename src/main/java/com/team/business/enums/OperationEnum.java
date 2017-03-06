package com.team.business.enums;

public enum OperationEnum {
	REGIST_SUCCESS(10,"注册成功"),
	REGIST_USERNAME_USED(11,"用户已注册"),
	REGIST_PHONE_USED(15,"手机号已被占用"),
	IDENTIFIER_CODE_ERROR(12,"验证码错误"),
	IDENTIFIER_CODE_EXPIRED(13,"验证码已过期"),
	
	OPERATION_SUCCESS(1,"成功"),
	
	LOGIN_SUCCESS(20,"登录成功"),
	LOGIN_NO_USER(21,"用户未注册"),
	LOGIN_USERNAME_PASSWORD_ERROR(22,"用户名密码错误"),
	
	SYSTEM_ERROR(-1,"系统异常"),
	PARAM_ERROR(-2,"参数异常");
	
	private int stateCode;
	private String stateInfo;
	
	OperationEnum(int stateCode,String stateInfo){
		this.stateInfo = stateInfo;
		this.stateCode = stateCode;
	}

	public int getStateCode() {
		return stateCode;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static OperationEnum stateOf(int index){
		for(OperationEnum state: values()){
			if(state.getStateCode() == index){
				return state;
			}
		}
		return null;
	}
}
