package com.sidian.dao;

import java.util.List;
import java.util.Map;

public interface IMyBatisDao {

	public int insert(String sql);

	public List<Map<String, Object>> listBySql(String sql);
	
	public int countBySql(String sql);
	
	public void update(String sql);
	
	public int delete(String sql);
}
