package com.sidian.bean;

import java.util.List;

public class TDefStore extends BaseEntity {

	//编号
	private String Store;
	
	//名字
	private String StoreName;
	
	private List<String> account;
	

	public String getStore() {
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	public List<String> getAccount() {
    	return account;
    }

	public void setAccount(List<String> account) {
    	this.account = account;
    }
	
	

}
