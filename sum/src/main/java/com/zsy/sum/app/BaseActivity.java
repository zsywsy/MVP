package com.zsy.sum.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zsy.sum.app.api.InitUI;
import com.zsy.sum.app.utils.AppState;
import com.zsy.sum.app.utils.AppUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity implements InitUI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppManager.getInstance().getActivityStack().isEmpty()) {
            onFirstActivityCreate();
        }
        AppManager.getInstance().addActivity(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppState.getInstance().setAppRunningForeground(AppUtils.isAppOnForeground(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppState.getInstance().setAppRunningForeground(AppUtils.isAppOnForeground(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
        if (AppManager.getInstance().getActivityStack().isEmpty()) {
            onAllActivityDestroy();
        }
    }

    private void onFirstActivityCreate() {
    }

    private void onAllActivityDestroy() {
    }


    @Override
    public void init() {
        setLayout();
        initInjector();
        initData();
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    @Override
    public abstract void setLayout();

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initEvent() {

    }
}
