package com.baobing.cc.basemvpframemaster01.other.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * author:Created by LiangSJ
 * date: 2017/6/3.
 * description : fragment基类
 */

public abstract class BaseFragment extends SupportFragment {

    protected View rootView;

    public Context mContext;
    protected AppCompatActivity mActivity;

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getContext();
        mActivity = (AppCompatActivity) this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (onLoadLayout() != 0) {
            rootView = inflater.inflate(onLoadLayout(), container, false);
        } else {
            throw new IllegalArgumentException("错误");
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        init();
        logic();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*
    * 设置布局资源
    * */
    protected abstract int onLoadLayout();

    /*
    * 初始化：数据设置等
    * */
    protected abstract void init();

    /*
    * 执行逻辑
    * */
    protected abstract void logic();

}
