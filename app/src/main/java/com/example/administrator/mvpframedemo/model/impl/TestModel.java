package com.example.administrator.mvpframedemo.model.impl;

import android.util.Log;

import com.example.administrator.mvpframedemo.domain.NewsDomain;
import com.example.administrator.mvpframedemo.model.inter.ITest;
import com.example.administrator.mvpframedemo.other.net.Api;
import com.example.administrator.mvpframedemo.other.net.RetrofitUtil;

import io.reactivex.Observable;


public class TestModel implements ITest {
    private static final String TAG = "bigname";

    @Override
    public Observable<NewsDomain> test() {
        RetrofitUtil retrofitUtil = RetrofitUtil.newInstance();
        Api api = retrofitUtil.createApi(Api.class);
        return api.getNews("top", "dc4fed8fb5fb46ac78e51d66306e6762");
    }
}
