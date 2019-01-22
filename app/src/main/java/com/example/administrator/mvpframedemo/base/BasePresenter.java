package com.example.administrator.mvpframedemo.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends IBaseView>  {

    private WeakReference<V> mViewRef;
    public V mView;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
        mView = mViewRef.get();
    }

    public void detachView() {
        mViewRef.clear();
        mView = null;
    }
}
