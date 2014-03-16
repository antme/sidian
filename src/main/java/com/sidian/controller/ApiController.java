package com.sidian.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sidian.bean.TDefSku;
import com.sidian.bean.TFitting;
import com.sidian.bean.TSysUser;
import com.sidian.exception.ResponseException;
import com.sidian.service.IApiService;
import com.sidian.util.ApiUtil;

@Controller
@RequestMapping("/api")
public class ApiController extends AbstractController {

	@Autowired
	private IApiService apiService;

	private static Logger logger = LogManager.getLogger(ApiController.class);

	@RequestMapping("/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		TSysUser user = (TSysUser) parserParametersToEntity(request, false, TSysUser.class);

		apiService.login(user);
		responseWithData(null, request, response);

	}

	@RequestMapping("/listStore.do")
	public void listStore(HttpServletRequest request, HttpServletResponse response) {

		responseWithListDataForApp(apiService.listStore(), request, response);

	}
	
	@RequestMapping("/listSku.do")
	public void listSku(HttpServletRequest request, HttpServletResponse response) {

		responseWithListDataForApp(apiService.listSku(), request, response);

	}

	@RequestMapping("/sku/check.do")
	public void checkSku(HttpServletRequest request, HttpServletResponse response) {

		TDefSku sku = (TDefSku) parserParametersToEntity(request, false, TDefSku.class);

		if (ApiUtil.isEmpty(sku.getSku())) {
			throw new ResponseException("查询的条形码不能为空");
		}

		apiService.checkSku(sku);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("checkedDateTime", new Date().getTime());
		responseWithData(result, request, response);

	}
	
	@RequestMapping("/fittings/add.do")
	public void addFittings(HttpServletRequest request, HttpServletResponse response) {

		List<TFitting> fittings =  parserListJsonParameters(request, false, TFitting.class, "list");

		apiService.addFittings(fittings);

		responseWithData(null, request, response);

	}

}
