package com.zhiyuan.androidwidget.ui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stefan on 2017/4/14.
 */

public class MyAdapter extends BaseAdapter {
	private Context	mContext;
	List<String>	list;

    public MyAdapter(Context context, List<String> list) {
        mContext = context;
        this.list = list;
    }

    @Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
    static class ViewHolder{
        private TextView mTextView;

    }
}
