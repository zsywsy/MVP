package com.zsy.mvp1.ui.login;


import com.zsy.mvp1.bean.HttpRepApi;
import com.zsy.mvp1.mvp.BasePresenter;
import com.zsy.mvp1.mvp.api.BaseView;

/**
 * Created by 24275 on 2016/10/12.
 */

public class LoginContact {

    interface View extends BaseView, HttpRepApi<String> {

        void setAccount(String account);

        void showLoading();

        void hideLoading();

    }

    static abstract class Presenter extends BasePresenter<View> {
        abstract void login(String account, String pwd);

        abstract void setAccount();

        abstract void cancelLogin();
    }


}
