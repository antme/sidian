package com.sidian.bean;

import java.util.Map;

import com.sidian.util.ApiUtil;

public class BaseEntity {

	public String ID;

	public BaseEntity() {

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * 打印类字段信息
	 */
	@Override
	public String toString() {
		return ApiUtil.toJson(this);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {
		return ApiUtil.toMap(this);
	}

}
