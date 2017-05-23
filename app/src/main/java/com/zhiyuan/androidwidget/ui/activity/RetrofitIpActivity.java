package com.zhiyuan.androidwidget.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiyuan.androidwidget.R;
import com.zhiyuan.androidwidget.api.IpServiceApi;
import com.zhiyuan.androidwidget.model.Ip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitIpActivity extends BaseActivity {
	
	@BindView(R.id.ip_etx)
	EditText			mIpEtx;
	@BindView(R.id.select_btn)
	Button				mSelectBtn;
	@BindView(R.id.detail_tx)
	TextView			mDetailTx;
	private String		url	= "http://ip.taobao.com/service/";
	private Retrofit	retrofit;
	
	@Override
	protected void init() {
		retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create())
				.build();
		
	}
	
	@Override
	protected void initData() {
		
	}
	
	@Override
	protected void setListener() {
		
	}
	
	@Override
	protected int getCotentViewId() {
		return R.layout.activity_retrofit_ip;
	}
	
	@OnClick(R.id.select_btn)
	public void onClick(View view) {
		selectIp();
	}
	
	private void selectIp() {
		String ip = mIpEtx.getText().toString().trim();
		IpServiceApi ipServiceApi = retrofit.create(IpServiceApi.class);
		Call<Ip> call = ipServiceApi.getIpInfo(ip);
		call.enqueue(new Callback<Ip>() {
			@Override
			public void onResponse(Call<Ip> call, Response<Ip> response) {
				if (response != null) {
					mDetailTx.setText(response.body().getData().getCountry());
					mDetailTx.setGravity(Gravity.CENTER);
				}
			}
			
			@Override
			public void onFailure(Call<Ip> call, Throwable t) {
				mDetailTx.setText(t.getMessage());
				mDetailTx.setGravity(Gravity.CENTER);
			}
		});
	}
	
}
