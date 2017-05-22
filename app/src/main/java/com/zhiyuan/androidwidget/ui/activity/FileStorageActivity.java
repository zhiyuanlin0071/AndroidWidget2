package com.zhiyuan.androidwidget.ui.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.lazylibrary.util.ToastUtils;
import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.utill.http.FileUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhiyuan.androidwidget.R.id.filecontent;
import static com.zhiyuan.androidwidget.R.id.write;

public class FileStorageActivity extends BaseActivity {
	
	@BindView(R.id.filename)
	EditText	mFilename;
	@BindView(R.id.filecontent)
	EditText	mFilecontent;
	@BindView(write)
	Button		mWrite;
	@BindView(R.id.clear)
	Button		mClear;
	@BindView(R.id.read)
	Button		mRead;
	@BindView(R.id.content)
	TextView	mContent;
	@BindView(R.id.assets)
	Button		mAssets;
	@BindView(R.id.read_sdcard_btn)
	Button		mReadSdcardBtn;
	@BindView(R.id.write_sdcard_btn)
	Button		mWriteSdcardBtn;
	
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
	
	@OnClick({R.id.read, write, R.id.clear, R.id.assets, R.id.read_sdcard_btn, R.id.write_sdcard_btn})
	public void button(View v) {
		switch (v.getId()) {
			case R.id.read :
				break;
			case write :
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
			case R.id.read_sdcard_btn :
				readSdcard();
				break;
			case R.id.write_sdcard_btn :
				writeSdcard();
				break;
		}
	}
	
	private void writeSdcard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			try {
				String filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + "test";
				String fileContent = mFilecontent.getText().toString();
				FileOutputStream fileOutputStream = new FileOutputStream(filename);
				fileOutputStream.write(fileContent.getBytes());
				fileOutputStream.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			ToastUtils.showToast(this, "SDcard not found");
		}
		
	}
	
	private void readSdcard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			StringBuilder sb = new StringBuilder("");
			try {
				String filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + "test";
				FileInputStream input = new FileInputStream(filename);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = input.read(bytes)) > 0) {
					sb.append(new String(bytes, 0, len));
				}
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mContent.setText(sb);
			
		} else {
			ToastUtils.showToast(this, "SDcard not found");
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
