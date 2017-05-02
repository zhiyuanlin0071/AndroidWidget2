package com.zhiyuan.androidwidget.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.service.AndroidService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by stefan on 2017/4/25.
 */

public class ServiceActivity extends BaseActivity {
	private final String	TAG	= getClass().getSimpleName();
	@BindView(R.id.button_start_service)
	Button					mButtonStartService;
	@BindView(R.id.button_close_service)
	Button					mButtonCloseService;
	Intent					intent;
	@BindView(R.id.button_bind_service)
	Button					mButtonBindService;
	
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
		return R.layout.activity_service;
	}
	
	@OnClick(R.id.button_start_service)
	public void startService() {
		intent = new Intent(this, AndroidService.class);
		startService(intent);
		
	}
	@OnClick(R.id.button_close_service)
	public void closeService() {
		intent = new Intent(this, AndroidService.class);
		stopService(intent);
	}
	@OnClick(R.id.button_bind_service)
	public void bindService() {
		intent = new Intent(this, AndroidService.class);
		bindService(intent, con, BIND_AUTO_CREATE);
	}
	ServiceConnection con = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(TAG, "onServiceConnected");
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "onServiceDisconnected");
		}
	};
	
}
