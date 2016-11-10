package com.zsy.sum.ui.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class ViewUtils {

    @SuppressWarnings("deprecation")
    public static void setBg(View v, Drawable drawable, int defaultBg) {
        if (drawable == null) {
            v.setBackgroundColor(defaultBg);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        } else {
            v.setBackgroundDrawable(drawable);
        }
    }
}