package com.zsy.mvp1.retrofit.api;

import com.zsy.mvp1.bean.HttpData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 24275 on 2016/10/13.
 */

public interface HttpApi {

    @FormUrlEncoded
    @POST("servlet/Login")
    Observable<HttpData<String>> login(@Field("account") String account, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("servlet/Register")
    Observable<HttpData<String>> register(@Field("account") String account, @Field("pwd") String pwd);


}
