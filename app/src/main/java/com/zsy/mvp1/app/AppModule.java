package com.zsy.mvp1.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 24275 on 2016/10/12.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        if (context instanceof Application) {
            return context;
        }
        return context.getApplicationContext();
    }

}
