package com.myproject.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UsernamePasswordRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		FaceImageUsernamePasswordToken faceImageUsernamePasswordToken = (FaceImageUsernamePasswordToken) token;
		Object principal = faceImageUsernamePasswordToken.getPrincipal();
		Object credentials = faceImageUsernamePasswordToken.getCredentials();
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials,
				this.getName());
		return authenticationInfo;
	}

}
