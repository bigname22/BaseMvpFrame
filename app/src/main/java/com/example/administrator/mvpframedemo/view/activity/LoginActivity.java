package com.example.administrator.mvpframedemo.view.activity;


import com.example.administrator.mvpframedemo.R;
import com.example.administrator.mvpframedemo.base.BaseMvpActivity;
import com.example.administrator.mvpframedemo.constract.LoginContract;
import com.example.administrator.mvpframedemo.presenter.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginContract.LoginPresenter> implements LoginContract.LoginView {


    @Override
    protected void initBeforeCreate() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void logic() {
        mPresenter.login("bigname", "123");
    }

    @Override
    public void showTips(String str) {
        showToast(str);
    }

    @Override
    protected LoginContract.LoginPresenter bindPresenter() {
        return new LoginPresenter();
    }
}
