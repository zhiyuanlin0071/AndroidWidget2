package com.zhiyuan.androidwidget.ui.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhiyuan.androidwidget.R;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;

/**
 * Created by stefan on 2017/4/20.
 */

public class ViewPagerActivity extends BaseActivity {
	@BindView(R.id.pagertitle)
	PagerTitleStrip mPagertabstrip;
	private View			view1, view2, view3;
	@BindView(R.id.viewpager)
	ViewPager				mViewpager;
	private LayoutInflater	mInflater;
	private List<View>		mViewList;
	private List<String>	mTabList;
	@Override
	protected void init() {
		mInflater = getLayoutInflater();
		view1 = mInflater.inflate(R.layout.viewpager_first, null);
		view2 = mInflater.inflate(R.layout.viewpager_second, null);
		view3 = mInflater.inflate(R.layout.viewpager_third, null);
		
	}
	
	@Override
	protected void initData() {
		mViewList = new ArrayList<>();
		mViewList.add(view1);
		mViewList.add(view2);
		mViewList.add(view3);
		mTabList = new ArrayList<>();
		mTabList.add("first");
		mTabList.add("second");
		mTabList.add("third");
		mViewpager.setAdapter(mPagerAdapter);
		mViewpager.setOffscreenPageLimit(3);
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_viewpager;
	}
	PagerAdapter mPagerAdapter = new PagerAdapter() {
		@Override
		public int getCount() {
			return mViewList.size();
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mViewList.remove(position));
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mViewList.get(position));
			return mViewList.get(position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTabList.get(position);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
