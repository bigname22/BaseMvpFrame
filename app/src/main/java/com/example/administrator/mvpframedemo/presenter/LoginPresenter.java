package com.example.administrator.mvpframedemo.presenter;

import com.example.administrator.mvpframedemo.constract.LoginContract;
import com.example.administrator.mvpframedemo.domain.LoginDomain;
import com.example.administrator.mvpframedemo.model.impl.LoginModel;
import com.example.administrator.mvpframedemo.model.inter.ILoginModel;
import com.example.administrator.mvpframedemo.other.inter.ICallBack;

public  class LoginPresenter extends LoginContract.LoginPresenter {
    ILoginModel loginModel;
    @Override
    public void login(String name, String password) {
        loginModel = new LoginModel();
        loginModel.login(name, password, new LoginCallBack());
    }

    private class LoginCallBack implements ICallBack<LoginDomain, Exception> {

        @Override
        public void onSuccess(LoginDomain result) {
            mView.showTips("登录成功");
        }

        @Override
        public void onFail(Exception error) {
            mView.showTips("登录失败");
        }
    }
}
