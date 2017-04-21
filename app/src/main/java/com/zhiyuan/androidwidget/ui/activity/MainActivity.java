package com.zhiyuan.androidwidget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
	@BindView(R.id.listview_id)
	ListView mListviewId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	protected void init() {
		
	}
	
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		mListviewId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
					case 0 :
						Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
						startActivity(intent);
						break;
					case 1 :
						Intent intent1 = new Intent(MainActivity.this, ViewPagerActivity.class);
						startActivity(intent1);
						break;
					case 2 :
						Intent intent2 = new Intent(MainActivity.this, WebViewActivity.class);
						startActivity(intent2);
						break;
					case 3 :
						Intent intent3 = new Intent(MainActivity.this, XmlActivity.class);
						startActivity(intent3);
						break;
				}
			}
		});
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_main;
	}
	
}
