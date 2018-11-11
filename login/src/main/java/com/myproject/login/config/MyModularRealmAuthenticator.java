package com.myproject.login.config;

import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;

public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

	public MyModularRealmAuthenticator() {
		super.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		//super.setAuthenticationStrategy(new AllSuccessfulStrategy());
		//super.setAuthenticationStrategy(new FirstSuccessfulStrategy());
	}

}
