package com.example.administrator.mvpframedemo.other.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.mvpframedemo.base.BaseFragment;

import java.util.List;

public class MainVpAdapter extends FragmentPagerAdapter {

    List<BaseFragment> mFragments;

    public MainVpAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
