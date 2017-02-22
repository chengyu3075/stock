package com.team.business.dto;

import java.io.Serializable;

import com.team.business.enums.OperationEnum;

public class TeamResult<T> implements Serializable {

	private int resultCode;      //结果编码
	private String resultInfo;   //结果信息
	private T      data;         //结果
	
	public TeamResult(int resultCode, String resultInfo, T data) {
		super();
		this.resultCode = resultCode;
		this.resultInfo = resultInfo;
		this.data = data;
	}
	
	public TeamResult(){
		
	}
	
	public TeamResult(OperationEnum operationInfo){
		this.resultCode = operationInfo.getStateCode();
		this.resultInfo = operationInfo.getStateInfo();
	}
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
