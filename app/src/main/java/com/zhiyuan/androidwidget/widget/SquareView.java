package com.zhiyuan.androidwidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by stefan on 2017/6/2.
 */

public class SquareView extends View {
	private final int	DEFAULT_SIZE	= 100;
	private Paint		mPaint;
	
	public SquareView(Context context) {
		this(context, null);
	}
	
	public SquareView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int width = getMySize(DEFAULT_SIZE, widthMeasureSpec);
		int height = getMySize(DEFAULT_SIZE, heightMeasureSpec);
		Log.i("test", "width:" + width + "****height****" + height);
		
		if (width < height) {
			height = width;
		} else {
			width = height;
		}
		setMeasuredDimension(width, height);
	}
	
	private int getMySize(int defaultSize, int measureSpec) {
		int mySize = 0;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		switch (mode) {
			case MeasureSpec.UNSPECIFIED :
				mySize = defaultSize;
				break;
			case MeasureSpec.AT_MOST :
				mySize = size;
				break;
			case MeasureSpec.EXACTLY :
				mySize = size;
				break;
		}
		return mySize;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i("test", "getMeasuredHeight:" + getMeasuredHeight() + "****height****" + getHeight());
		int radius = 50;
		int x = getLeft() + radius;
		int y = getTop() + radius;
		Log.i("test", "x:" + x + "****y****" + y);
		mPaint.setColor(Color.RED);
		canvas.drawCircle(x, y, 50, mPaint);
	}
}
