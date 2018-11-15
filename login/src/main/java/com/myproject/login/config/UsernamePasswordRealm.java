package com.myproject.login.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.myproject.entity.po.User;
import com.myproject.login.service.UserService;

public class UsernamePasswordRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		FaceImageUsernamePasswordToken faceImageUsernamePasswordToken = (FaceImageUsernamePasswordToken) token;
		String principal = (String) faceImageUsernamePasswordToken.getPrincipal();
		char[] credentials = (char[]) faceImageUsernamePasswordToken.getCredentials();
		
		if(principal==null) {
			return null;
		}
		//User user = userService.queryByUsername(principal.toString());
		//if(!user.getPassword().equals(String.valueOf(credentials))) {
		//	return null;
		//}
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials,
				this.getName());
		return authenticationInfo;
	}

}
