package com.example.administrator.mvpframedemo.model.inter;

import com.example.administrator.mvpframedemo.domain.NewsDomain;

import io.reactivex.Observable;

public interface ITest {

    Observable<NewsDomain> test();

}
