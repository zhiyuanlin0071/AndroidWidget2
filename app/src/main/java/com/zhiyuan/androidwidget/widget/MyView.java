package com.zhiyuan.androidwidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.constraint.solver.SolverVariable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhiyuan.androidwidget.R;

/**
 * Created by stefan on 2017/5/31.
 */

public class MyView extends View {
    private final String TAG = getClass().getSimpleName();
    private Paint mPaint;
    private RectF oval;
    private float radius;
    private int color_main;
    private int color_cover;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initParams(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initParams(context, attrs);
    }

    private void initParams(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        if (array != null) {
            radius = array.getDimension(R.styleable.MyView_radius, 0);
            color_main = array.getColor(R.styleable.MyView_color_main, Color.RED);
            color_cover = array.getColor(R.styleable.MyView_color_conver, Color.WHITE);
            array.recycle();
        }
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        oval = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;

        }
        Log.e(TAG, "onMeasure--widthSize-->" + width);
        Log.e(TAG, "onMeasure--heightMode-->" + heightMode);
        Log.e(TAG, "onMeasure--heightSize-->" + height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout" + "left:" + left + "top:" + top + "right:" + right + "bottom:" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color_main);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        int width = getWidth();
        int height = getHeight();
        Log.e(TAG, "onDraw---->" + width + "*" + height);
//        float radius = width / 4;
        canvas.drawCircle(width / 2, height / 2, radius, mPaint);
        mPaint.setColor(color_cover);
        oval.set(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius);// 用于定义的圆弧的形状和大小的界限
        canvas.drawArc(oval, 180, 120, true, mPaint);

        canvas.drawCircle(width / 4, height / 4, radius / 2, mPaint);


    }
}
