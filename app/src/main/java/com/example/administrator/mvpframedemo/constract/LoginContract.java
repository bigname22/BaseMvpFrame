package com.example.administrator.mvpframedemo.constract;

import com.example.administrator.mvpframedemo.base.BasePresenter;
import com.example.administrator.mvpframedemo.base.IBaseView;

public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter<LoginView>{

        public abstract void login(String name, String password);

    }

    interface LoginView extends IBaseView {
        void showTips(String str);
    }
}
