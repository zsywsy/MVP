package com.zsy.mvp1.ui.guide;

import com.zsy.mvp1.R;
import com.zsy.sum.app.BaseFragment;
import com.zsy.sum.utils.depend.Lg;

/**
 * Created by 24275 on 2016/10/8.
 */

public class GuideFragment2 extends BaseFragment {


    public GuideFragment2() {
        Lg.i("logNewFg", this.getClass().getSimpleName());
    }

    public static GuideFragment2 newInstance() {
        GuideFragment2 fragment = new GuideFragment2();
        Lg.i("logNewInstance", fragment.getClass().getSimpleName());
        return fragment;
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.fragment_guide2);
    }
}
