package com.zsy.mvp1.app;

import com.zsy.mvp1.RetrofitUtils;
import com.zsy.mvp1.RxJavaUtils;
import com.zsy.mvp1.retrofit.api.HttpApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by 24275 on 2016/10/14.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideGetRetrofit() {
        return RetrofitUtils.getDefaultRetrofit();
    }

    @Provides
    @Singleton
    public HttpApi provideHttpApi(Retrofit retrofit) {
        return retrofit.create(HttpApi.class);
    }

}
