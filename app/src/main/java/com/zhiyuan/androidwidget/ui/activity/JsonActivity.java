package com.zhiyuan.androidwidget.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.model.Person;
import com.zhiyuan.androidwidget.utill.http.Parser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class JsonActivity extends BaseActivity {
	@BindView(R.id.json)
	TextView		mJson;
	@BindView(R.id.parser)
	Button			mParser;
	private String	mJsonString	= "[\n" + "    { \"id\":\"1\",\"name\":\"基神\",\"age\":\"18\" },\n" + "    { \"id\":\"2\",\"name\":\"B神\",\"age\":\"18\"  },\n"
			+ "    { \"id\":\"3\",\"name\":\"曹神\",\"age\":\"18\" }\n" + "]";
	
	@Override
	protected void init() {
		mJson.setText(mJsonString);
	}
	
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_json;
	}
	
	@OnClick(R.id.parser)
	public void parser() {
		List<Person> mPerson = new ArrayList<>();
		Parser.ParserMy parserMy = new Parser.ParserMy();
		try {
			mPerson = parserMy.parse(mJsonString);
			String data = "";
			for (Person person : mPerson) {
				data += "***" + person.getId() + person.getName() + person.getAge() + "****";
			}
			mJson.setText(data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
