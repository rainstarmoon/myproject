package com.myproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.entity.po.User;
import com.myproject.login.dao.UserRepository;
import com.myproject.login.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Test
	public void test1() {
		User user = new User();
		user.setUsername("xiazeyu");
		user.setPassword("1234");
		user.setIsExist(true);
		user = userRepository.save(user);
	}

	@Test
	public void test2() {
		User user = userService.queryByUsername("xiazeyu");
		System.out.println(user.getPassword());
	}

}
