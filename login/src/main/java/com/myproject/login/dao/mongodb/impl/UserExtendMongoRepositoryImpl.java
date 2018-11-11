package com.myproject.login.dao.mongodb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.myproject.entity.po.UserMongo;
import com.myproject.login.dao.mongodb.UserExtendMongoRepository;

public class UserExtendMongoRepositoryImpl implements UserExtendMongoRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public UserMongo findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is("xiazeyu"));
		return mongoTemplate.findOne(query, UserMongo.class);
	}

}
