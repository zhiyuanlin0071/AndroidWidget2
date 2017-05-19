package com.zhiyuan.androidwidget.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.zhiyuan.androidwidget.model.DaoMaster;
import com.zhiyuan.androidwidget.model.DaoSession;

/**
 * Created by stefan on 2017/5/17.
 */

public class MyApplication extends Application {
	
	private static DaoSession daoSession;
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}
	
	private void init() {
		/* 配置数据库 */
		setupDatabase();
	}
	/**
	 * 配置数据库
	 */
	private void setupDatabase() {
		/* 创建数据库 */
		DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db", null);
		// 获取可写的数据库SqliteDatebase
		SQLiteDatabase db = devOpenHelper.getWritableDatabase();
		// 获取数据库的对象
		DaoMaster daoMaster = new DaoMaster(db);
		// 获取dao对象管理者
		daoSession = daoMaster.newSession();
	}
	
	public static DaoSession getDaoInstant() {
		return daoSession;
	}
}
