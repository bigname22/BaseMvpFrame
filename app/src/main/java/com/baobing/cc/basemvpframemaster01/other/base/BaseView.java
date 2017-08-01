package com.baobing.cc.basemvpframemaster01.other.base;

/**
 * author:Created by LiangSJ
 * date: 2017/5/31.
 * description:IView基类
 */

public interface BaseView<T> {
    //绑定presenter
    void setPresenter(T presenter);

    void showTips(String tips);
}
