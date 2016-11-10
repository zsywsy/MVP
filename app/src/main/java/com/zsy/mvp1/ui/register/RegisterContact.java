package com.zsy.mvp1.ui.register;

import com.zsy.mvp1.bean.HttpRepApi;
import com.zsy.mvp1.mvp.BasePresenter;
import com.zsy.mvp1.mvp.api.BaseView;

/**
 * Created by 24275 on 2016/10/14.
 */

public class RegisterContact {
    interface View extends BaseView, HttpRepApi<String> {
        void showLoading();

        void hideLoading();
    }

    static abstract class Presenter extends BasePresenter<View> {
        abstract void register(String account, String pwd);

        abstract void cancelRegister();
    }
}
