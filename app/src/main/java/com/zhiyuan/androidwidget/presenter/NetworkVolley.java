package com.zhiyuan.androidwidget.presenter;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by stefan on 2017/5/23.
 */

public class NetworkVolley implements INetwork {
	private final String	TAG	= getClass().getSimpleName();
	private RequestQueue	requestQueue;
	
	public NetworkVolley(RequestQueue requestQueue) {
		this.requestQueue = requestQueue;
	}
	
	@Override
	public void get() {
		StringRequest request = new StringRequest("http://www.runoob.com/", new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				Log.d(TAG, s);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.d(TAG, volleyError.getMessage(), volleyError);
			}
		});
		requestQueue.add(request);
	}
	
	@Override
	public void post() {
		StringRequest request = new StringRequest(Request.Method.POST, "http://www.runoob.com/", new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				Log.d(TAG, s);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.d(TAG, volleyError.getMessage(), volleyError);
			}
		});
		requestQueue.add(request);
	}
}
