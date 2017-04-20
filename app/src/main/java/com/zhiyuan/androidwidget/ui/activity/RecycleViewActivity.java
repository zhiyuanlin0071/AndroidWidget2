package com.zhiyuan.androidwidget.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhiyuan.androidwidget.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by stefan on 2017/4/14.
 */

public class RecycleViewActivity extends BaseActivity {
	
	@BindView(R.id.content)
	RecyclerView					mContent;
	@BindView(R.id.spinner)
	Spinner							mSpinner;
	@BindView(R.id.add)
	Button							mAdd;
	@BindView(R.id.remove)
	Button							mRemove;
	private LinearLayoutManager		mLinearLayout;
	private String[]				mCategory	= new String[]{"LinnerLayout vertical", "LinnerLayout horizontal", "GridLayout", "WaterfallLayout"};
	private ArrayAdapter<String>	mArrayAdapter;
	private List<String>			mDatas		= new ArrayList<>();
	// private MyAdapter mMyAdapter;
	private CommonAdapter<String>	mCommonAdapter;
	private HeaderAndFooterWrapper	mHeaderAndFooterWrapper;
	private EmptyWrapper			mEmptyWrapper;
	private LoadMoreWrapper			mLoadMoreWrapper;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void init() {
		initSpinner();
		initRecycleView();
	}
	
	private void initSpinner() {
		mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, mCategory);
		mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(mArrayAdapter);
	}
	
	private void initRecycleView() {
		mLinearLayout = new LinearLayoutManager(this);
		mContent.setLayoutManager(mLinearLayout);
		// StaggeredGridLayoutManager layoutManager = new
		// StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
		mContent.setLayoutManager(mLinearLayout);
		mContent.setHasFixedSize(true);
		mContent.setItemAnimator(new DefaultItemAnimator());
		mContent.addItemDecoration(new SpaceItem(20));
		
	}
	
	@Override
	protected void initData() {
		for (int i = 0; i < 20; i++) {
			mDatas.add("text" + i);
		}
		mCommonAdapter = new CommonAdapter<String>(this, R.layout.item_text, mDatas) {
			@Override
			protected void convert(ViewHolder holder, String s, int position) {
				holder.setText(R.id.list_item, s);
			}
		};
		initHead();
		initLoadMore();
		// mMyAdapter = new MyAdapter(mDatas);
		mContent.setAdapter(mLoadMoreWrapper);
		
	}
	
	private void initLoadMore() {
		mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
		mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
		mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
			@Override
			public void onLoadMoreRequested() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						mDatas.add("add");
						mLoadMoreWrapper.notifyDataSetChanged();
					}
				}, 2000);
			}
		});
	}
	
	private void initHead() {
		mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
		TextView textView = new TextView(this);
		textView.setText("head");
		textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		textView.setGravity(Gravity.CENTER);
		TextView textView1 = new TextView(this);
		textView1.setText("foot");
		mHeaderAndFooterWrapper.addHeaderView(textView);
		mHeaderAndFooterWrapper.addFootView(textView1);
	}
	
	@Override
	protected void setListener() {
		mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
					case 0 :
						mContent.setLayoutManager(new LinearLayoutManager(RecycleViewActivity.this));
						break;
					case 1 :
						mContent.setLayoutManager(new LinearLayoutManager(RecycleViewActivity.this, LinearLayoutManager.HORIZONTAL, false));
						break;
					case 2 :
						mContent.setLayoutManager(new GridLayoutManager(RecycleViewActivity.this, 3));
						break;
					case 3 :
						mContent.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
						break;
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_recycleview;
	}
	public class SpaceItem extends RecyclerView.ItemDecoration {
		private int space;
		
		public SpaceItem(int space) {
			this.space = space;
		}
		
		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
			outRect.left = space;
			outRect.right = space;
			outRect.bottom = space;
			outRect.top = space;
			// if (parent.getChildPosition(view) == 0)
			// outRect.top = space;
		}
	}
	
	@OnClick({R.id.add, R.id.remove})
	public void onclick(View view) {
		switch (view.getId()) {
			case R.id.add :
				// mMyAdapter.add(1);
				break;
			case R.id.remove :
				// mMyAdapter.remove(1);
				break;
		}
	}
}
