package com.zhiyuan.androidwidget.ui.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lazylibrary.util.ToastUtils;
import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.widget.MyTitlebar;
import com.zhiyuan.androidwidget.widget.TagsLayout;

public class TiitlebarActivity extends AppCompatActivity {
	private MyTitlebar	myTitlebar;
	private TagsLayout	mTaglayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tiitlebar);
		init();
	}
	
	private void init() {
		myTitlebar = (MyTitlebar) findViewById(R.id.mytitlebar);
		mTaglayout = (TagsLayout) findViewById(R.id.taglayout);
		ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		String[] string = {"从我写代代码", "6546545646546", "从我写代码那天起", "我就没有打算写代码", "没打算", "写代码"};
		
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
		
		for (int i = 0; i < 5; i++) {
			TextView textView = new TextView(this);
			textView.setText(string[i]);
			textView.setTextColor(Color.WHITE);
			textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
			mTaglayout.addView(textView, lp);
		}
	}
	
}
