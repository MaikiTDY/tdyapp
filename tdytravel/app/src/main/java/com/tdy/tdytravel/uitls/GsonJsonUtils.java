package com.tdy.tdytravel.uitls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangdayi on 2017/5/7.
 * 作者:tangdayi
 * 日期:2017年05月07日15时00分
 * 文件:OrderListActivity.java
 * 工程:tdytravel
 *
 * 网络数据格式转换
 */
public class GsonJsonUtils {
	public static Map<String, Object> parseJson2Map(String json) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().create();
		JsonReader reader = new JsonReader(new StringReader(json));
		map = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}

	public static String parseObj2Json(Object object) throws Exception {
		if (object == null) {
			return "";
		}
		Gson g = new GsonBuilder().create();
		String json = g.toJson(object, object.getClass());
		return json;
	}

	/**
	 * JSON转成指定对象
	 * @param json
	 */
	public static <T> T parseJson2Obj(String json, TypeToken<T> typeToken) throws Exception {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader(json));
		return gson.fromJson(reader, typeToken.getType());
	}
}
