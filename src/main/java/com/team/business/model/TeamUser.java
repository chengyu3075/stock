package com.team.business.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.team.validator.annotation.Password;
import com.team.validator.annotation.Phone;

public class TeamUser implements Serializable {
	private String        userId;        //用户id
	@Length(max=16, min=1,message="{user.name}") 
	private String        userName;      //用户账号
	private String        password;      //用户密码
	@Phone
	private Long          userPhone;     //用户手机  
	private Date          createTime;    //创建时间
	private Date          updateTime;    //更新时间
	
	public String getUserid() {
		return userId;
	}
	
	public void setUserid(String userId) {
		this.userId = userId;
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

	public Long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
