package com.baobing.cc.basemvpframemaster01.other.util;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

/**
 * author:Created by LiangSJ
 * date: 2017/7/19.
 * description:页面跳转工具类
 */

public class ActSkipUtil {
    public static void skipActivity(Context fromAct, Class<?> toAct) {
        skipActivity(fromAct, toAct, "", "");
    }

    public static void skipActivity(Context fromAct, Class<?> toAct, String key, String value) {
        Intent intent = new Intent(fromAct, toAct);
        if (key != null && value != null && key.length() > 0 && value.length() > 0) {
            intent.putExtra(key, value);
        }
        skipActivity(fromAct, intent);
    }

    public static void skipActivity(Context fromAct, Class<?> toAct, String key, int value) {
        Intent intent = new Intent(fromAct, toAct);
        if (key != null) {
            intent.putExtra(key, value);
        }
        skipActivity(fromAct, intent);
    }

    public static void skipActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static void skipActivity(Context fromAct, Class<?> toAct, String[] keys, String[] values) {
        Intent intent = new Intent(fromAct, toAct);
        for (int i = 0; i < keys.length; i++) {
            intent.putExtra(keys[i], values[i]);
        }
        skipActivity(fromAct, intent);
    }

    public static void skipActivity(Context fromAct, Class<?> toAct, String key1, String value1, String key2, int value2) {
        Intent intent = new Intent(fromAct, toAct);
        intent.putExtra(key1, value1);
        intent.putExtra(key2, value2);
        skipActivity(fromAct, intent);
    }

    public static void skipActivity(Context fromeAct, Class<?> toAct, String key1,int value1,String key2, Serializable value2) {
        Intent intent = new Intent(fromeAct, toAct);
        intent.putExtra(key1, value1);
        intent.putExtra(key2, value2);
        skipActivity(fromeAct,intent);
    }

    public static void skipActivity(Context fromAct, Class<?> toAct, String key, Serializable serializable) {
        Intent intent = new Intent(fromAct, toAct);
        intent.putExtra(key, serializable);
        skipActivity(fromAct, intent);
    }
}
