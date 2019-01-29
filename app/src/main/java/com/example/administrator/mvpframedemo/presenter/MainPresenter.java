package com.example.administrator.mvpframedemo.presenter;

import android.util.Log;

import com.example.administrator.mvpframedemo.constract.MainContract;
import com.example.administrator.mvpframedemo.domain.NewsDomain;
import com.example.administrator.mvpframedemo.model.impl.TestModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends MainContract.MainPresenter {
    private static final String TAG = "bigname";

    @Override
    public void login(String name, String password) {
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
}
