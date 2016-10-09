package com.team.business.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private String    userid;        //用户id
	private String    userName;      //用户账号
	private String    password;       //用户密码
	private String    nickName;      //用户姓名、昵称
	private Integer   sex;           //性别
	private String    email;         //电子邮件
	private String    portrait;      //头像
	private Date      regTime;       //注册时间
	private Date      lastLoginTime; //最后登陆时间
	private Date      address;       //地址
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getAddress() {
		return address;
	}
	public void setAddress(Date address) {
		this.address = address;
	}
}
