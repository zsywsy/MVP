package com.zsy.sum.mvp.api;

import android.support.annotation.NonNull;

/**
 * Created by 24275 on 2016/9/22.
 */

public interface Presenter {

    void attachView(@NonNull View view);

    void detachView();

}
