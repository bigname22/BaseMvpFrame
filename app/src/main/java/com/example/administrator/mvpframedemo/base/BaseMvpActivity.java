package com.example.administrator.mvpframedemo.base;

import android.os.Bundle;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IBaseView {

    protected T mPresenter;

    @Override
    protected void init(Bundle savedInstanceState) {
        mPresenter = bindPresenter();
        mPresenter.attachView(this);
    }

    protected abstract T bindPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
