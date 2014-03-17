package com.ecomm.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
		user.setStore("BBB003");
		user.setUserName("徐敏");
		user.setPassword("123456");

//	    api.login(user);
	    
	    TDefSku sku = new TDefSku();
	    sku.setSku("01109101037838");
//	    api.checkSku(sku);
	    
	    List<TFitting> fittings = new ArrayList<TFitting>();
	    for(int i=0; i<2; i++){
	    	TFitting fitting = new TFitting();
	    	fitting.setCustomerCode("00" + i);
	    	fitting.setUserName("test");
	    	fitting.setFittingDate(DateUtil.getDateString(new Date()));
	    	fitting.setFittingTime("20:32:52");
	    	fitting.setSku("00000001");
	    	fitting.setStore("00" + i);
	    	fittings.add(fitting);
	    	
	    }
	    System.out.println(api.addFittings(fittings));
	
	}

}
