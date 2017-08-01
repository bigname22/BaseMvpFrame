package com.baobing.cc.basemvpframemaster01.view.activity;

import android.os.Bundle;

import com.baobing.cc.basemvpframemaster01.R;
import com.baobing.cc.basemvpframemaster01.contract.MainContract;
import com.baobing.cc.basemvpframemaster01.other.base.BaseActivity;


public class MainActivity extends BaseActivity implements MainContract.IView{

    @Override
    protected int onLoadLayout() {
        return R.layout.activity_main;
    }



    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setting() {

    }

    @Override
    protected void logic() {

    }

    @Override
    public void setPresenter(MainContract.IPresenter presenter) {

    }

    @Override
    public void showTips(String tips) {

    }
}
