package com.zsy.test.dialog;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.zsy.sum.app.BaseActivity;
import com.zsy.sum.utils.depend.Lg;
import com.zsy.test.R;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogActivity extends BaseActivity {


    private AlertDialog alertDialog;


    @BindView(R.id.btn_new_d) Button newDBtn;
    @BindView(R.id.btn_re_d) Button reDBtn;

    @BindView(R.id.btn_new_fd) Button newFdBtn;
    @BindView(R.id.btn_re_fd) Button reFdBtn;

    @BindView(R.id.btn_new_fv) Button newFvBtn;
    @BindView(R.id.btn_re_fv) Button reFvBtn;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        super.initView();
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("newTitle")
                .setMessage("newMsg")
                .setPositiveButton("ok", null)
                .create();
    }

    @OnClick({R.id.btn_new_d, R.id.btn_re_d, R.id.btn_new_fd, R.id.btn_re_fd, R.id.btn_new_fv, R.id.btn_re_fv})
    void doClick(View v) {
        Lg.d("v:" + v.getId());
        switch (v.getId()) {
            case R.id.btn_new_d:
                new AlertDialog.Builder(this)
                        .setTitle("newTitle")
                        .setMessage("newMsg")
                        .setPositiveButton("ok", null)
                        .create()
                        .show();
                break;
            case R.id.btn_re_d:
                alertDialog.show();
                break;
            case R.id.btn_new_fd:
                break;
            case R.id.btn_re_fd:
                break;
            case R.id.btn_new_fv:
                break;
            case R.id.btn_re_fv:
                break;
        }
    }
}
