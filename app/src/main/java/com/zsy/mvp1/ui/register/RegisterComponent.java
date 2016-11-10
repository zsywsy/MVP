package com.zsy.mvp1.ui.register;

import com.zsy.mvp1.app.AppComponent;
import com.zsy.mvp1.mvp.api.PerActivity;

import dagger.Component;

/**
 * Created by 24275 on 2016/10/14.
 */

@PerActivity
@Component(dependencies = AppComponent.class)
public interface RegisterComponent {

    void inject(RegisterActivity activity);

}
