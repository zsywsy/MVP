package com.zsy.mvp1.ui.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.zsy.mvp1.R;
import com.zsy.sum.app.BaseFragment;
import com.zsy.sum.utils.depend.Lg;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 24275 on 2016/10/8.
 */

public class GuideFragment1 extends BaseFragment {


    private int count;

    @BindView(R.id.btn_count) Button countBtn;

    public GuideFragment1() {
        Lg.i("log1NewFg1", this.getClass().getSimpleName());
    }

    public static GuideFragment1 newInstance() {
        GuideFragment1 fragment = new GuideFragment1();
        Lg.i("log1NewInstance1", fragment.getClass().getSimpleName());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Lg.i("log1Save1", "null");
        } else {
            Lg.i("log1Save1", savedInstanceState.getString("name"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.i("logSave","save name");
        outState.putString("name", "mzs");
    }


    @Override
    public void setLayout() {
        setContentView(R.layout.fragment_guide1);
    }

    @OnClick(R.id.btn_count)
    void count() {
        Lg.i("count:" + count++);
        Lg.i("toString:" + this);
    }

    @Override
    public String toString() {
        return super.toString() + ";    count:" + count;
    }
}
