package com.example.administrator.mvpframedemo.other.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
*   静态模式
* */

public class RetrofitUtil {

    /*
     *   BaseUrl
     * */
    private final static String BASE_URL = "http://v.juhe.cn/";

    private static Retrofit mRetrofit;

    private static RetrofitUtil mRetrofitUtil;

    static {
        mRetrofitUtil = new RetrofitUtil();
        initRetrofit();
    }

    private RetrofitUtil () {
    }

    public static RetrofitUtil newInstance() {
        return mRetrofitUtil;
    }

    /*
    *   初始化网络设置
    * */
    private static void initRetrofit () {
        // 设置okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.SECONDS)//设置超时时间
                .retryOnConnectionFailure(true);
        //添加拦截
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        builder.addInterceptor(logInterceptor);
        OkHttpClient client = builder.build();
        //设置retrofit
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /*
    *   创建api
    * */
    public <T> T createApi (Class<T> cla) {
        return mRetrofit.create(cla);
    }


}
