package com.zhiyuan.androidwidget.utill.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseJsonParser<T> {
	public abstract T parse(String data) throws JSONException;
	
	public static JSONObject convertJSONObject(String data) throws JSONException {
		if (data == null || data.length() <= 0) {
			throw new JSONException("String is empty, can not conver to JSONObject");
		}
		String jsonString = data.trim();
		return new JSONObject(jsonString);
	}
	
	public static JSONArray convertJSONArray(String data) throws JSONException {
		if (data == null || data.length() <= 0) {
			throw new JSONException("String is empty, can not conver to JSONArray");
		}
		String jsonString = data.trim();
		return new JSONArray(jsonString);
	}
}
