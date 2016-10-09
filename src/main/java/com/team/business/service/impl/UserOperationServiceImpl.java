package com.team.business.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.team.business.dao.GroupDao;
import com.team.business.dao.UserDao;
import com.team.business.enums.UserOperationEnum;
import com.team.business.exception.IndentiferErrorException;
import com.team.business.exception.IndentiferExpiredException;
import com.team.business.exception.SystemErrorException;
import com.team.business.exception.UserExistException;
import com.team.business.model.User;
import com.team.business.service.UserOperationService;
@Service
public class UserOperationServiceImpl implements UserOperationService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDao    userDao;
	@Autowired
	private GroupDao   groupDao; 
	
	@Cacheable(value="myCache", key="#userName")
	public User login(String userName){
		try {
			System.out.println("username is:"+userName);
			return userDao.getUser(userName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int regist(User user) throws UserExistException,IndentiferExpiredException,IndentiferErrorException,SystemErrorException{
		int result = 0;
		String indentifer = "";
		try {
			//用户已被注册
			if(userDao.getUser(user.getUserName()) !=null){
				throw new UserExistException(UserOperationEnum.REGIST_USERNAME_USED.getStateInfo());
			}
			//验证码错误
			if(!checkIdentiferError(indentifer)){
				throw new IndentiferExpiredException(UserOperationEnum.IDENTIFIER_CODE_ERROR.getStateInfo());
			}
			//验证码过期
			if(!checkIdentiferExpired(indentifer)){
				throw new IndentiferErrorException(UserOperationEnum.IDENTIFIER_CODE_EXPIRED.getStateInfo());
			}
			user.setRegTime(new Date());
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			userDao.saveUser(user);
		}catch (UserExistException e1){
			throw e1;
		}catch (IndentiferExpiredException e2){
			throw e2;
		}catch (IndentiferErrorException e3){
			throw e3;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SystemErrorException(UserOperationEnum.SYSTEM_ERROR.getStateInfo());
		}
		return result;
	}
	
	public void saveLoginTime(String userName) throws SystemErrorException{
		try {
			userDao.saveLoginTime(userName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SystemErrorException(e.getMessage());
		}
	}

	/**
	 * 验证验证码是否正确或者超时
	 * @param validateNum
	 * @return
	 */
	private Boolean checkIdentiferExpired(String identifer){
		
		return true;
	}
	/**
	 * 验证码是否正确
	 * @param identifer
	 * @return
	 */
	private Boolean checkIdentiferError(String identifer){
		
		return true;
	}
	
	
}
