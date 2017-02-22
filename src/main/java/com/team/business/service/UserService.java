package com.team.business.service;

import com.team.business.exception.IndentiferErrorException;
import com.team.business.exception.IndentiferExpiredException;
import com.team.business.exception.SystemErrorException;
import com.team.business.exception.UserExistException;
import com.team.business.model.TeamUser;

public interface UserService {
	/**
	 * 用户登录
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	TeamUser login(Long cellPhone);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int regist(TeamUser user) throws UserExistException,SystemErrorException,Exception;
}
