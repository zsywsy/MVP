package com.zsy.mvp1.ui.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zsy.mvp1.R;
import com.zsy.mvp1.app.AppContext;
import com.zsy.mvp1.bean.HttpData;
import com.zsy.sum.app.BaseActivity;
import com.zsy.sum.ui.view.TopBar;
import com.zsy.sum.utils.IterateIntent;
import com.zsy.sum.utils.PromptUtils;
import com.zsy.sum.utils.SpUtils;
import com.zsy.sum.utils.depend.Lg;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 24275 on 2016/10/14.
 */

public class RegisterActivity extends BaseActivity implements RegisterContact.View {


    private boolean isRegisterBtnEnabled = false;

    @Inject RegisterPresenter presenter;

    @BindView(R.id.tb_register) TopBar registerTb;
    @BindView(R.id.et_account) EditText accountEt;
    @BindView(R.id.et_pwd) EditText pwdEt;
    @BindView(R.id.btn_register) Button registerBtn;

    private MaterialDialog materialDialog;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initInjector() {
        super.initInjector();
        DaggerRegisterComponent
                .builder().
                appComponent(AppContext.getInstance().getAppComponent())
                .build()
                .inject(this);
        presenter.attachView(this);
    }

    @OnClick(R.id.btn_register)
    void register() {
        presenter.register(accountEt.getText().toString(), pwdEt.getText().toString());
    }

    @Override
    public void initData() {
        super.initData();
        Lg.i(IterateIntent.iterateIntent(getIntent(), new StringBuilder()).toString());
    }

    @Override
    public void initView() {
        super.initView();
        materialDialog = new MaterialDialog.Builder(this)
                .title("提示")
                .content("注册中")
                .progress(true, 0)
                .build();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        registerTb.setTopbarOnClickListener(new TopBar.LeftOnClickListener() {
            @Override
            public void leftOnClick(View v) {
                finish();
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Lg.i("s");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Lg.i("s");
            }

            @Override
            public void afterTextChanged(Editable s) {
                int accountLen = accountEt.length();
                int pwdLen = pwdEt.length();
                Lg.i("account:" + accountLen + ":" + pwdLen);
                if (accountLen == 11 && pwdLen >= 6 && pwdLen <= 20) {
                    if (!isRegisterBtnEnabled) {
                        registerBtn.setEnabled(true);
                        isRegisterBtnEnabled = true;
                    }
                } else if (isRegisterBtnEnabled) {
                    registerBtn.setEnabled(false);
                    isRegisterBtnEnabled = false;
                }
            }
        };
        accountEt.addTextChangedListener(textWatcher);
        pwdEt.addTextChangedListener(textWatcher);
        materialDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                presenter.cancelRegister();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        materialDialog.dismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Intent intent = new Intent();
//            intent.putExtra("name", "mzs");
//            setResult(RESULT_OK, intent);
//            finish();
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showLoading() {
        if (materialDialog != null) {
            materialDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (materialDialog != null) {
            materialDialog.hide();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Lg.e(throwable.getMessage());
        PromptUtils.toast(this, "注册异常，请检查网络状况");
    }

    @Override
    public void onFailure(HttpData<String> httpData) {
        Lg.i(httpData.getMsg());
        PromptUtils.toast(this, httpData.getMsg());
    }

    @Override
    public void onSuccess(HttpData<String> httpData) {
        Lg.e(httpData.getMsg());
        PromptUtils.toast(this, "注册成功");
        Intent intent = new Intent();
        intent.putExtra("account", accountEt.getText().toString());
//        SpUtils.putAccount(this, accountEt.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
