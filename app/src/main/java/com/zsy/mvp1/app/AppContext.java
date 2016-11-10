package com.zsy.mvp1.app;

import android.app.Application;

import com.zsy.sum.utils.depend.Lg;

/**
 * Created by 24275 on 2016/10/10.
 */

public class AppContext extends Application {

    private static AppContext context;
    private AppComponent appComponent;

    public static AppContext getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Lg.init(Lg.LoggerType);
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
