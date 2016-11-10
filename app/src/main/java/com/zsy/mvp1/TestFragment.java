package com.zsy.mvp1;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zsy.sum.app.BaseFragment;
import com.zsy.sum.utils.depend.Lg;

import rx.exceptions.Exceptions;

/**
 * Created by 24275 on 2016/10/10.
 */

public class TestFragment extends BaseFragment {
    @Override
    public void setLayout() {
        setContentView(R.layout.fragment_test);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lg.i("ishidden:" + isHidden());
        if (savedInstanceState == null) {
            Lg.i("save null");
        } else {
            Lg.i("save:" + savedInstanceState.getBoolean("hid"));
        }

    }
}
