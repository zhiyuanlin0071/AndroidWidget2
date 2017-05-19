package com.zhiyuan.androidwidget.ui.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.lazylibrary.util.ToastUtils;
import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.utill.http.FileUtils;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileStorageActivity extends BaseActivity {
	
	@BindView(R.id.filename)
	EditText	mFilename;
	@BindView(R.id.filecontent)
	EditText	mFilecontent;
	@BindView(R.id.write)
	Button		mWrite;
	@BindView(R.id.clear)
	Button		mClear;
	@BindView(R.id.read)
	Button		mRead;
	@BindView(R.id.content)
	TextView	mContent;
	@BindView(R.id.assets)
	Button		mAssets;
	
	@Override
	protected void init() {
		
	}
	
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_file_storage;
	}
	@OnClick({R.id.read, R.id.write, R.id.clear, R.id.assets})
	public void button(View v) {
		switch (v.getId()) {
			case R.id.read :
				break;
			case R.id.write :
				write();
				break;
			case R.id.clear :
				clear();
				break;
			case R.id.assets :
				try {
					readAssets();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	public void read() {
		String fileName = mFilename.getText().toString();
		try {
			String str = FileUtils.getInstance(this).read(fileName);
			ToastUtils.showToast(this, "读取成功！");
			mContent.setText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void write() {
		String fileName = mFilename.getText().toString();
		String fileContent = mFilecontent.getText().toString();
		try {
			FileUtils.getInstance(this).save(fileName, fileContent);
			ToastUtils.showToast(this, "数据写入成功！");
		} catch (Exception e) {
			ToastUtils.showToast(this, "数据写入失败！");
			e.printStackTrace();
		}
	}
	public void clear() {
		mFilename.setText("");
		mFilecontent.setText("");
		mContent.setText("");
		ToastUtils.showToast(this, "数据清空!");
	}
	public void readAssets() throws IOException {
		StringBuilder sb = new StringBuilder("");
		String fileName = mFilename.getText().toString();
		AssetManager assetManager = getAssets();
		InputStream ip = assetManager.open(fileName);
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = ip.read(bytes)) > 0) {
			sb.append(new String(bytes, 0, len));
		}
		ip.close();
		mContent.setText(sb.toString());
	}
}
