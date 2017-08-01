package com.baobing.cc.basemvpframemaster01.other.util;

import com.baobing.cc.basemvpframemaster01.other.exception.ParamsException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by LiangSJ
 * date: 2017/6/1.
 * description:网络请求工具
 */

public class OkHttpUtil {

    public static void connectUrl(String url,IConnectResult iConnectResult){
        connectUrl(url,null,null,iConnectResult);
    }

    public static void connectUrl(String url, String[] keys, String[] values, final IConnectResult iConnectResult) {
        FormBody.Builder formBuider = new FormBody.Builder();
        Request.Builder requestBuilder = new Request.Builder();
        FormBody formBody = null;
        Request request = null;
        if (keys!=null&&values!=null) {
            if (keys.length != values.length) {
                throw new ParamsException("参数键值长度不一致");
            }
            for (int i = 0; i < keys.length; i++) {
                formBuider.add(keys[i],values[i]);
            }
            formBody = formBuider.build();
            requestBuilder.post(formBody);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(HelperUtil.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .build();
        request = requestBuilder.url(url).build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (iConnectResult != null) {
                        iConnectResult.onFail(e.getMessage());
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (iConnectResult != null) {
                        iConnectResult.onSuccess(response);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 接口响应
    * */
    public interface IConnectResult{
        void onSuccess(Response response) throws IOException;
        void onFail(String errorTips);
    }
}
