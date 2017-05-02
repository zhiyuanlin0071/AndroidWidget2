package com.zhiyuan.androidwidget.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * Created by stefan on 2017/4/25.
 */

public class AndroidService extends Service {
	private final String	TAG		= getClass().getSimpleName();
	int						count	= 0;
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "*******onBind*******");
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "*******onStartCommand*******");
		doSometing();
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void doSometing() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					Log.i(TAG, "*******" + i + "*******");
				}
			}
		}).start();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "*******onCreate*******");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "*******onUnbind*******");
		
		return super.onUnbind(intent);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.i(TAG, "*******onConfigurationChanged*******");
		
	}
	
	@Override
	protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
		super.dump(fd, writer, args);
		Log.i(TAG, "*******dump*******");
		
	}
	
	@Override
	public void onTaskRemoved(Intent rootIntent) {
		super.onTaskRemoved(rootIntent);
		Log.i(TAG, "*******onTaskRemoved*******");
		
	}
	
	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		Log.i(TAG, "*******onRebind*******");
		
	}
	
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		Log.i(TAG, "*******onTrimMemory*******");
		
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.i(TAG, "*******onLowMemory*******");
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "*******onDestroy*******");
		
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i(TAG, "*******onStart*******");
		
	}
	
	public AndroidService() {
		super();
		Log.i(TAG, "*******AndroidService*******");
		
	}
}
