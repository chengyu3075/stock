package com.team.business.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.team.business.dao.GroupDao;
import com.team.business.dao.UserDao;
import com.team.business.enums.OperationEnum;
import com.team.business.exception.IndentiferErrorException;
import com.team.business.exception.IndentiferExpiredException;
import com.team.business.exception.SystemErrorException;
import com.team.business.exception.UserExistException;
import com.team.business.model.TeamUser;
import com.team.business.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDao    userDao;
	@Autowired
	private GroupDao   groupDao; 
	
	@Cacheable(value="myCache", key="#userName")
	@CacheEvict
	public TeamUser login(Long cellPhone){
		try {
			return userDao.queryUserByPhone(cellPhone);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int regist(TeamUser user) throws UserExistException,SystemErrorException,Exception{
		int result = 0;
		//用户已被注册
		if(userDao.queryUserByPhone(user.getUserPhone()) !=null){
			throw new UserExistException(15,OperationEnum.REGIST_PHONE_USED.getStateInfo());
		}
		//用户名已被占用
		if(userDao.queryUserByUserName(user.getUserName()) !=null){
			throw new UserExistException(11,OperationEnum.REGIST_USERNAME_USED.getStateInfo());
		}
		//验证码错误
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		result = userDao.saveUser(user);
		
		return result;
	}

	
		
}
