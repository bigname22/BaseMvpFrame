package com.example.administrator.mvpframedemo.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.mvpframedemo.R;
import com.example.administrator.mvpframedemo.base.BaseActivity;
import com.example.administrator.mvpframedemo.base.BaseFragment;
import com.example.administrator.mvpframedemo.base.BaseMvpActivity;
import com.example.administrator.mvpframedemo.base.BasePresenter;
import com.example.administrator.mvpframedemo.constract.LoginContract;
import com.example.administrator.mvpframedemo.constract.MainContract;
import com.example.administrator.mvpframedemo.other.adapter.MainVpAdapter;
import com.example.administrator.mvpframedemo.presenter.LoginPresenter;
import com.example.administrator.mvpframedemo.presenter.MainPresenter;
import com.example.administrator.mvpframedemo.view.fragment.main.EmptyRoomFragment;
import com.example.administrator.mvpframedemo.view.fragment.main.MainShowFragment;
import com.example.administrator.mvpframedemo.view.fragment.main.MessageFragment;
import com.example.administrator.mvpframedemo.view.fragment.main.MineFragment;
import com.example.administrator.mvpframedemo.view.fragment.main.NearSchoolFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainContract.MainPresenter> implements MainContract.MainView, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.bottomBar)
    BottomNavigationView mBb;

    /*
    *   List Fragment
    * */
    List<BaseFragment> mFragments;

    /*
    *   ViewPager adapter
    * */
    MainVpAdapter mVpAdapter;

    /*
    *   bottombar arr
    * */
    int bottomArr[] = {
            R.id.bar_show,
            R.id.bar_empty_school,
            R.id.bar_message,
            R.id.bar_near_school,
            R.id.bar_mine,
    };



    @Override
    protected void initBeforeCreate() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new MainShowFragment());
        mFragments.add(new EmptyRoomFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new NearSchoolFragment());
        mFragments.add(new MineFragment());

        mVpAdapter = new MainVpAdapter(getSupportFragmentManager(),mFragments);
    }

    @Override
    protected void initView() {
        mVp.setAdapter(mVpAdapter);
        mVp.addOnPageChangeListener(this);
        mBb.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void logic() {
        mPresenter.login("11111", "2222222");
    }

    /*
    *   底部导航栏切换时-联动ViewPager
    * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        for(int i=0; i<bottomArr.length; i++) {
            if(bottomArr[i] == menuItem.getItemId()) {
                mVp.setCurrentItem(i, true);
            }
        }
        return true;
    }

    /*
    *   监听ViewPager切换 联动bottombar
    * */
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int vpSelectIndex) {
        mBb.setSelectedItemId(bottomArr[vpSelectIndex]);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void showTips(String str) {
        showToast(str);
    }
}
