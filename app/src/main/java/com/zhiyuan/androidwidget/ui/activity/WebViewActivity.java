package com.zhiyuan.androidwidget.ui.activity;

import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyuan.androidwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by stefan on 2017/4/20.
 */

public class WebViewActivity extends BaseActivity {
	@BindView(R.id.back)
	Button			mBack;
	@BindView(R.id.title)
	TextView		mTitle;
	@BindView(R.id.refresh)
	Button			mRefresh;
	@BindView(R.id.webview)
	WebView			mWebview;
	private float	exitTime;
	
	@Override
	protected void init() {
		mWebview.getSettings().setJavaScriptEnabled(true);
	}
	
	@Override
	protected void initData() {
		mWebview.loadUrl("http://www.baidu.com/");
		mWebview.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				mTitle.setText(title);
			}
		});
		mWebview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				mWebview.loadUrl(url);
				return true;
			}
		});
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_webview;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@OnClick({R.id.back, R.id.refresh})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.back :
				finish();
				break;
			case R.id.refresh :
				mWebview.reload();
				break;
		}
	}
	@Override
	public void onBackPressed() {
		if (mWebview.canGoBack()) {
			mWebview.goBack();
		} else {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			
		}
	}
}
