package com.zhiyuan.androidwidget.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;

/**
 * Created by stefan on 2017/4/14.
 */

public class RecycleViewActivity extends BaseActivity {
	@BindView(R.id.listview_id)
	ListView mListviewId;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
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
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_recycleview;
	}
}
