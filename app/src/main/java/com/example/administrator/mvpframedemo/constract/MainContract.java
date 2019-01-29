package com.example.administrator.mvpframedemo.constract;

import com.example.administrator.mvpframedemo.base.BasePresenter;
import com.example.administrator.mvpframedemo.base.IBaseView;

public interface MainContract {
    abstract class MainPresenter extends BasePresenter<MainView>{

        public abstract void login(String name, String password);

    }

    interface MainView extends IBaseView {
        void showTips(String str);
    }
}
