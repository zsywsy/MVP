package com.zsy.mvp1;

import com.zsy.mvp1.constant.HttpConst;
import com.zsy.mvp1.retrofit.LogIntercepter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {

    private static final Converter.Factory GsonFactory = GsonConverterFactory.create();
    public static final Converter.Factory ScalarsFactory = ScalarsConverterFactory.create();

    public static final CallAdapter.Factory RxJavaFactory = RxJavaCallAdapterFactory.create();

//    private static Retrofit blogRetrofit;

//    public static Retrofit getBlogRetrofit() {
//        if (blogRetrofit == null) {
//            synchronized (RetrofitUtils.class) {
//                if (blogRetrofit == null) {
//                    blogRetrofit = new Retrofit.Builder()
//                            .baseUrl(HttpConst.BaseUrl)
//                            .addCallAdapterFactory(RxJavaFactory)
//                            .addConverterFactory(ScalarsFactory)
//                            .addConverterFactory(GsonFactory)
//                            .build();
//                }
//            }
//        }
//        return blogRetrofit;
//    }

    private static Retrofit getRetrofit(String baseUrl) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new LogIntercepter()).build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaFactory)
                .addConverterFactory(ScalarsFactory)
                .addConverterFactory(GsonFactory)
                .build();
    }

    public static Retrofit getDefaultRetrofit() {
        return getRetrofit(HttpConst.BaseUrl);
    }

}
