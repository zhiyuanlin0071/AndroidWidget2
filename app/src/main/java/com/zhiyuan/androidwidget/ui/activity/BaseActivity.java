package com.zhiyuan.androidwidget.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;

/**
 * Created by stefan on 2017/4/14.
 */

public abstract class BaseActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(getCotentViewId());
		ButterKnife.bind(this);
		init();
		initData();
		setListener();
	}
	protected abstract void init();
	protected abstract void initData();
	protected abstract void setListener();
	protected abstract int getCotentViewId();
	
}
