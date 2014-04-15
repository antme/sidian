package com.ecomm.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.Gson;
import com.sidian.bean.TDefSku;
import com.sidian.bean.TFitting;
import com.sidian.bean.TSysUser;
import com.sidian.service.IApiService;
import com.sidian.service.impl.ApiServiceImpl;
import com.sidian.util.DateUtil;

public class BaseTestCase extends TestCase {
	private static Logger logger = LogManager.getLogger(BaseTestCase.class);

	protected static ApplicationContext ac;

	private IApiService api;

	public BaseTestCase() {

		if (ac == null) {
			ac = new FileSystemXmlApplicationContext("/src/main/WEB-INF/application.xml");
		}

		api = ac.getBean(ApiServiceImpl.class);

	}

	public void testEmpty() throws IOException, InterruptedException {
		
		TSysUser user = new TSysUser();
		user.setStore("BBB005");
		user.setUserName("林映霞");
		user.setPassword("111111");

	    api.login(user);
		
//		System.out.println(api.listStore());
	    
	    TDefSku sku = new TDefSku();
	    sku.setSku("14131467038");
	    sku.setStore("ZSBBB1");
	
	    System.out.println(api.checkSku(sku));
	    
	    List<TFitting> fittings = new ArrayList<TFitting>();
	    for(int i=0; i<2; i++){
	    	TFitting fitting = new TFitting();
	    	fitting.setFittingCode("test" + i);
	    	fitting.setCustomerCode("00" + i);
	    	fitting.setUserName("test" + i);
	    	fitting.setFittingDate(DateUtil.getDateString(new Date()));
	    	fitting.setFittingTime("20:32:52");
	    	fitting.setSku("00000001");
	    	fitting.setStore("00" + i);
	    	fittings.add(fitting);
	    	
	    }
	    
//	    
//
//	    System.out.println(api.addFittings(fittings));
//	    
//	    api.deleteFittings(fittings);
//	
	}

}
