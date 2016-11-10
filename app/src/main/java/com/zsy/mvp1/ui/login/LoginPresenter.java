package com.zsy.mvp1.ui.login;

import android.content.Context;

import com.zsy.mvp1.RxJavaUtils;
import com.zsy.mvp1.bean.HttpCode;
import com.zsy.mvp1.bean.HttpData;
import com.zsy.mvp1.retrofit.api.HttpApi;
import com.zsy.sum.utils.SpUtils;
import com.zsy.sum.utils.depend.Lg;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 24275 on 2016/9/22.
 */

public class LoginPresenter extends LoginContact.Presenter {

    private Context context;
    private HttpApi httpApi;

    private Subscription subscription;

    @Inject
    public LoginPresenter(Context context, HttpApi api) {
        this.httpApi = api;
        this.context = context;
    }

    @Override
    void setAccount() {
        view.setAccount(SpUtils.getAccount(context));
    }

    @Override
    public void login(final String account, String pwd) {
        Lg.i("login");
        view.showLoading();
        subscription = httpApi.login(account, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpData<String>>() {
                    @Override
                    public void call(HttpData<String> httpData) {
                        view.hideLoading();
                        if (httpData.getCode() == HttpCode.Success) {
                            SpUtils.setLoginState(context, true);
                            SpUtils.putAccount(context, account);
                            view.onSuccess(httpData);
                        } else {
                            view.onFailure(httpData);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.hideLoading();
                        view.onError(throwable);
                    }
                });
    }

    @Override
    void cancelLogin() {
        RxJavaUtils.unsubscribe(subscription);
    }

    @Override
    public void detachView() {
        super.detachView();
        RxJavaUtils.unsubscribe(subscription);
    }
}
