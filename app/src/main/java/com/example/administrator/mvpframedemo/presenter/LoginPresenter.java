package com.example.administrator.mvpframedemo.presenter;

import android.util.Log;

import com.example.administrator.mvpframedemo.constract.LoginContract;
import com.example.administrator.mvpframedemo.domain.LoginDomain;
import com.example.administrator.mvpframedemo.domain.NewsDomain;
import com.example.administrator.mvpframedemo.model.impl.TestModel;
import com.example.administrator.mvpframedemo.model.inter.ILoginModel;
import com.example.administrator.mvpframedemo.other.inter.ICallBack;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public  class LoginPresenter extends LoginContract.LoginPresenter {
    private static final String TAG = "bigname";
    ILoginModel loginModel;
    @Override
    public void login(String name, String password) {
//        loginModel = new LoginModel();
//        loginModel.login(name, password, new LoginCallBack());

        TestModel testModel = new TestModel();
        Observable<NewsDomain> newsDomainObservable = testModel.test();
        newsDomainObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsDomain>() {
                    @Override
                    public void onNext(NewsDomain newsDomain) {
                        Log.d(TAG, "onNext: " +  newsDomain.toString());
                        mView.showTips(newsDomain.getReason());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private class LoginCallBack implements ICallBack<LoginDomain, Exception> {

        @Override
        public void onSuccess(LoginDomain result) {
            mView.showTips("登录成功");
        }

        @Override
        public void onFail(Exception error) {
            mView.showTips("登录失败");
        }
    }
}
