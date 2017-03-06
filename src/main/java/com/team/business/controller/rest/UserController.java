package com.team.business.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.business.dto.TeamResult;
import com.team.business.enums.OperationEnum;
import com.team.business.exception.IndentiferErrorException;
import com.team.business.exception.IndentiferExpiredException;
import com.team.business.exception.SystemErrorException;
import com.team.business.exception.UserExistException;
import com.team.business.model.TeamUser;
import com.team.business.service.UserService;
import com.team.business.utils.NumberUtils;
import com.team.business.utils.ParamValidator;
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
	private UserService userOperationService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String IDENTIFY_CODE = "indentify_code";
	/**
	 * 用户注册
	 * @param request
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public TeamResult<TeamUser> regist(HttpServletRequest request,@Validated TeamUser user,BindingResult bind,String indentyCode){
		TeamResult<TeamUser> result = new TeamResult<TeamUser>();
		boolean flag = false;
		//参数验证
		if(bind.hasErrors()){
			result.setResultCode(OperationEnum.PARAM_ERROR.getStateCode());
			result.setResultInfo(bind.getAllErrors().get(0).getDefaultMessage());
			return result;
		}
		//验证码
		List<String[]> indentifyList = (List<String[]>) request.getSession().getAttribute(IDENTIFY_CODE);
		//没有获取验证码
		if(indentifyList==null||indentifyList.size()==0||StringUtils.isEmpty(indentyCode))
			return new TeamResult<TeamUser>(OperationEnum.IDENTIFIER_CODE_ERROR);
		//验证码是否有效
		for(String[] strArray:indentifyList){
			if(indentyCode.equals(strArray[1])){
				long now = System.currentTimeMillis();
				long createTime = Long.valueOf(strArray[0]);
				if(user.getUserPhone().equals(Long.valueOf(strArray[2]))){
					flag = true;
					if(now-createTime>1000*60*5){
						return new TeamResult<TeamUser>(OperationEnum.IDENTIFIER_CODE_EXPIRED);
					}
				}else{
					return new TeamResult<TeamUser>(OperationEnum.IDENTIFIER_CODE_ERROR);
				}
			}
		}
		//验证码错误
		if(flag==false)return new TeamResult<TeamUser>(OperationEnum.IDENTIFIER_CODE_ERROR);
		
		try {
			userOperationService.regist(user);
			result = new TeamResult<TeamUser>(OperationEnum.REGIST_SUCCESS.getStateCode(),
					OperationEnum.REGIST_SUCCESS.getStateInfo(), null); 
		}catch (UserExistException e1){
			result = new TeamResult<TeamUser>(e1.getCode(),
					e1.getMessage(), null); 
		}catch (IndentiferExpiredException e2){
			result = new TeamResult<TeamUser>(OperationEnum.IDENTIFIER_CODE_EXPIRED.getStateCode(),
					e2.getMessage(), null);
		}catch (IndentiferErrorException e3){
			result = new TeamResult<TeamUser>(OperationEnum.IDENTIFIER_CODE_ERROR.getStateCode(),
					e3.getMessage(), null);
		}catch (SystemErrorException e) {
			logger.error(e.getMessage(), e);
			result = new TeamResult<TeamUser>(OperationEnum.IDENTIFIER_CODE_ERROR.getStateCode(),
					e.getMessage(), null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
	@RequestMapping(value = "{userPhone}/login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public TeamResult<TeamUser> login(HttpServletRequest request,@PathVariable("userPhone") String userPhone,String password){
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userPhone, password);
		TeamResult<TeamUser> result = null;
		try {
			subject.login(token);
			TeamUser user = (TeamUser) subject.getSession().getAttribute("user");
			result = new TeamResult<TeamUser>(OperationEnum.LOGIN_SUCCESS.getStateCode(),
					OperationEnum.LOGIN_SUCCESS.getStateInfo(), user); 
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
		return result;
	}
	/**
	 * 用户注销
	 * @param request
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return "home";
	}
	/**
	 * 生成4位数字验证码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/indentifyCode", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public TeamResult<String> generatorIndentyCode(HttpServletRequest request,@RequestParam String userPhone) {
		//验证手机号
		if(!ParamValidator.validatePhone(userPhone)){
			TeamResult<String> team = new TeamResult<String>();
			team.setResultCode(-2);
			team.setResultInfo("手机号码格式错误");
			return team;
		}
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<String[]> list = (List<String[]>) session.getAttribute(IDENTIFY_CODE);
		if(list == null){
			list = new ArrayList<String[]>();
		}
		String indentifyCode = NumberUtils.generateRandomNum(4);
		String[] stringArray = {String.valueOf(System.currentTimeMillis()),indentifyCode,userPhone};
		list.add(stringArray);
		session.setAttribute(IDENTIFY_CODE, list);
		//TODO 调用手机服务发送验证码
		
		return new TeamResult<String>(OperationEnum.OPERATION_SUCCESS);
	};
}
