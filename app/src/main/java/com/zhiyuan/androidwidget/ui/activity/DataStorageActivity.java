package com.zhiyuan.androidwidget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataStorageActivity extends BaseActivity {
	
	@BindView(R.id.listview_id)
	ListView mListviewId;
	
	@Override
	protected void init() {
		mListviewId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
					case 0 :
						Intent intent = new Intent(DataStorageActivity.this, FileStorageActivity.class);
						startActivity(intent);
						break;
					case 3 :
						Intent intent3 = new Intent(DataStorageActivity.this, GreenDaoActivity.class);
						startActivity(intent3);
						break;
				}
			}
		});
	}
	
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_data_storage;
	}
	
}
