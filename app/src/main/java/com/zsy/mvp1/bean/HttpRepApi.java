package com.zsy.mvp1.bean;

/**
 * Created by 24275 on 2016/10/17.
 */

public interface HttpRepApi<T> {

    void onError(Throwable throwable);

    void onFailure(HttpData<T> httpData);

    void onSuccess(HttpData<T> httpData);

}
