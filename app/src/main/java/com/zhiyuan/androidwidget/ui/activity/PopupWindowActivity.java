package com.zhiyuan.androidwidget.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by stefan on 2017/4/21.
 */

public class PopupWindowActivity extends BaseActivity {
	@BindView(R.id.button)
	Button				mButton;
	private View		mPop;
	private PopupWindow	popupWindow;
	
	@Override
	protected void init() {
		initPop();
	}
	
	private void initPop() {
		mPop = LayoutInflater.from(this).inflate(R.layout.pop_item, null);
		popupWindow = new PopupWindow(mPop, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	}
	
	@OnClick(R.id.button)
	public void button() {
		popupWindow.showAsDropDown(mButton);
	}
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_pop;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
}
