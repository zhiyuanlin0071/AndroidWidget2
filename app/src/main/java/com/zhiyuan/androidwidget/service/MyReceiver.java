package com.zhiyuan.androidwidget.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.zhiyuan.androidwidget.ui.activity.BroadcastActivity;

public class MyReceiver extends BroadcastReceiver {
	private final String	AIRPLANE_MODE	= "Intent.ACTION_AIRPLANE_MODE_CHANGED";
	private final String	INTENENT		= "android.net.conn.CONNECTIVITY_CHANGE";
	public MyReceiver() {
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		switch (intent.getAction()) {
			case BroadcastActivity.ACTION_STATIC :
				Toast.makeText(context, "静态广播", Toast.LENGTH_SHORT).show();
				break;
			case AIRPLANE_MODE :
				Toast.makeText(context, "开始飞行模式", Toast.LENGTH_SHORT).show();
				break;
			case INTENENT :
				Toast.makeText(context, "网络", Toast.LENGTH_SHORT).show();
				break;
		}
	}
}
