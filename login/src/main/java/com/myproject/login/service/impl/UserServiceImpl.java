package com.myproject.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.myproject.entity.po.User;
//import com.myproject.login.dao.UserRepository;
//import com.myproject.login.dao.mybatis.UserMapper;
import com.myproject.login.service.UserService;

@Service
//@Transactional
public class UserServiceImpl implements UserService{

	/*@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserMapper userDao;
	
	@Override
	public User queryByUsername(String username) {
		//return userRepository.findByUsername(username);
		return userDao.selectByUsername(username);
	}*/

}
