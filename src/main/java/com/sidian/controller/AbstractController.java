package com.sidian.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sidian.bean.BaseEntity;
import com.sidian.exception.ResponseException;
import com.sidian.util.ApiUtil;

public abstract class AbstractController {
	private static Logger logger = LogManager.getLogger(AbstractController.class);

	protected <T extends BaseEntity> BaseEntity parserParametersToEntity(HttpServletRequest request, boolean emptyParameter, Class<T> claszz) {
		HashMap<String, Object> parametersMap = parserJsonParameters(request, emptyParameter);
		return ApiUtil.toEntity(parametersMap, claszz);

	}

	protected <T extends BaseEntity> List<T> parserListJsonParameters(HttpServletRequest request, boolean emptyParameter, Class<T> claszz, String key) {
		Map<String, Object> params = this.parserJsonParameters(request, false);
		List<T> list = ApiUtil.toJsonList(params, claszz, key);

		return list;
	}


	private HashMap<String, Object> parserJsonParameters(HttpServletRequest request, boolean emptyParameter) {
		HashMap<String, Object> parametersMap = new HashMap<String, Object>();

		Enumeration<?> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String pName = parameterNames.nextElement().toString();
			parametersMap.put(pName, request.getParameter(pName).trim());
		}
		if (!emptyParameter && (parametersMap == null || parametersMap.isEmpty())) {
			throw new ResponseException("参数不能为空");
		}
		return parametersMap;
	}

	protected void responseWithData(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
		responseMsg(data, 1, request, response, null);
	}

	protected void responseWithData(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response, String msgKey) {
		responseMsg(data, 1, request, response, msgKey);
	}


	protected <T extends BaseEntity> void responseWithListDataForApp(List<T> listBean, HttpServletRequest request, HttpServletResponse response) {
		if (listBean != null) {
			Map<String, Object> list = new HashMap<String, Object>();
			list.put("data", listBean);
			responseMsg(list, 1, request, response, null);
		} else {
			responseMsg(null, 1, request, response, null);
		}
	}

	/**
	 * This function will return JSON data to Client
	 * 
	 * 
	 * @param data
	 *            data to return to client
	 * @param dataKey
	 *            if set dataKey, the JSON format use dataKey as the JSON key,
	 *            data as it's value, and both the dataKey and "status" key are
	 *            child of the JSON root node. If not set dataKey, the data and
	 *            the "status" node are both the child of the JSON root node
	 * @param status
	 *            0:FAIL, 1: SUCCESS
	 * @return
	 */
	private void responseMsg(Map<String, Object> data, int status, HttpServletRequest request, HttpServletResponse response, String msgKey) {

		if (data == null) {
			data = new HashMap<String, Object>();
		}
		
		if(data.get("data") == null){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", data);
			data = result;
		}
		data.put("code", status);

		response.setContentType("text/plain;charset=UTF-8");
		response.addHeader("Accept-Encoding", "gzip, deflate");

		String jsonReturn = new Gson().toJson(data);
		try {
			response.getWriter().write(jsonReturn);

		} catch (IOException e) {
			logger.fatal("Write response data to client failed!", e);
		}

	}

	protected void responseServerError(Throwable throwable, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> temp = new HashMap<String, Object>();

		int code = -1;
		if (throwable instanceof ResponseException) {
			ResponseException apiException = (ResponseException) throwable;
			temp.put("msg", apiException.getMessage());
			code = 0;
		} else {
			temp.put("msg", "获取数据失败");
		}
		responseMsg(temp, code, request, response, null);

	}

}
