package com.myproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.entity.po.User;
import com.myproject.entity.po.UserMongo;
import com.myproject.login.dao.UserRepository;
import com.myproject.login.dao.mongodb.UserMongoRepository;
import com.myproject.login.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMongoRepository userMongoRepository;
	
	@Autowired
	private UserService userService;

	@Test
	public void test01() {
		User user = new User();
		user.setUsername("xiazeyu");
		user.setPassword("1234");
		user.setIsExist(true);
		user = userRepository.save(user);
	}

	@Test
	public void test02() {
		User user = userService.queryByUsername("xiazeyu");
		System.out.println(user.getPassword());
	}
	
	@Test
	public void test03() {
		UserMongo user = new UserMongo();
		user.setUsername("xiazeyu");
		user.setPassword("1234");
		user.setIsExist(true);
		userMongoRepository.save(user);
	}
	
	@Test
	public void test04() {
		UserMongo user = userMongoRepository.findByUsername("xiazeyu");
		System.out.println(user.getPassword());
	}

}
