package com.sidian.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sidian.annotation.FloatColumn;
import com.sidian.annotation.IntegerColumn;
import com.sidian.bean.BaseEntity;
import com.sidian.exception.ResponseException;

public class ApiUtil {

	private static Logger logger = LogManager.getLogger(ApiUtil.class);

	public static boolean isEmpty(Object param) {

		if (param == null) {
			return true;
		}

		if (param instanceof Map) {
			return ((Map) param).isEmpty();
		}
		if (param instanceof List) {
			return ((List) param).isEmpty();
		}
		String parameter = param.toString();
		if (parameter.trim().length() == 0) {
			return true;
		}

		if ("null".equalsIgnoreCase(parameter)) {
			return true;
		}

		return false;
	}

	public static Integer getInteger(Object value, int defaultValue, boolean log) {
		Integer result = null;

		if (isEmpty(value)) {
			result = defaultValue;
		} else {
			try {
				result = (int) Float.parseFloat(String.valueOf(value));
			} catch (NumberFormatException e) {
				try {
					result = Integer.parseInt(String.valueOf(value));
				} catch (NumberFormatException e1) {

					if (log) {
						logger.error(String.format("Integer parameter illegal [%s]", value));
					}
				}
			}

		}
		if (result == null)
			result = defaultValue;
		return result;
	}

	public static Float getFloat(Object value, Float defaultValue) {
		Float result = null;

		if (isEmpty(value)) {
			result = defaultValue;
		} else {
			try {
				result = Float.parseFloat(String.valueOf(value));
			} catch (NumberFormatException e) {

				logger.error(String.format("Integer parameter illegal [%s]", value), e);
				throw new ResponseException("ILEGAL_PARAMTERS");

			}

		}
		if (result == null)
			result = defaultValue;
		return result;
	}

	public static Double getDouble(Object value, Double defaultValue) {
		Double result = null;

		if (isEmpty(value)) {
			result = defaultValue;
		} else {
			try {
				result = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {

				logger.error(String.format("Integer parameter illegal [%s]", value), e);
				throw new ResponseException("ILEGAL_PARAMTERS");

			}

		}
		if (result == null)
			result = defaultValue;
		return result;
	}

	public static String join(String[] array) {
		return join(array, ",");
	}

	public static String join(List<String> array) {
		return join((String[]) array.toArray(), ",");
	}

	public static String join(Set<String> array) {
		return join((String[]) array.toArray(), ",");
	}

	/**
	 * Join all the elements of a string array into a single String.
	 * 
	 * If the given array empty an empty string will be returned. Null elements
	 * of the array are allowed and will be treated like empty Strings.
	 * 
	 * @param array
	 *            Array to be joined into a string.
	 * @param delimiter
	 *            String to place between array elements.
	 * @return Concatenation of all the elements of the given array with the the
	 *         delimiter in between.
	 * @throws NullPointerException
	 *             if array or delimiter is null.
	 * 
	 * @since ostermillerutils 1.05.00
	 */
	public static String join(String[] array, String delimiter) {
		// Cache the length of the delimiter
		// has the side effect of throwing a NullPointerException if
		// the delimiter is null.
		int delimiterLength = delimiter.length();
		// Nothing in the array return empty string
		// has the side effect of throwing a NullPointerException if
		// the array is null.
		if (array.length == 0)
			return "";
		// Only one thing in the array, return it.
		if (array.length == 1) {
			if (array[0] == null)
				return "";
			return array[0];
		}
		// Make a pass through and determine the size
		// of the resulting string.
		int length = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				length += array[i].length();
			if (i < array.length - 1)
				length += delimiterLength;
		}
		// Make a second pass through and concatenate everything
		// into a string buffer.
		StringBuffer result = new StringBuffer(length);
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				result.append(array[i]);
			if (i < array.length - 1)
				result.append(delimiter);
		}
		return result.toString();
	}

	static JsonSerializer<Date> ser = new JsonSerializer<Date>() {
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return src == null ? null : new JsonPrimitive(DateUtil.getDateStringByLong(src.getTime()));
		}
	};

	static JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			return DateUtil.getDateTime(json.getAsString());
		}
	};

	public static <T extends BaseEntity> BaseEntity toEntity(Map<String, Object> data, Class<T> classzz) {
		String json = new GsonBuilder().registerTypeAdapter(Date.class, ser).setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(data);
		return new GsonBuilder().registerTypeAdapter(Date.class, deser).create().fromJson(json, classzz);

	}

	public static <T extends BaseEntity> BaseEntity toEntity(String data, Class<T> classzz) {
		return new GsonBuilder().registerTypeAdapter(Date.class, deser).create().fromJson(data, classzz);

	}

	public static <T extends BaseEntity> List<T> toJsonList(Object params, Class<T> clz, String key) {

		List<T> results = new ArrayList<T>();

		if (params instanceof Map) {
			Map<String, Object> result = (Map<String, Object>) params;
			if (!ApiUtil.isEmpty(result.get(key))) {

				if (result.get(key) instanceof List) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) result.get(key);

					for (Map<String, Object> obj : list) {
						updateJsonFieldWithType(obj, clz);
						results.add((T) ApiUtil.toEntity(obj, clz));
					}
				} else if (result.get(key) instanceof String) {

					List<Map<String, Object>> listMap = (List<Map<String, Object>>) new GsonBuilder().registerTypeAdapter(Date.class, deser).create()
					        .fromJson(result.get(key).toString(), List.class);
					for (Map<String, Object> map : listMap) {
						results.add((T) ApiUtil.toEntity(map, clz));
					}
				}

			}
		} else if (params instanceof List) {

			List<Map<String, Object>> dataList = (List<Map<String, Object>>) params;

			for (Map<String, Object> obj : dataList) {
				updateJsonFieldWithType(obj, clz);
				results.add((T) ApiUtil.toEntity(obj, clz));
			}
		}
		return results;

	}

	public static String toJson(BaseEntity entity) {
		return new GsonBuilder().registerTypeAdapter(Date.class, ser).create().toJson(entity);
	}

	public static String toJson(Object data) {
		return new GsonBuilder().registerTypeAdapter(Date.class, ser).create().toJson(data);
	}

	public static Map<String, Object> toMap(BaseEntity entity) {
		return new Gson().fromJson(entity.toString(), HashMap.class);
	}

	public static Map<String, Object> toMap(String jsonStr) {
		return new Gson().fromJson(jsonStr, HashMap.class);
	}

	public static String toString(Map<String, Object> data) {
		return new Gson().toJson(data);
	}

	public static <T extends BaseEntity> void updateJsonFieldWithType(Map<String, Object> params, Class<T> clz) {
		Field[] fields = clz.getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(IntegerColumn.class)) {
				if (params.get(field.getName()) != null) {
					params.put(field.getName(), getInteger(params.get(field.getName()), 0, true));
				}
			} else if (field.isAnnotationPresent(FloatColumn.class)) {
				if (params.get(field.getName()) != null) {
					params.put(field.getName(), getFloat(params.get(field.getName()), 0.0f));
				}
			}
		}

	}

	public static boolean isValid(Object param) {
		return !isEmpty(param);
	}

	/** 判断字符串是否为数字和字母组成 */
	public static boolean isCharNum(String str) {
		if (str != null) {
			return str.matches("^[A-Za-z0-9]+$");
		}
		return false;
	}

}
