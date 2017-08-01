package com.baobing.cc.basemvpframemaster01.presenter;


import com.baobing.cc.basemvpframemaster01.contract.MainContract;
import com.baobing.cc.basemvpframemaster01.other.base.BaseActivity;

/**
 * author:Created by LiangSJ
 * date: 2017/5/31.
 * description:主页presenter
 */

public class MainPresenter implements MainContract.IPresenter {
    private MainContract.IView mainView;

    public MainPresenter(MainContract.IView iView) {
        this.mainView = iView;
        mainView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    /*
    * ？？同时获取多个数据这样的代码不清晰
    * */
    @Override
    public void start(final BaseActivity context) {

    }
}
