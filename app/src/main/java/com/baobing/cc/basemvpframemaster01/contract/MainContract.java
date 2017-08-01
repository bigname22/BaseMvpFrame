package com.baobing.cc.basemvpframemaster01.contract;


import com.baobing.cc.basemvpframemaster01.other.base.BaseActivity;
import com.baobing.cc.basemvpframemaster01.other.base.BasePresenter;
import com.baobing.cc.basemvpframemaster01.other.base.BaseView;



/**
 * author:Created by LiangSJ
 * date: 2017/5/31.
 * description:主页面合同
 */

public interface MainContract {
    interface IView extends BaseView<IPresenter> {
    }

    interface IPresenter extends BasePresenter {
        void start(BaseActivity context);
    }
}
