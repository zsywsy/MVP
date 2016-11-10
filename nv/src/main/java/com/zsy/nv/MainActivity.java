package com.zsy.nv;

import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zsy.sum.app.BaseActivity;
import com.zsy.sum.utils.DirUtils;
import com.zsy.sum.utils.SpUtils;
import com.zsy.sum.utils.depend.Lg;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private final String DirName = "123";
    private final String PageNoKey = "PageNoKey";
    private final String ScrollYKey = "ScrollYKey";

    private int pageNum = 0;
    private int scrollY = 0;

    private boolean isMenuShow = false;
    private boolean isMenuKeyAvaliable = true;

    private File dir;
    private File[] files;

    @BindView(R.id.wv_nv) CusWebView nvWv;
    @BindView(R.id.btn_pre) Button preBtn;
    @BindView(R.id.btn_next) Button nextBtn;
    @BindView(R.id.et_pagenum) EditText pagenumEt;
    @BindView(R.id.btn_jump) Button jumpBtn;
    @BindView(R.id.fl_menu) FrameLayout menuFl;
    @BindView(R.id.ll_menu) LinearLayout menuLll;

    private float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            pageNum = (int) SpUtils.getSate(this, PageNoKey, 0);
            scrollY = (int) SpUtils.getSate(this, ScrollYKey, 0);
            Lg.e("pageNumState:" + pageNum + ";" + scrollY);
        } else {
            pageNum = savedInstanceState.getInt(PageNoKey);
            scrollY = savedInstanceState.getInt(ScrollYKey);
            Lg.e("pageNumStateSave:" + pageNum + ";" + scrollY);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PageNoKey, pageNum);
        outState.putInt(ScrollYKey, scrollY);
    }

    @Override
    public void initData() {
        super.initData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        dir = new File(DirUtils.getExRootDir(), DirName);
        files = dir.listFiles();

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
//        Lg.e(files[pageNum].getAbsolutePath());
        nvWv.loadUrl("file:///" + files[pageNum]);
        nvWv.getSettings().setSupportZoom(true);
        nvWv.getSettings().setBuiltInZoomControls(true);
        nvWv.scrollBy(0, scrollY);
//        try {
//            nvWv.loadData(IOUtils.is2str(new FileInputStream(files[0])),"text/html","UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
//                Lg.e("oldScale:" + oldScale + ":" + newScale + ":" + nvWv.getScale());
                super.onScaleChanged(view, oldScale, newScale);
            }
        };
        nvWv.setWebViewClient(webViewClient);
        nvWv.setOnScrollChangedLsn(new CusWebView.OnScrollChangedLsn() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                scrollY = t;
                Lg.e("l:" + l + ";t:" + t + ";oldL:" + oldl + ";oadT:" + oldt);
//                Lg.e("height:" + nvWv.getHeight() + ";" + nvWv.getContentHeight() * nvWv.getScale());
                Lg.e((nvWv.getContentHeight() * nvWv.getScale()) + ":" + (nvWv.getHeight() + nvWv.getScrollY()));
                if (nvWv.getContentHeight() * nvWv.getScale() - (nvWv.getHeight() + nvWv.getScrollY()) == 0) {
                    if (!isMenuShow) {
                        switchMenu();
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpUtils.setState(this, PageNoKey, pageNum);
        SpUtils.setState(this, ScrollYKey, scrollY);
    }

    @OnClick(R.id.fl_menu)
    void hideMenu() {
        Lg.e("nvwvclick");
        if (isMenuShow) {
            switchMenu();
        }
    }

    @OnClick(R.id.btn_pre)
    void prePage() {
        Lg.e("pre");
        pageNum--;
        loadPage(pageNum);
    }

    @OnClick(R.id.btn_next)
    void nextPage() {
        Lg.e("next");
        pageNum++;
        loadPage(pageNum);
//        nvWv.
    }

    @OnClick(R.id.btn_jump)
    void jumpPage() {
        pageNum = Integer.parseInt(pagenumEt.getText().toString());
        loadPage(pageNum);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((isMenuKeyAvaliable && keyCode == KeyEvent.KEYCODE_MENU)) {
            isMenuKeyAvaliable = false;
            switchMenu();
            return true;
        }
        if ((isMenuShow && keyCode == KeyEvent.KEYCODE_BACK)) {
            switchMenu();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            isMenuKeyAvaliable = true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void loadPage(int page) {
        nvWv.loadUrl("file:///" + files[page]);
        if (isMenuShow) {
            switchMenu();
        }
    }

    private void switchMenu() {
        if (isMenuShow) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_menu_out);
            menuLll.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    menuFl.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {
            menuFl.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_menu_in);
            menuLll.startAnimation(animation);

        }
        isMenuShow = !isMenuShow;

    }

}
