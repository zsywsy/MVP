package com.zsy.mvp1.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zsy.mvp1.R;
import com.zsy.mvp1.app.AppContext;
import com.zsy.mvp1.bean.HttpData;
import com.zsy.mvp1.ui.main.MainActivity;
import com.zsy.mvp1.ui.register.RegisterActivity;
import com.zsy.sum.app.BaseActivity;
import com.zsy.sum.ui.view.TopBar;
import com.zsy.sum.utils.ActUtils;
import com.zsy.sum.utils.EventFilter;
import com.zsy.sum.utils.IterateIntent;
import com.zsy.sum.utils.PromptUtils;
import com.zsy.sum.utils.depend.Lg;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements LoginContact.View {

    public static final int REGISTER_CODE = 0x10;

    private boolean isLoginBtnEnabled = false;

    @Inject LoginPresenter presenter;

    @BindView(R.id.tb_login) TopBar loginTb;
    @BindView(R.id.et_account) EditText accountEt;
    @BindView(R.id.et_pwd) EditText pwdEt;
    @BindView(R.id.btn_login) Button loginBtn;


    private MaterialDialog materialDialog;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initInjector() {
        super.initInjector();
        DaggerLoginComponent
                .builder()
                .appComponent(AppContext.getInstance().getAppComponent())
                .build()
                .inject(this);
        presenter.attachView(this);
    }


    @OnClick(R.id.btn_login)
    void login() {
        if (EventFilter.isFastClick()) {
            return;
        }
        presenter.login(accountEt.getText().toString(), pwdEt.getText().toString());
    }

    @Override
    public void initView() {
        super.initView();
        materialDialog = new MaterialDialog.Builder(this)
                .title("提示")
                .content("登陆中")
                .progress(true, 0)
                .build();
        presenter.setAccount();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        loginTb.setTopbarOnClickListener(new TopBar.TopbarOnClickListener() {
            @Override
            public void leftOnClick(android.view.View v) {
                ActUtils.act2Act(LoginActivity.this, MainActivity.class);
                finish();
            }

            @Override
            public void rightOnClick(android.view.View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REGISTER_CODE);
//                ActUtils.act2Act(LoginActivity.this, RegisterActivity.class);
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
                    if (!isLoginBtnEnabled) {
                        loginBtn.setEnabled(true);
                        isLoginBtnEnabled = true;
                    }
                } else if (isLoginBtnEnabled) {
                    loginBtn.setEnabled(false);
                    isLoginBtnEnabled = false;
                }
            }
        };
        accountEt.addTextChangedListener(textWatcher);
        pwdEt.addTextChangedListener(textWatcher);
        materialDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                presenter.cancelLogin();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Lg.i("requestCode:" + requestCode + ";resultCode:" + resultCode);
        Lg.e("data:" + IterateIntent.iterateIntent(data, new StringBuilder()));
        String account;
        if (requestCode == REGISTER_CODE && resultCode == RESULT_OK && data != null && (account = data.getStringExtra("account")) != null) {
            accountEt.setText(account);
        }
    }

    @Override
    public void setAccount(String account) {
        if (account != null && account.length() > 0) {
            accountEt.setText(account);
        }
    }

    @Override
    public void showLoading() {
        Lg.i("showLoading");
        if (materialDialog != null) {
            materialDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        Lg.i("hideLoading");
        if (materialDialog != null) {
            materialDialog.hide();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Lg.e(throwable.getMessage());
        PromptUtils.toast(this, "登录异常，请检查网络状况");
    }

    @Override
    public void onFailure(HttpData<String> httpData) {
        PromptUtils.toast(this, httpData.getMsg());
    }

    @Override
    public void onSuccess(HttpData<String> httpData) {
        PromptUtils.toast(this, "登陆成功");
        ActUtils.act2Act(this, MainActivity.class);
        finish();
    }
}
