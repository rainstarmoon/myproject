package com.myproject.login.dao;

import com.myproject.entity.po.User;

public interface UserRepository extends GeneralRepository<User, Long> {
	
	User findByUsername(String username);
	
}
