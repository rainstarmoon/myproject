package com.myproject.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.entity.po.User;
import com.myproject.login.dao.UserRepository;
import com.myproject.login.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public User queryByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
