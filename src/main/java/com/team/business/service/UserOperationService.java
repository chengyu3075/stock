package com.team.business.service;

import com.team.business.exception.SystemErrorException;
import com.team.business.model.User;

public interface UserOperationService {
	/**
	 * 用户登录
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	User login(String userName);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int regist(User user);
	/**
	 * 用户登录成功后记录登录时间
	 * @param userName
	 */
	void saveLoginTime(String userName)  throws SystemErrorException;
}
