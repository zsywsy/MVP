package com.zsy.mvp1.ui.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zsy.sum.app.BaseFragment;
import com.zsy.sum.utils.depend.Lg;

/**
 * Created by 24275 on 2016/10/8.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private int adpterCount;

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Lg.i("getItem:" + adpterCount++);
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = GuideFragment1.newInstance();
                break;
            case 1:
                fragment = GuideFragment2.newInstance();
                break;
            case 2:
                fragment = GuideFragment3.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
