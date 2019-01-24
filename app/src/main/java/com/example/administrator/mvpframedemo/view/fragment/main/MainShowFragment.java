package com.example.administrator.mvpframedemo.view.fragment.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mvpframedemo.R;
import com.example.administrator.mvpframedemo.base.BaseFragment;

/**
 *  desc: Fragment的使用：
 *          第一步：绑定布局
 *          第二步：初始化数据
 *          第三步：布局绑定数据
 * */

public class MainShowFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_show,container, false);
        return view;
    }
}
