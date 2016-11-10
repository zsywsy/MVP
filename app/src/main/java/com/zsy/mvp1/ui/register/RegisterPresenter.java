package com.zsy.mvp1.ui.register;

import com.zsy.mvp1.RxJavaUtils;
import com.zsy.mvp1.bean.HttpCode;
import com.zsy.mvp1.bean.HttpData;
import com.zsy.mvp1.retrofit.api.HttpApi;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 24275 on 2016/10/14.
 */

public class RegisterPresenter extends RegisterContact.Presenter {

    HttpApi httpApi;
    Subscription subscription;

    @Inject
    public RegisterPresenter(HttpApi httpApi) {
        this.httpApi = httpApi;
    }

    @Override
    void register(String account, String pwd) {
        view.showLoading();
        subscription = httpApi.register(account, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpData<String>>() {
                    @Override
                    public void call(HttpData<String> httpData) {
                        view.hideLoading();
                        if (httpData.getCode() == HttpCode.Success) {
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
    void cancelRegister() {
        RxJavaUtils.unsubscribe(subscription);
    }

    @Override
    public void detachView() {
        super.detachView();
        RxJavaUtils.unsubscribe(subscription);
    }
}
