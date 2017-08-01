package com.baobing.cc.basemvpframemaster01.other.util;

/**
 * author:Created by LiangSJ
 * date: 2017/7/19.
 * description:输入检测工具
 */

public class InputCheckUtil {
    /*
    * 检测非空，长度为0,true为输入长度大于0
    * true：正常
    * */
    public static boolean isNullOrZero(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() == 0) {
            return false;
        }
        return true;
    }

    /*
    * 检测字符是否为指定长度
    * */
    public static boolean isMatch(String str, int specifyLength) {
        if (str == null) {
            return false;
        }
        if (str.length() == specifyLength) {
            return true;
        }
        return false;
    }

    /*
    *  检测多字符串 非空 长度不为0
    * */
    public static boolean isNullOrZeros(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (!isNullOrZero(strings[i])){
                return false;
            }
        }
        return true;
    }
}
