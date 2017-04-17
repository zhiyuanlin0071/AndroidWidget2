package com.zhiyuan.androidwidget.ui.activity;

import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.ui.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
	private String[]				mCategory	= new String[]{"LinnerLayout vertical","LinnerLayout horizontal", "GridLayout", "WaterfallLayout"};
	private ArrayAdapter<String>	mArrayAdapter;
	private List<String>			mDatas		= new ArrayList<>();
	private MyAdapter				mMyAdapter;
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
//		mLinearLayout = new LinearLayoutManager(this);
//		mContent.setLayoutManager(mLinearLayout);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        mContent.setLayoutManager(layoutManager);
		mContent.setHasFixedSize(true);
		mContent.setItemAnimator(new DefaultItemAnimator());
		mContent.addItemDecoration(new SpaceItem(20));
		
	}
	
	@Override
	protected void initData() {
		for (int i = 0; i < 20; i++) {
			mDatas.add("text" + i);
		}
		mMyAdapter = new MyAdapter(mDatas);
		mContent.setAdapter(mMyAdapter);
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
                        mContent.setLayoutManager(new LinearLayoutManager(RecycleViewActivity.this,LinearLayoutManager.HORIZONTAL,false));
						break;
					case 2 :
                        mContent.setLayoutManager(new GridLayoutManager(RecycleViewActivity.this, 3));
						break;
                    case 3:
                        mContent.setLayoutManager(new StaggeredGridLayoutManager(4
                                , StaggeredGridLayoutManager.VERTICAL
                        ));
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
//			 if (parent.getChildPosition(view) == 0)
//			 outRect.top = space;
		}
	}
}
