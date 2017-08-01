package com.baobing.cc.basemvpframemaster01.other.base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author:Created by LiangSJ
 * date: 2017/5/27.
 * description : activity基类
 */

public abstract class  BaseActivity extends AppCompatActivity {
    private int resId;
    private Unbinder binder;
    public final static int SHOW_TIP = 1000000;
    public static final int CLOSE_PROGRESS = 10000001;
    public static final int OPEN_PROGRESS = 10000002;
    private ProgressDialog progressDialog;

    public Handler getUiHandler() {
        return uiHandler;
    }

    public void setUiHandler(Handler uiHandler) {
        this.uiHandler = uiHandler;
    }

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_TIP:
                    Toast.makeText(BaseActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case CLOSE_PROGRESS:
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    break;
                case OPEN_PROGRESS:
                    if (progressDialog == null) {
                        progressDialog = new ProgressDialog(BaseActivity.this);
                    }
                    progressDialog.show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resId = onLoadLayout();
        setContentView(resId);
        binder = ButterKnife.bind(this);
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        init(savedInstanceState);
        initData();
        findViews();
        setting();
        logic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    /*
     * desc：返回resid设置视图
     * */
    protected abstract int onLoadLayout();

    /*
    * desc:配置初始化
    * */
    protected abstract void init(Bundle savedInstanceState);

    /*
    * 数据初始化
    * */
    protected abstract void initData();

    /*
    * 查找控件
    * */
    protected abstract void findViews();

    /*
    * 设置
    * */
    protected abstract void setting();

    /*
    * 逻辑处理
    * */
    protected abstract void logic();


    /*
    * 初始化toolbar
    * */
    protected void initToolBar(Toolbar toolbar, String title) {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(android.R.drawable.checkbox_off_background);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this, "返回", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    /*
    *  显示进度框
    * */
    public void showProgress() {
        Message obtain = Message.obtain();
        obtain.what = OPEN_PROGRESS;
        uiHandler.sendMessage(obtain);
    }

    /*
    *  隐藏进度框
    * */
    public void closeProgress() {
        Message obtain = Message.obtain();
        obtain.what = CLOSE_PROGRESS;
        uiHandler.sendMessage(obtain);
    }

    /*
    *  判断是否在主线程
    * */
    public boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /*
    * 创建message
    * */
    public Message obtainMessage(int what,Object obj){
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = obj;
        return msg;
    }
}
