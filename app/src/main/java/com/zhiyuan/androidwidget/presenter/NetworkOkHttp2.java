package com.zhiyuan.androidwidget.presenter;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by stefan on 2017/5/23.
 */

public class NetworkOkHttp2 implements INetwork {
	// 创建OkHttpClient对象
	private final String	TAG				= getClass().getSimpleName();
	private OkHttpClient	mOkHttpClient	= new OkHttpClient();
	private final String	URL				= "http://www.runoob.com/";
	private Context			mContext;
	
	public NetworkOkHttp2(Context mContext) {
		this.mContext = mContext;
		mOkHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
		mOkHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
		mOkHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
	}
	
	@Override
	public void get() {
		// 异步请求get
		getAsync();
		// 同步请求get
		// getSync();
	}
	
	@Override
	public void post() {
		RequestBody requestBody = new FormEncodingBuilder().add("size", "10").build();
		Request request = new Request.Builder().url("http://api.1-blog.com/biz/bizserver/article/list.do").post(requestBody).build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				
			}
			
			@Override
			public void onResponse(Response response) throws IOException {
				String str = response.body().string();
				Log.i("wangshu", str);
			}
		});
		
	}
	
	private void getAsync() {
		// File sdcache = mContext.getExternalCacheDir();
		// int cacheSize = 10 * 1024 * 1024;
		// mOkHttpClient.setCache(new Cache(sdcache.getAbsoluteFile(),
		// cacheSize));
		Request request = new Request.Builder().url(URL).build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				
			}
			
			@Override
			public void onResponse(Response response) throws IOException {
				String str = response.body().toString();
				String str2 = response.toString();
				Log.i(TAG, str);
				Log.i(TAG, str2);
			}
		});
	}
	private void getSync() {
		Request request = new Request.Builder().url(URL).build();
		Call call = mOkHttpClient.newCall(request);
		try {
			Response response = call.execute();
			if (response.isSuccessful()) {
				String str = response.body().toString();
				Log.i(TAG, str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
