package com.team.business.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.business.enums.UserOperationEnum;
import com.team.business.exception.IndentiferErrorException;
import com.team.business.exception.IndentiferExpiredException;
import com.team.business.exception.SystemErrorException;
import com.team.business.exception.UserExistException;
import com.team.business.model.User;
import com.team.business.model.dto.TeamResult;
import com.team.business.service.UserOperationService;
/**
 * 包括用户注册、登陆接口
 * @author chengyu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserOperationService userOperationService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 用户注册
	 * @param request
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", headers = {
			"Accept=application/json",
			"Content-Type=application/json;charset=utf-8" })
	public TeamResult<User> regist(HttpServletRequest request,User user){
		TeamResult<User> result = null;
		try {
			userOperationService.regist(user);
			result = new TeamResult<User>(UserOperationEnum.REGIST_SUCCESS.getStateCode(),
					UserOperationEnum.REGIST_SUCCESS.getStateInfo(), null); 
		}catch (UserExistException e1){
			result = new TeamResult<User>(UserOperationEnum.REGIST_USERNAME_USED.getStateCode(),
					e1.getMessage(), null); 
		}catch (IndentiferExpiredException e2){
			result = new TeamResult<User>(UserOperationEnum.IDENTIFIER_CODE_EXPIRED.getStateCode(),
					e2.getMessage(), null);
		}catch (IndentiferErrorException e3){
			result = new TeamResult<User>(UserOperationEnum.IDENTIFIER_CODE_ERROR.getStateCode(),
					e3.getMessage(), null);
		}catch (SystemErrorException e) {
			logger.error(e.getMessage(), e);
			result = new TeamResult<User>(UserOperationEnum.IDENTIFIER_CODE_ERROR.getStateCode(),
					e.getMessage(), null);
		}
		return result;
	}
	/**
	 * 用户登录
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{userName}/login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", headers = {
			"Accept=application/json",
			"Content-Type=application/json;charset=utf-8" })
	public TeamResult<User> login(HttpServletRequest request,@PathVariable("userName") String userName,String password){
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userName, password);
		TeamResult<User> result = null;
		try {
			subject.login(token);
			userOperationService.saveLoginTime(userName);
			User user = (User) subject.getSession().getAttribute("user");
			result = new TeamResult<User>(UserOperationEnum.LOGIN_SUCCESS.getStateCode(),
					UserOperationEnum.LOGIN_SUCCESS.getStateInfo(), user); 
		} catch (UnknownAccountException e) {
			//用户未注册
			result = new TeamResult<User>(UserOperationEnum.LOGIN_NO_USER.getStateCode(),
					UserOperationEnum.LOGIN_NO_USER.getStateInfo(), null);
			logger.info(e.getMessage(), e);
		}catch (IncorrectCredentialsException e) {
			//用户名密码错误
			result = new TeamResult<User>(UserOperationEnum.LOGIN_USERNAME_PASSWORD_ERROR.getStateCode(),
					UserOperationEnum.LOGIN_USERNAME_PASSWORD_ERROR.getStateInfo(), null); 
			logger.info(e.getMessage(), e);
		}catch (Exception e) {
			//用户名密码错误
			result = new TeamResult<User>(UserOperationEnum.SYSTEM_ERROR.getStateCode(),
					UserOperationEnum.SYSTEM_ERROR.getStateInfo(), null); 
			logger.info(e.getMessage(), e);
		}  
		return result;
	}
	/**
	 * 用户注销
	 * @param request
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{userName}/logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", headers = {
			"Accept=application/json",
			"Content-Type=application/json;charset=utf-8" })
	public TeamResult<User> logout(HttpServletRequest request, @PathVariable String userName){
		
		
		return null;
	}
	
	
	
	
	
	
}
