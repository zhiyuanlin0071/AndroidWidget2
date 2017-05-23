package com.zhiyuan.androidwidget.api;

import com.zhiyuan.androidwidget.model.Ip;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by stefan on 2017/5/23.
 */

public interface IpServiceApi {
	@GET("/service/getIpInfo.php")
	Call<Ip> getIpInfo(@Query("ip") String ip);
}
