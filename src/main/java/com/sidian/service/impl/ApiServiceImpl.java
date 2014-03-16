package com.sidian.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidian.bean.ListEntity;
import com.sidian.bean.TDefSku;
import com.sidian.bean.TDefStore;
import com.sidian.bean.TSysUser;
import com.sidian.dao.IMyBatisDao;
import com.sidian.exception.ResponseException;
import com.sidian.service.IApiService;
import com.sidian.util.ApiUtil;

@Service(value = "userService")
public class ApiServiceImpl implements IApiService {

	private static Logger logger = LogManager.getLogger(ApiServiceImpl.class);

	@Autowired
	private IMyBatisDao dao;

	public void list() {

		List<Map<String, Object>> results = dao.listBySql("SELECT TOP 1000 [ID],[Store],[StoreName] FROM [sidiandemo].[dbo].[TDefStore]");

		// List<Map<String, Object>> results =
		// dao.listBySql("SELECT TOP 1000 [UserName], [Password] FROM [sidiandemo].[dbo].[TSysUser]");

		System.out.println(results);
	}

	public void login(TSysUser user) {
		String sql = "SELECT COUNT(*) as count FROM [sidiandemo].[dbo].[TSysUser] AS a WHERE a.UserName='" + user.getUserName() + "' and a.Password='" + user.getPassword() + "';";
		int count = dao.countBySql(sql);

		if (count == 0) {
			throw new ResponseException("用户名或则密码错误");
		}

	}
	public List<TDefStore> listStore() {
		List<Map<String, Object>> results = dao.listBySql("SELECT  TOP 50  [ID],[Store],[StoreName] FROM [sidiandemo].[dbo].[TDefStore]");
		return ApiUtil.toJsonList(results, TDefStore.class, null);
	}
	
	public List<TDefSku> listSku(){
		
		List<Map<String, Object>> results = dao.listBySql("SELECT TOP 50 [Sku], [PName] FROM [sidiandemo].[dbo].[TDefSku]");
		return ApiUtil.toJsonList(results, TDefSku.class, null);

	}

	public void checkSku(TDefSku sku) {
		String sql = "SELECT COUNT(*) as count FROM [sidiandemo].[dbo].[TDefSku] AS a WHERE a.Sku='" + sku.getSku() + "';";
		int count = dao.countBySql(sql);
		if (count == 0) {
			throw new ResponseException("条形码不存在");
		}
	}
}
