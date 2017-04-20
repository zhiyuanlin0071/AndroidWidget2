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
import static com.zhiyuan.androidwidget.R.array.view;

/**
 * Created by stefan on 2017/4/14.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
	private List<String>	data	= null;
	List<Integer>			mheight;
	public interface OnitemClickListener{
//		void onItemLongClick(View view,int posiotion);
		void onItemClick(View view,int posiotion);
	}
	private View.OnClickListener mOnClickListener;
	private OnitemClickListener mOnitemClickListener;

	public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
		mOnitemClickListener = onitemClickListener;
	}
	public MyAdapter(List<String> data) {
		this.data = data;
//		mheight = new ArrayList<Integer>();// 瀑布式
//		for (int i = 0; i < data.size(); i++) {
//			mheight.add((int) (100 + Math.random() * 300));
//		}
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
//		ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//		lp.height = mheight.get(position);
//		holder.itemView.setLayoutParams(lp);
		holder.mTextView.setText(data.get(position));
//		holder.itemView.setOnClickListener(mOnClickListener);
//		if (mOnitemClickListener!=null)
//		{
//			holder.itemView.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					int pos=holder.getLayoutPosition();
//					mOnitemClickListener.onItemClick(v,pos);
//				}
//			});
//			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//				@Override
//				public boolean onLongClick(View v) {
////					int pos = holder.getLayoutPosition();
////					mOnitemClickListener.onItemLongClick(v, pos);
//					return false;
//				}
//			});
//		}

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
	public void add(int position) {
		data.add(position, "nihaoma");
		notifyItemInserted(position);
	}
	public void remove(int position) {
		data.remove(position);
		notifyItemRemoved(position);
	}

}
