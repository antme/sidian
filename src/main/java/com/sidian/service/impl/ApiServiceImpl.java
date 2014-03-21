package com.sidian.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidian.bean.TDefSku;
import com.sidian.bean.TDefStore;
import com.sidian.bean.TFitting;
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

	public TSysUser login(TSysUser user) {
		String sql = "SELECT [UserId],[Store],[UserName] FROM [sidiandemo].[dbo].[试穿用户表] AS a WHERE a.UserName='" + user.getUserName() + "' and a.Password='" + user.getPassword()
		        + "' and a.Store='" + user.getStore() + "';";

		List<Map<String, Object>> results = dao.listBySql(sql);
		if (results.size() == 0) {
			throw new ResponseException("用户名或则密码错误");
		}
		return (TSysUser) ApiUtil.toEntity(results.get(0), TSysUser.class);

	}
	public List<TDefStore> listStore() {
		List<Map<String, Object>> results = dao.listBySql("SELECT [ID],[Store],[StoreName] FROM [sidiandemo].[dbo].[TDefStore]");
		return ApiUtil.toJsonList(results, TDefStore.class, null);
	}
	
	public List<TDefSku> listSku(){
		
		List<Map<String, Object>> results = dao.listBySql("SELECT [Sku], [PName] FROM [sidiandemo].[dbo].[TDefSku]");
		return ApiUtil.toJsonList(results, TDefSku.class, null);

	}

	public TDefSku checkSku(TDefSku sku) {

		String sql = "SELECT a.Sku, a.PName, b.Style, b.StyleName, b.Attrib22 as isStarProduct, b.Attrib37 as remark FROM [sidiandemo].[dbo].[TDefSku] AS a left join  [sidiandemo].[dbo].[TDefStyle] AS b ON b.Style=a.Style WHERE a.Sku='" + sku.getSku() + "';";
		List<Map<String, Object>> results = dao.listBySql(sql);
		
		
		if(results.isEmpty()){
			throw new ResponseException("条形码不存在");
		}

		return (TDefSku) ApiUtil.toEntity(results.get(0), TDefSku.class);
	
	}
	
	public int addFittings(List<TFitting> fittings){
		String sql = "INSERT INTO [sidiandemo].[dbo].[试穿数据表](试穿编号,Store,试穿日期,试穿时间,客人编号,年龄,条码,是否成交,意见,选项1价格,选项2颜色,选项3尺码,选项4搭配,选项5款式,选项6其它,UserName,是否推荐) VALUES ";
		
		if (ApiUtil.isEmpty(fittings)) {
			throw new ResponseException("试衣记录不能为空");
		}

		int i = 0;
		String items = "";
		for(TFitting fitting: fittings){
			
			String item = "('" + fitting.getFittingCode() + "'," 
					+  "'" + fitting.getStore() + "'," 
					+  "'" + fitting.getFittingDate() + "',"
					+  "'" + fitting.getFittingTime() + "',"
					+  "'" + fitting.getCustomerCode() + "',"
					+ fitting.getCustomerAge() + ","
					+  "'" + fitting.getSku() + "',"
					+ fitting.getIsSaled() + ","
					+ "'"  + fitting.getFeedBack() + "',"
					+ fitting.getIsPriceOk() + ","
					+ fitting.getIsColorOk() + ","
					+ fitting.getIsSizeOk() + ","
					+ fitting.getIsSuitable() + ","
					+ fitting.getIsStyleOk() + ","
					+ fitting.getIsOtherOk() + ","
					+  "'" + fitting.getUserName() + "',"
					+ fitting.getIsRecommend() + ")"; 
			
			i++;
			if (i == fittings.size()) {
				items = items + item + ";";
			} else {
				items = items + item + ",";
			}
		}
		
		sql = sql + items;
		
		System.out.println(sql);
		return this.dao.insert(sql);
	}
}
