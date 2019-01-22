package com.example.administrator.mvpframedemo.other.inter;

public interface ICallBack<S, F> {

    void onSuccess(S result);

    void onFail(F error);

}
