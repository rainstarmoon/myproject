package com.myproject.config;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Configuration
public class ShiroConfiguration {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/loginSuccess");
		Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
		filterChainDefinitionMap.put("/01js/**", "anon");
		// 少进行一次校验
		filterChainDefinitionMap.put("/login", "anon");
		// filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setAuthenticator(modularRealmAuthenticator());
		securityManager.setRealms(allRealms());
		return securityManager;
	}

	@Bean
	public ModularRealmAuthenticator modularRealmAuthenticator() {
		ModularRealmAuthenticator modularRealmAuthenticator = new MyModularRealmAuthenticator();
		// modularRealmAuthenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
		return modularRealmAuthenticator;
	}

	// 将用户名密码的验证方式加入容器
	@Bean
	public UsernamePasswordRealm usernamePasswordRealm() {
		UsernamePasswordRealm usernamePasswordRealm = new UsernamePasswordRealm();
		return usernamePasswordRealm;
	}

	// 将人脸识别的验证方式加入容器
	@Bean
	public FaceRealm faceRealm() {
		FaceRealm faceRealm = new FaceRealm();
		return faceRealm;
	}

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	// 获取所有realm
	private List<Realm> allRealms() {
		return Lists.newArrayList(faceRealm(), usernamePasswordRealm());
	}

}
