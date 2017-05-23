package com.zhiyuan.androidwidget.ui.activity;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.lazylibrary.util.ToastUtils;
import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.model.Weather;
import com.zhiyuan.androidwidget.presenter.NetworkOkHttp2;
import com.zhiyuan.androidwidget.presenter.NetworkVolley;
import com.zhiyuan.androidwidget.utill.http.GsonRequest;
import com.zhiyuan.androidwidget.utill.http.XMLRequest;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetworkActivity extends BaseActivity {
	private final String	TAG			= getClass().getSimpleName();
	@BindView(R.id.get_btn)
	Button					mGetBtn;
	@BindView(R.id.post_btn)
	Button					mPostBtn;
	RequestQueue			requestQueue;
	@BindView(R.id.json_btn)
	Button					mJsonBtn;
	@BindView(R.id.image_btn)
	Button					mImageBtn;
	@BindView(R.id.image_view)
	ImageView				mImageView;
	@BindView(R.id.net_image_view)
	NetworkImageView		mNetImageView;
	@BindView(R.id.xml_btn)
	Button					mXmlBtn;
	@BindView(R.id.gson_btn)
	Button					mGsonBtn;
	private String			weather		= "http://api.weatherdt.com/common/?area=101160901|101160801&type=air&key=fd034bf8fe70289698ec4ea79876feaa";
	private String			imageview	= "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
	private NetworkVolley	mNetworkVolley;
	private NetworkOkHttp2	mNetworkOkHttp;
	@Override
	protected void init() {
		requestQueue = Volley.newRequestQueue(this);
		// mNetworkVolley = new NetworkVolley(requestQueue);
		mNetworkOkHttp = new NetworkOkHttp2(this);
	}
	
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_network;
	}
	
	@OnClick({R.id.get_btn, R.id.post_btn, R.id.json_btn, R.id.image_btn, R.id.xml_btn, R.id.gson_btn})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.get_btn :
				get();
				break;
			case R.id.post_btn :
				post();
				break;
			case R.id.json_btn :
				json();
				break;
			case R.id.image_btn :
				// imageRequest();
				// imageLoader();
				networkImage();
				break;
			case R.id.xml_btn :
				xmlRequest();
				break;
			case R.id.gson_btn :
				gsonRequest();
				break;
		}
	}
	
	private void gsonRequest() {
		String gson = "http://api.weatherdt.com/common/?area=101160901|101160801&type=forecast&key=fd034bf8fe70289698ec4ea79876feaa";
		GsonRequest<Weather> request = new GsonRequest(gson, Weather.class, new Response.Listener<Weather>() {
			@Override
			public void onResponse(Weather weather) {
				Weather.WeatherinfoBean weatherinfoBean = weather.getWeatherinfo();
				Log.d(TAG, weatherinfoBean.getCity());
				Log.d(TAG, weatherinfoBean.getIsRadar());
				Log.d(TAG, weatherinfoBean.getRain());
				Log.d(TAG, weatherinfoBean.getTime());
				Log.d(TAG, weatherinfoBean.getWD());
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				ToastUtils.showToast(getApplicationContext(), volleyError.getMessage().toString());
			}
		});
	}
	
	private void xmlRequest() {
		String xml = "http://flash.weather.com.cn/wmaps/xml/china.xml";
		XMLRequest xmlRequest = new XMLRequest(xml, new Response.Listener<XmlPullParser>() {
			@Override
			public void onResponse(XmlPullParser xmlPullParser) {
				try {
					int eventType = xmlPullParser.getEventType();
					while (eventType != XmlPullParser.END_DOCUMENT) {
						switch (eventType) {
							case XmlPullParser.START_TAG :
								String nodeName = xmlPullParser.getName();
								if ("city".equals(nodeName)) {
									Log.d(TAG, xmlPullParser.getAttributeValue(0).toString());
								}
								break;
							case XmlPullParser.END_TAG :
								break;
						}
						eventType = xmlPullParser.next();
					}
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.d(TAG, volleyError.getMessage(), volleyError);
			}
		});
		requestQueue.add(xmlRequest);
	}
	
	private void imageRequest() {
		ImageRequest request = new ImageRequest(imageview, new Response.Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap bitmap) {
				Log.d(TAG, "successful");
				mImageView.setImageBitmap(bitmap);
			}
		}, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.d(TAG, volleyError.getMessage(), volleyError);
				mImageView.setImageResource(R.mipmap.ic_launcher);
			}
		});
		requestQueue.add(request);
	}
	private void imageLoader() {
		ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
			@Override
			public Bitmap getBitmap(String s) {
				return null;
			}
			
			@Override
			public void putBitmap(String s, Bitmap bitmap) {
				
			}
		});
		ImageLoader.ImageListener imageListener = imageLoader.getImageListener(mImageView, R.mipmap.ic_launcher, R.drawable.back_sel);
		imageLoader.get(imageview, imageListener, 0, 0);
	}
	private void networkImage() {
		ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
			@Override
			public Bitmap getBitmap(String s) {
				return null;
			}
			
			@Override
			public void putBitmap(String s, Bitmap bitmap) {
			}
		});
		mNetImageView.setDefaultImageResId(R.mipmap.ic_launcher);
		mNetImageView.setErrorImageResId(R.mipmap.ic_launcher);
		mNetImageView.setImageUrl(imageview, imageLoader);
	}
	
	private void json() {
		JsonObjectRequest request = new JsonObjectRequest(weather, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				Log.d(TAG, jsonObject.toString());
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.d(TAG, volleyError.getMessage(), volleyError);
			}
		});
		requestQueue.add(request);
	}
	
	private void get() {
		// mNetworkVolley.get();
		mNetworkOkHttp.get();
	}
	
	private void post() {
		// mNetworkVolley.post();
		mNetworkOkHttp.post();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
