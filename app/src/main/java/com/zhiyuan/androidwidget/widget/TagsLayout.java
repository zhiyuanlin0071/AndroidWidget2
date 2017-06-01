package com.zhiyuan.androidwidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.zhiyuan.androidwidget.R;

import static android.R.attr.max;

/**
 * Created by stefan on 2017/6/1.
 */

public class TagsLayout extends ViewGroup {
	private int	tagVerticalSpace;
	private int	tagHorizontalSpace;
	public TagsLayout(Context context) {
		super(context);
	}
	
	public TagsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	public TagsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}
	
	private void init(Context context, AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagsLayout);
		if (typedArray != null) {
			tagVerticalSpace = typedArray.getDimensionPixelSize(R.styleable.TagsLayout_tagVerticalSpace, 0);
			tagHorizontalSpace = typedArray.getDimensionPixelSize(R.styleable.TagsLayout_tagHorizontalSpace, 0);
			typedArray.recycle();
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		// 如果是warp_content情况下，记录宽和高
		int width = 0;
		int height = 0;
		/**
		 * 记录每一行的宽度，width不断取最大宽度
		 */
		int lineWidth = 0;
		/**
		 * 每一行的高度，累加至height
		 */
		int lineHeight = 0;
		
		int count = getChildCount();
		int left = getPaddingLeft();
		int top = getPaddingTop();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + tagHorizontalSpace;
			int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + tagVerticalSpace;
			if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
				width = Math.max(lineWidth, childWidth);
				lineWidth = childWidth;
				height += lineHeight;
				child.setTag(new Location(left, top + height, childWidth + left - tagHorizontalSpace, height + child.getMeasuredHeight() + top));
				
			} else {
				child.setTag(new Location(lineWidth + left, top + height, lineWidth + childWidth - tagHorizontalSpace + left,
						height + child.getMeasuredHeight() + top));
				lineWidth += childWidth;
				lineHeight = Math.max(lineHeight, childHeight);
			}
		}
		width = Math.max(width, lineWidth) + getPaddingLeft() + getPaddingRight();
		height += lineHeight;
		sizeHeight += getPaddingTop() + getPaddingBottom();
		height += getPaddingTop() + getPaddingBottom();
		setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
		
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() == GONE)
				continue;
			Location location = (Location) child.getTag();
			child.layout(location.left, location.top, location.right, location.bottom);
		}
	}
	
	/**
	 * 记录子控件的坐标
	 */
	public class Location {
		public Location(int left, int top, int right, int bottom) {
			this.left = left;
			this.top = top;
			this.right = right;
			this.bottom = bottom;
		}
		
		public int	left;
		public int	top;
		public int	right;
		public int	bottom;
		
	}
}
