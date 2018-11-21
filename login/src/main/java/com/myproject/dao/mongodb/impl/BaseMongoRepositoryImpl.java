//package com.myproject.dao.mongodb.impl;
//
//import java.io.Serializable;
//
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
//import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
//
//import com.myproject.dao.mongodb.BaseMongoRepository;
//
//public class BaseMongoRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID>
//		implements BaseMongoRepository<T, ID> {
//
//	protected final MongoOperations mongoTemplate;
//
//	protected final MongoEntityInformation<T, ID> entityInformation;
//
//	private Class<T> clazz;
//
//	public BaseMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
//		super(metadata, mongoOperations);
//		this.mongoTemplate = mongoOperations;
//		this.entityInformation = metadata;
//		clazz = entityInformation.getJavaType();
//	}
//
//}
