package com.zsy.nv;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 24275 on 2016/11/1.
 */

public class CusWebView extends WebView {

    private OnScrollChangedLsn onScrollChangedLsn;

    public CusWebView(Context context) {
        this(context, null);
    }

    public CusWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CusWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedLsn != null) {
            onScrollChangedLsn.onScrollChanged(l, t, oldl, oldt);
        }
    }


    public void setOnScrollChangedLsn(OnScrollChangedLsn onScrollChangedLsn) {
//        setWebChromeClient();
        this.onScrollChangedLsn = onScrollChangedLsn;
    }



    public interface OnScrollChangedLsn {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

}
