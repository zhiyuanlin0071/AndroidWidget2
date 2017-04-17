package com.zhiyuan.androidwidget.ui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiyuan.androidwidget.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by stefan on 2017/4/14.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
	private List<String> data = null;
	List<Integer> mheight;

	public MyAdapter(List<String> data) {
		this.data = data;
//		mheight=new ArrayList<Integer>();//瀑布式
//		for(int i=0;i<data.size();i++){
//			mheight.add((int)(100+Math.random()*300));
//		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, null);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
//		ViewGroup.LayoutParams lp=holder.mTextView.getLayoutParams();
//		lp.height=mheight.get(position);
//		holder.mTextView.setLayoutParams(lp);
		holder.mTextView.setText(data.get(position));
	}
	
	@Override
	public int getItemCount() {
		return data.size();
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView mTextView;
		public ViewHolder(View itemView) {
			super(itemView);
			mTextView = (TextView) itemView.findViewById(R.id.list_item);
		}
	}
	
}
