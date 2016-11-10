package com.zsy.mvp1.ui.guide;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.zsy.mvp1.R;
import com.zsy.mvp1.ui.login.LoginActivity;
import com.zsy.sum.app.BaseActivity;
import com.zsy.sum.utils.ActUtils;
import com.zsy.sum.utils.SpUtils;
import com.zsy.sum.utils.depend.Lg;

import butterknife.BindView;

import com.zsy.mvp1.ui.main.MainActivity;

/**
 * Created by 24275 on 2016/10/8.
 */

public class GuideActivity extends BaseActivity {

    private SimpleFragmentPagerAdapter adapter;

    @BindView(R.id.vp_guide) ViewPager guideVp;
    @BindView(R.id.pointtab_guide) PointTab pointTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SpUtils.isFirstExecute(this)) {
            if (SpUtils.isLogined(this)) {
                ActUtils.act2Act(this, MainActivity.class);
                Lg.v("guideAct -->  mainAct");
            } else {
                ActUtils.act2Act(this, LoginActivity.class);
                Lg.v("guideAct -->  loginAct");
            }
            finish();
        }
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_guide);
    }

    @Override
    public void initView() {
        super.initView();
        adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        guideVp.setAdapter(adapter);
        pointTab.setData(R.layout.guide_point, guideVp);
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }
}
