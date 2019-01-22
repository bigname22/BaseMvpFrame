package com.example.administrator.mvpframedemo.view.activity;

import android.os.Bundle;

import com.example.administrator.mvpframedemo.R;
import com.example.administrator.mvpframedemo.base.BaseActivity;
import com.example.administrator.mvpframedemo.base.BasePresenter;

public class MainActivity extends BaseActivity {

    BasePresenter p;

    @Override
    protected void initBeforeCreate() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        p = new BasePresenter() {};
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void logic() {

    }
}
