package com.example.administrator.mvpframedemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initBeforeCreate();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init(savedInstanceState);
        initData();
        initView();
        logic();
    }

    protected abstract void initBeforeCreate();

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void logic();

    protected void showToast (String toastStr) {
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    };


}
