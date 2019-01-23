package com.example.administrator.mvpframedemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;

import com.example.administrator.mvpframedemo.R;
import com.example.administrator.mvpframedemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestRecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rv_test)
    RecyclerView mRv;

    List<String> mData;
    RvAdapter mAdapter;

    @Override
    protected void initBeforeCreate() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_recycler_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mData = new ArrayList<String>();
        for (int i=0; i< 100; i++) {
            String str = "Hi" + (i+1);
            if(i%9 == 0) {
                str+= "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
            }
            mData.add(str);
        }
    }

    @Override
    protected void initView() {
        mAdapter = new RvAdapter(mData);
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        RecyclerView.LayoutManager lm = new GridLayoutManager(this,2);
//        RecyclerView.LayoutManager lm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRv.setLayoutManager(lm);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void logic() {

    }
}
