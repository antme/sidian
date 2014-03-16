package com.sidian.dao;

import java.util.List;
import java.util.Map;

import com.sidian.bean.BaseEntity;

public interface IMyBatisDao {

	public int insert(BaseEntity entity);

	public List<Map<String, Object>> listBySql(String sql);
	
	public int countBySql(String sql);
	

}
