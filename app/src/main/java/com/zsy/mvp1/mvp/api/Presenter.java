package com.zsy.mvp1.mvp.api;

import android.support.annotation.NonNull;

/**
 * Created by 24275 on 2016/10/13.
 */

public interface Presenter <V extends BaseView> {

    void attachView(@NonNull V view);

    void detachView();

}