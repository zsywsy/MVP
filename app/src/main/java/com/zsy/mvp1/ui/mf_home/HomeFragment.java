package com.zsy.mvp1.ui.mf_home;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zsy.mvp1.LoadRecyclerView;
import com.zsy.mvp1.R;
import com.zsy.sum.app.BaseFragment;
import com.zsy.sum.utils.IterateIntent;
import com.zsy.sum.utils.depend.Lg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;


/**
 * Created by 24275 on 2016/10/10.
 */

public class HomeFragment extends BaseFragment {

    Handler handler = new Handler();

    private List<String> data;
    private HomeRvAdapter homeRvAdapter;

    @BindView(R.id.srl_home) SwipeRefreshLayout homeSrl;
    @BindView(R.id.rv_home) LoadRecyclerView homeRv;

    @Override
    public void init() {
//        super.init();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
        if (nfcAdapter == null) {
            Log.i("LgTag", "设备不支持NFC！");

        } else if (!nfcAdapter.isEnabled()) {
            Log.i("LgTag", "请在设置中打开NFC功能！");
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivityForResult(intent, 123);
//            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Lg.e("requestCode:" + requestCode + "; resultCode:" + resultCode);
        Lg.e("data:" + IterateIntent.iterateIntent(data, new StringBuilder()));
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.fragment_mf_home);
    }

    @Override
    public void initData() {
        super.initData();
        data = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            data.add("item:" + System.currentTimeMillis());
//        }
        homeRvAdapter = new HomeRvAdapter(data);

    }

    @Override
    public void initView() {
        super.initView();
        homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRv.setAdapter(homeRvAdapter);
        homeSrl.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        homeSrl.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        homeSrl.post(new Runnable() {
            @Override
            public void run() {
                homeSrl.setRefreshing(true);
            }
        });

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.clear();
                for (int i = 20; i < 40; i++) {
                    data.add("item:" + System.currentTimeMillis());
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        homeRv.getAdapter().notifyDataSetChanged();
                        homeSrl.setRefreshing(false);
                    }
                });
            }
        }.start();


    }


    @Override
    public void initEvent() {
        super.initEvent();
        homeSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 20; i < 40; i++) {
                            data.add("item:" + System.currentTimeMillis());
                        }
                        homeRv.getAdapter().notifyDataSetChanged();
                        homeSrl.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
}
