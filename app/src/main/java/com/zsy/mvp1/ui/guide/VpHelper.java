package com.zsy.mvp1.ui.guide;

/**
 * Created by 24275 on 2016/10/9.
 */

public class VpHelper {

    public static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
