package com.example.administrator.mvpframedemo.model.inter;

import com.example.administrator.mvpframedemo.domain.LoginDomain;
import com.example.administrator.mvpframedemo.other.inter.ICallBack;

public interface ILoginModel {
    void login(String username, String password, ICallBack<LoginDomain, Exception> callBack);
}
