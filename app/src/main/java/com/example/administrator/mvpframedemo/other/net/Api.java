package com.example.administrator.mvpframedemo.other.net;

import com.example.administrator.mvpframedemo.domain.NewsDomain;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    //http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    @GET("/toutiao/index")
    Observable<NewsDomain> getNews (@Query("type")String type, @Query("key")String apiKey);

}
