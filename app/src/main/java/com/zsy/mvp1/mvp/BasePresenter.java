package com.zsy.mvp1.mvp;

import android.support.annotation.NonNull;

import com.zsy.mvp1.mvp.api.BaseView;
import com.zsy.mvp1.mvp.api.Presenter;

/**
 * Created by 24275 on 2016/9/22.
 */

public class BasePresenter<V extends BaseView> implements Presenter<V> {

    protected V view;

    @Override
    public void attachView(@NonNull V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
