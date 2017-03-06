package com.team.business.exception;

public class UserExistException extends RuntimeException {
	private Integer code;
	public UserExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public UserExistException(Integer code,String message) {
		super(message);
		this.code = code;
	}
	
	public Integer getCode(){
		return this.code;
	}
	
}
