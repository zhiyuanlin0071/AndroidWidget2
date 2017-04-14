package com.zhiyuan.androidwidget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
	@BindView(R.id.listview_id)
	ListView			mListviewId;


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
              switch (position)
              {
                  case 0:
                      Intent intent=new Intent(MainActivity.this,RecycleViewActivity.class);
                      startActivity(intent);
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
