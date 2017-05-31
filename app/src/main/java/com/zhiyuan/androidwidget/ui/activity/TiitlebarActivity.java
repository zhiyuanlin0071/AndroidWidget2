package com.zhiyuan.androidwidget.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.lazylibrary.util.ToastUtils;
import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.widget.MyTitlebar;

public class TiitlebarActivity extends AppCompatActivity {
	private MyTitlebar myTitlebar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tiitlebar);
		init();
	}
	
	private void init() {
		myTitlebar = (MyTitlebar) findViewById(R.id.mytitlebar);
		myTitlebar.setTitleBtnListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.left_btn :
						ToastUtils.showToast(getApplicationContext(), "left btn");
						break;
					case R.id.right_btn :
						ToastUtils.showToast(getApplicationContext(), "right btn");
						break;
				}
			}
		});
	}
	
}
