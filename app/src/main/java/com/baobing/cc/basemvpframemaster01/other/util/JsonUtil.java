package com.baobing.cc.basemvpframemaster01.other.util;

import com.google.gson.Gson;


/**
 * author:Created by LiangSJ
 * date: 2017/6/1.
 * description:gson解析、生成工具
 */

public class JsonUtil {
    /*
    * desc:json转APIModel
    * */
    public static <T> T json2Object(String json, Class<T> C) {
        return new Gson().fromJson(json, C);
    }


}
