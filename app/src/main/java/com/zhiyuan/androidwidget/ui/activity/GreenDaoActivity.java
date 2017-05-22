package com.zhiyuan.androidwidget.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.application.MyApplication;
import com.zhiyuan.androidwidget.model.User;
import com.zhiyuan.androidwidget.model.UserDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends BaseActivity {
	
	@BindView(R.id.user_etx)
	EditText	mUserEtx;
	@BindView(R.id.pass_etx)
	EditText	mPassEtx;
	@BindView(R.id.commit_btn)
	Button		mCommitBtn;
	@BindView(R.id.select_btn)
	Button		mSelectBtn;
	@BindView(R.id.delete_btn)
	Button		mDeleteBtn;
	
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
		return R.layout.activity_green_dao;
	}
	
	@OnClick({R.id.commit_btn, R.id.select_btn, R.id.delete_btn})
	public void onclick(View view) {
		switch (view.getId()) {
			case R.id.commit_btn :
				commit();
				break;
			case R.id.select_btn :
				select(0);
				break;
			case R.id.delete_btn :
				delete();
				break;
		}
	}
	
	private void delete() {
		String username = mUserEtx.getText().toString();
		if (username != null) {
			
		}
	}
	
	private void commit() {
		String username = mUserEtx.getText().toString();
		String pass = mPassEtx.getText().toString();
		if (username != null && pass != null) {
			User user = new User(null, username, pass);
			UserDao userDao = MyApplication.getDaoInstant().getUserDao();
			userDao.insert(user);
		}
	}
	private void select(int i) {
		String username = mUserEtx.getText().toString();
		switch (i) {
			case 0 :
				List<User> userList = MyApplication.getDaoInstant().getUserDao().loadAll();
				for (User user : userList) {
					Log.d("User", "" + user.getUsername() + "///" + user.getPassword() + "///" + user.getId());
				}
				break;
			case 1 :
				if (username != null) {
					User user = MyApplication.getDaoInstant().getUserDao().load((long) 1);
					Log.d("User", "" + user.getUsername() + "///" + user.getPassword() + "///" + user.getId());
				}
				break;
			case 2 :
				if (username != null) {
					User user = MyApplication.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.Username.eq(username)).build().unique();
					Log.d("User", "" + user.getUsername() + "///" + user.getPassword() + "///" + user.getId());
				}
				break;
			
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
	
	public void inserAll() {
		MyApplication.getDaoInstant().getUserDao().getSession().runInTx(new Runnable() {
			@Override
			public void run() {
				
			}
		});
	}
}
