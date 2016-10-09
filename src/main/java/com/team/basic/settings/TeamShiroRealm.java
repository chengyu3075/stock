package com.team.basic.settings;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.team.business.model.User;
import com.team.business.service.UserOperationService;

public class TeamShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserOperationService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		String userName = (String)arg0.getPrincipal();
		User user = userService.login(userName);
		SimpleAuthenticationInfo authortication = null;
		try {
			//用户未注册异常
			if(user == null){
				throw new UnknownAccountException();
			}
			authortication = new SimpleAuthenticationInfo(user.getUserName(),
					user.getPassword(),user.getNickName()==null?"":user.getNickName());
			//保存用户信息
			Subject currentUser = SecurityUtils.getSubject(); 
			Session session = currentUser.getSession();
//			user.setPassword("");
			session.setAttribute("user", user);
		} catch (UnknownAccountException e1) {
			throw e1;
		}
		return authortication;
	}

}
