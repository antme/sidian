package com.ecomm.dao;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sidian.bean.TSysUser;
import com.sidian.service.IApiService;
import com.sidian.service.impl.ApiServiceImpl;

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
		System.out.println(api.listStore());
	
	}

}
