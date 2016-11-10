package com.zsy.mvp1.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.zsy.mvp1.R;
import com.zsy.mvp1.TestFragment;
import com.zsy.mvp1.ui.mf_about.AboutFragment;
import com.zsy.mvp1.ui.mf_home.HomeFragment;
import com.zsy.mvp1.ui.mf_intro.IntroFragment;
import com.zsy.mvp1.ui.mf_notice.NoticeFragment;
import com.zsy.sum.app.BaseActivity;
import com.zsy.sum.ui.view.TabLayout;
import com.zsy.sum.utils.FragmentUtils;
import com.zsy.sum.utils.depend.Lg;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    //    @Inject UserInfo userInfo;

    private Bundle savedInstanceState;
    private int tempFragmentPosition = -1;
    private Fragment[] fragments = new Fragment[4];

    @BindView(R.id.fl_main) FrameLayout mainFl;
    @BindView(R.id.tablayout_main) TabLayout mainTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

//        DaggerMainComponent.builder().build().inject(this);
//        new AlertDialog.Builder().create();
//        Log.i("logInfo", userInfo.toString());
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragments[0] = new HomeFragment();
            fragments[1] = new IntroFragment();
            fragments[2] = new NoticeFragment();
            fragments[3] = new AboutFragment();
            FragmentUtils.loadMultipleRootTransaction(fm, R.id.fl_main, -1, fragments);
        } else {
            fragments[0] = FragmentUtils.findStackFragment(fm, HomeFragment.class);
            fragments[1] = FragmentUtils.findStackFragment(fm, IntroFragment.class);
            fragments[2] = FragmentUtils.findStackFragment(fm, NoticeFragment.class);
            fragments[3] = FragmentUtils.findStackFragment(fm, AboutFragment.class);
        }
    }

    @Override
    public void initView() {
        super.initView();
        mainTabLayout.setTextColor(0xff8A94AD, 0xff60A4E5);
        mainTabLayout.setData(new TabLayout.Entity[]{
                mainTabLayout.new Entity("首页", R.mipmap.icon_tab_home_normal, R.mipmap.icon_tab_home_press),
                mainTabLayout.new Entity("功能介绍", R.mipmap.icon_tab_annc_normal, R.mipmap.icon_tab_annc_press),
                mainTabLayout.new Entity("相关公告", R.mipmap.icon_tab_intro_normal, R.mipmap.icon_tab_intro_press),
                mainTabLayout.new Entity("关于我们", R.mipmap.icon_tab_about_normal, R.mipmap.icon_tab_about_press)});


    }

    @Override
    public void initEvent() {
        super.initEvent();
        mainTabLayout.setOnSelectedChangedLsn(new TabLayout.onSelectedChangedLsn() {
            @Override
            public void onSelected(int position) {
                if (position == tempFragmentPosition) {
                    return;
                }
                Fragment from = null;
                Fragment to = fragments[position];
                if (tempFragmentPosition >= 0) {
                    from = fragments[tempFragmentPosition];
                }
                tempFragmentPosition = position;
                FragmentUtils.showHideFragment(getSupportFragmentManager(), to, from);
            }

            @Override
            public void onUnSelected(int position) {
            }
        });
        if (savedInstanceState == null) {
            mainTabLayout.setSelected(0);// after set selected lsn
        }
    }
}
