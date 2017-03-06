package com.team.business.dao;

import java.util.Date;

import com.team.business.model.TeamUser;

public interface UserDao {
	/**
	 * 获取用户密码，用于登陆
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	TeamUser queryUserByPhone(Long userPhone) throws Exception;
	/**
	 * 根据用户名取得用户
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	TeamUser queryUserByUserName(String userName) throws Exception;
	/**
	 * 保存用户详细信息
	 * @param user
	 * @param password
	 * @return
	 * @throws Exception
	 */
	int saveUser(TeamUser user) throws Exception;
	/**
	 * 保存用户登录时间
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	int saveLoginTime(String userName) throws Exception;
}
