package com.zhiyuan.androidwidget.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BroadcastActivity extends BaseActivity {
	
	@BindView(R.id.static_bt)
	Button							mStaticBt;
	@BindView(R.id.dymic_bt)
	Button							mDymicBt;
	
	private final String			ACTION_DIYDYMATIC	= "com.dymatic";
	public static final String		ACTION_STATIC		= "com.statci";
	@BindView(R.id.local_bt)
	Button							mLocalBt;
	
	private LocalBroadcastManager	mLb;
	
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
		return R.layout.activity_broadcast;
	}
	
	@OnClick({R.id.dymic_bt, R.id.static_bt, R.id.local_bt})
	public void myOnClick(View view) {
		switch (view.getId()) {
			case R.id.dymic_bt :
				Intent intent = new Intent(ACTION_DIYDYMATIC);
				intent.putExtra("nihao", "nihaoma?");
				sendBroadcast(intent);
				break;
			case R.id.static_bt :
				Intent intent2 = new Intent(ACTION_STATIC);
				sendBroadcast(intent2);
				break;
			case R.id.local_bt :
				Intent intent3 = new Intent(ACTION_DIYDYMATIC);
				intent3.putExtra("nihao", "nihaoma?");
				mLb.sendBroadcast(intent3);
				break;
		}
	}
	
	BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "动态广播", Toast.LENGTH_SHORT).show();
			Toast.makeText(context, intent.getStringExtra("nihao"), Toast.LENGTH_SHORT).show();
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter(ACTION_DIYDYMATIC);
		registerReceiver(mBroadcastReceiver, intentFilter);
		mLb = LocalBroadcastManager.getInstance(this);
		mLb.registerReceiver(mBroadcastReceiver, intentFilter);
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mBroadcastReceiver);
		mLb.unregisterReceiver(mBroadcastReceiver);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
