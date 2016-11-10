package com.zsy.mvp1.ui.guide;

import android.widget.Button;

import com.zsy.mvp1.R;
import com.zsy.mvp1.ui.login.LoginActivity;
import com.zsy.sum.app.BaseFragment;
import com.zsy.sum.utils.ActUtils;
import com.zsy.sum.utils.SpUtils;
import com.zsy.sum.utils.depend.Lg;

import butterknife.BindView;
import butterknife.OnClick;

import com.zsy.mvp1.ui.main.MainActivity;

/**
 * Created by 24275 on 2016/10/8.
 */

public class GuideFragment3 extends BaseFragment {

    @BindView(R.id.btn_enter) Button enterBtn;

    public GuideFragment3() {
        Lg.i("logNewFg", this.getClass().getSimpleName());
    }

    public static GuideFragment3 newInstance() {
        GuideFragment3 fragment = new GuideFragment3();
        Lg.i("logNewInstance", fragment.getClass().getSimpleName());
        return fragment;
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.fragment_guide3);
    }

    @OnClick(R.id.btn_enter)
    void enterApp() {
        SpUtils.setExecuted(getContext());
        ActUtils.frag2Act(this, LoginActivity.class);
        getActivity().finish();

    }

}
