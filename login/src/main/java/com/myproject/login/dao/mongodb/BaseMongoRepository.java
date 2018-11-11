package com.myproject.login.dao.mongodb;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseMongoRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
}
