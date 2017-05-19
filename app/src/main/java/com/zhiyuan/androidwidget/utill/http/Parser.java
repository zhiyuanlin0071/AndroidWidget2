package com.zhiyuan.androidwidget.utill.http;

import com.zhiyuan.androidwidget.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefan on 2017/5/8.
 */

public class Parser {
	
	public static class ParserMy extends BaseJsonParser<List<Person>> {
		List<Person> mPersons = new ArrayList<>();
		@Override
		public List<Person> parse(String data) throws JSONException {
			JSONArray jsonArray = convertJSONArray(data);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Person person = new Person();
				person.setId(jsonObject.getString("id"));
				person.setAge(jsonObject.getString("age"));
				person.setName(jsonObject.getString("name"));
				mPersons.add(person);
			}
			return mPersons;
		}
	}
}
