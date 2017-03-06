package com.team.business.controller.web;

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
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team.business.dto.TeamResult;
import com.team.business.enums.OperationEnum;
import com.team.business.model.TeamUser;
import com.team.business.service.UserService;

@Controller
@Scope("prototype")
@RequestMapping("/web/user")
public class UserWebController {
	
	@Autowired
	private UserService userOperationService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 用户登录
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "{userName}/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,@PathVariable("userName") String userName,@RequestParam(value="password",required=true) String password){
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userName, password);
		TeamResult<TeamUser> result = null;
		String page = "";
		try {
			subject.login(token);
			TeamUser user = (TeamUser) subject.getSession().getAttribute("user");
			result = new TeamResult<TeamUser>(OperationEnum.LOGIN_SUCCESS.getStateCode(),
					OperationEnum.LOGIN_SUCCESS.getStateInfo(), user); 
			page = "/home";
		} catch (UnknownAccountException e) {
			//用户未注册
			result = new TeamResult<TeamUser>(OperationEnum.LOGIN_NO_USER.getStateCode(),
					OperationEnum.LOGIN_NO_USER.getStateInfo(), null);
			logger.info(e.getMessage(), e);
		}catch (IncorrectCredentialsException e) {
			//用户名密码错误
			result = new TeamResult<TeamUser>(OperationEnum.LOGIN_USERNAME_PASSWORD_ERROR.getStateCode(),
					OperationEnum.LOGIN_USERNAME_PASSWORD_ERROR.getStateInfo(), null); 
			logger.info(e.getMessage(), e);
		}catch (Exception e) {
			//用户名密码错误
			result = new TeamResult<TeamUser>(OperationEnum.SYSTEM_ERROR.getStateCode(),
					OperationEnum.SYSTEM_ERROR.getStateInfo(), null); 
			logger.info(e.getMessage(), e);
		}  
		return new ModelAndView(page,"result",result);
	}
	
	@RequestMapping(value = "{userName}/regist", method = RequestMethod.GET)
	public ModelAndView regist(String identifyCode,TeamUser user){
		
	
		return null;
	}
	/**
	 * 获取登录页
	 * @return
	 */
	@RequestMapping("/login/page")
	public String getLoginPage(){
		//TODO
		
		return "login";
	}
	
	/**
	 * 返回主页
	 * @return
	 */
	@RequestMapping("/home")
	public ModelAndView getHome(){
		//TODO
		return new ModelAndView("/home");
	}
	
	/**
	 * 获取注册页面
	 * @return
	 */
	@RequestMapping("/regist/page")
	public ModelAndView getRegist(){
		
		return new ModelAndView("/regist");
	}
	
	
	
}
