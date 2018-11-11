package com.myproject.login.dao.mongodb;

import com.myproject.entity.po.UserMongo;

public interface UserExtendMongoRepository {
	
	UserMongo findByUsername(String username);
	
}
