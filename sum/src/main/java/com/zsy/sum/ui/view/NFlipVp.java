package com.zsy.sum.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zsy.sum.utils.depend.Lg;

public class NFlipVp extends ViewPager {

    public NFlipVp(Context context) {
        this(context, null);
    }

    public NFlipVp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Lg.i("logTouch", "vp click");
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

}
