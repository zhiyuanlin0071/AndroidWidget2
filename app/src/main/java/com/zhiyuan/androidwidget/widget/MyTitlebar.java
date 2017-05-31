package com.zhiyuan.androidwidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.icu.text.DisplayContext;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyuan.androidwidget.R;

import org.w3c.dom.Text;

/**
 * Created by stefan on 2017/6/1.
 */

public class MyTitlebar extends RelativeLayout {
	private Button		mLefyBtn;
	private Button		mRight_btn;
	private TextView	mTitle;
	
	// public MyTitlebar(Context context) {
	// super(context);
	// }
	
	public MyTitlebar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initParams(context, attrs);
	}
	
	// public MyTitlebar(Context context, AttributeSet attrs, int defStyleAttr)
	// {
	// super(context, attrs, defStyleAttr);
	// initParams(context, attrs);
	// }
	
	private void initParams(Context context, AttributeSet attrs) {
		LayoutInflater.from(context).inflate(R.layout.merge_titlebar, this, true);
		mLefyBtn = (Button) findViewById(R.id.left_btn);
		mRight_btn = (Button) findViewById(R.id.right_btn);
		mTitle = (TextView) findViewById(R.id.title_txt);
		TypedArray arry = context.obtainStyledAttributes(attrs, R.styleable.MyTitlebar);
		if (arry != null) {
			int backgroundTitle = arry.getResourceId(R.styleable.MyTitlebar_title_backgroud_color, Color.RED);
			setBackgroundResource(backgroundTitle);
			boolean leftvtb = arry.getBoolean(R.styleable.MyTitlebar_left_button_visible, true);
			if (leftvtb) {
				mLefyBtn.setVisibility(VISIBLE);
			} else {
				mLefyBtn.setVisibility(INVISIBLE);
			}
			String leftBtnTxt = arry.getString(R.styleable.MyTitlebar_left_btn_text);
			if (!TextUtils.isEmpty(leftBtnTxt)) {
				mLefyBtn.setText(leftBtnTxt);
				int leftBtnColor = arry.getColor(R.styleable.MyTitlebar_left_btn_color, Color.RED);
				mLefyBtn.setTextColor(leftBtnColor);
			} else {
				int leftBtnDrawable = arry.getResourceId(R.styleable.MyTitlebar_left_button_drawable, R.mipmap.ic_launcher);
				if (leftBtnDrawable != -1) {
					mLefyBtn.setBackgroundResource(leftBtnDrawable);
				}
			}
			int titleDrawable = arry.getResourceId(R.styleable.MyTitlebar_title_text_drawable, -1);
			if (titleDrawable != -1) {
				mTitle.setBackgroundResource(titleDrawable);
			} else {
				String titleTxt = arry.getString(R.styleable.MyTitlebar_title_text);
				if (!TextUtils.isEmpty(titleTxt)) {
					mTitle.setText(titleTxt);
					int titleTxtColor = arry.getColor(R.styleable.MyTitlebar_title_txt_color, Color.RED);
					mTitle.setTextColor(titleTxtColor);
				}
			}
			boolean rightbtn = arry.getBoolean(R.styleable.MyTitlebar_right_button_visible, true);
			if (rightbtn) {
				mRight_btn.setVisibility(VISIBLE);
			} else {
				mRight_btn.setVisibility(INVISIBLE);
			}
			String rightBtnTxt = arry.getString(R.styleable.MyTitlebar_right_button_text);
			if (!TextUtils.isEmpty(rightBtnTxt)) {
				mRight_btn.setText(rightBtnTxt);
				int rightBtnColor = arry.getColor(R.styleable.MyTitlebar_right_button_text_color, Color.RED);
				mRight_btn.setTextColor(rightBtnColor);
			} else {
				int rightBtnDrawable = arry.getResourceId(R.styleable.MyTitlebar_right_button_drawable, R.mipmap.ic_launcher);
				if (rightBtnDrawable != -1) {
					mRight_btn.setBackgroundResource(rightBtnDrawable);
				}
			}
			arry.recycle();
		}
		
	}
	
	public void setTitleBtnListener(OnClickListener listener) {
		if (listener != null) {
			mLefyBtn.setOnClickListener(listener);
			mRight_btn.setOnClickListener(listener);
		}
	}
	
}
