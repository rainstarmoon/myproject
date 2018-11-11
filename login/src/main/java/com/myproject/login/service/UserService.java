package com.myproject.login.service;

import com.myproject.entity.po.User;

public interface UserService {
	
	User queryByUsername(String username);
	
}
