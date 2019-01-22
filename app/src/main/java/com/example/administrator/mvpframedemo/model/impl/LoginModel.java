package com.example.administrator.mvpframedemo.model.impl;

import com.example.administrator.mvpframedemo.domain.LoginDomain;
import com.example.administrator.mvpframedemo.model.inter.ILoginModel;
import com.example.administrator.mvpframedemo.other.inter.ICallBack;

public class LoginModel implements ILoginModel {

    @Override
    public void login(String username, String password, ICallBack<LoginDomain, Exception> callBack) {
        // 模拟一部网络获取
        try {
            Thread.sleep(2000);
            callBack.onSuccess(new LoginDomain("周润发","123"));
        } catch (InterruptedException e) {
            e.printStackTrace();
            callBack.onFail(new Exception());
        }
    }
}
