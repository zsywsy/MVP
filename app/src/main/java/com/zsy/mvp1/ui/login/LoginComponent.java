package com.zsy.mvp1.ui.login;

import com.zsy.mvp1.app.AppComponent;
import com.zsy.mvp1.mvp.api.PerActivity;

import dagger.Component;

/**
 * Created by 24275 on 2016/10/12.
 */

@PerActivity
@Component(dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
