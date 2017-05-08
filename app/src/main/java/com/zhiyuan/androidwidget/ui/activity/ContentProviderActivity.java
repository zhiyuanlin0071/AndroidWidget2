package com.zhiyuan.androidwidget.ui.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentProviderActivity extends BaseActivity {
	
	@BindView(R.id.read_sms)
	Button		mReadSms;
	@BindView(R.id.insert_sms)
	Button		mInsertSms;
	@BindView(R.id.text_sms)
	TextView	mTextSms;
	
	@Override
	protected void init() {
		
	}
	
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_content_provider;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
	
	public void getMsg() {
		Uri uri = Uri.parse("content://sms/");
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
		while (cursor.moveToNext()) {
			String address = cursor.getString(0);
			String date = cursor.getString(1);
			String type = cursor.getString(2);
			String body = cursor.getString(3);
			Toast.makeText(this, "地址:" + address, Toast.LENGTH_SHORT).show();
			System.out.println("地址:" + address);
			System.out.println("时间:" + date);
			System.out.println("类型:" + type);
			System.out.println("内容:" + body);
			System.out.println("======================");
		}
		cursor.close();
	}
	public void insetMsg() {
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		ContentValues conValues = new ContentValues();
		conValues.put("address", "123456789");
		conValues.put("type", 1);
		conValues.put("date", System.currentTimeMillis());
		conValues.put("body", "no zuo no die why you try!");
		resolver.insert(uri, conValues);
		Log.e("HeHe", "短信插入完毕~");
	}
	@OnClick(R.id.read_sms)
	public void readSms() {
		getMsg();
	}
	@OnClick(R.id.insert_sms)
	public void insertSms() {
		insetMsg();
	}
	
}
