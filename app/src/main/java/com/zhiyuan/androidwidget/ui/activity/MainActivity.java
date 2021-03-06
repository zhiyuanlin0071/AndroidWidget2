package com.zhiyuan.androidwidget.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
					case 4 :
						Intent intent4 = new Intent(MainActivity.this, PopupWindowActivity.class);
						startActivity(intent4);
					case 5 :
						Intent intent5 = new Intent(MainActivity.this, ServiceActivity.class);
						startActivity(intent5);
						break;
					case 6 :
						Intent intent6 = new Intent(MainActivity.this, BroadcastActivity.class);
						startActivity(intent6);
						break;
					case 7 :
						Intent intent7 = new Intent(MainActivity.this, ContentProviderActivity.class);
						startActivity(intent7);
						break;
					case 8 :
						Intent intent8 = new Intent(MainActivity.this, JsonActivity.class);
						startActivity(intent8);
						break;
					case 9 :
						Intent intent9 = new Intent(MainActivity.this, DataStorageActivity.class);
						startActivity(intent9);
						break;
					case 10 :
						Intent intent10 = new Intent(MainActivity.this, NetworkActivity.class);
						startActivity(intent10);
						break;
					case 11 :
						Intent intent11 = new Intent(MainActivity.this, RetrofitIpActivity.class);
						startActivity(intent11);
						break;
					case 12 :
						Intent intent12 = new Intent(MainActivity.this, ViewActivity.class);
						startActivity(intent12);
						break;
					case 13 :
						Intent intent13 = new Intent(MainActivity.this, TiitlebarActivity.class);
						startActivity(intent13);
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
