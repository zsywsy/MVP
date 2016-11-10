package com.zsy.mvp1.app;

import android.content.Context;

import com.zsy.mvp1.retrofit.api.HttpApi;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by 24275 on 2016/10/12.
 */

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    Context getContext();

    Retrofit getRetrofit();

    HttpApi getHttpApi();

    void inject(AppContext context);

}
