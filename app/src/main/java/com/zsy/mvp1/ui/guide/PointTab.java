package com.zsy.mvp1.ui.guide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 24275 on 2016/10/9.
 */

public class PointTab extends LinearLayout {

    private Context context;
    private View[] views;
    private int count;

    public PointTab(Context context) {
        this(context, null);
    }

    public PointTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setData(int resId, ViewPager viewPager) {
        count = viewPager.getAdapter().getCount();
        views = new View[count];
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0; i < count; i++) {
            View view = inflater.inflate(resId, this, false);
            views[i] = view;
            addView(view);
        }
        setSelected(0);
        initEvent(viewPager);
    }

    private void initEvent(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setSelected(int position) {
        for (View view : views) {
            view.setSelected(false);
        }
        views[position].setSelected(true);
    }

}
