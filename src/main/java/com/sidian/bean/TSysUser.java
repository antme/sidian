package com.sidian.bean;

public class TSysUser extends BaseEntity {

	
	private String UserName;
	
	private String Password;
	
	private String Store;
	
	private String UserId;
	
	public String getUserName() {
    	return UserName;
    }

	public void setUserName(String userName) {
    	UserName = userName;
    }

	public String getPassword() {
    	return Password;
    }

	public void setPassword(String password) {
    	Password = password;
    }

	public String getStore() {
    	return Store;
    }

	public void setStore(String store) {
    	Store = store;
    }

	public String getUserId() {
    	return UserId;
    }

	public void setUserId(String userId) {
    	UserId = userId;
    }
	
	
	
}
