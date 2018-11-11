package com.myproject.entity.po;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "t_user")
public class UserMongo implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.springframework.data.annotation.Id
	private ObjectId objectId;

	private String username;

	private String password;

	private Boolean isExist;

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}

}
