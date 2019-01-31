package com.example.administrator.mvpframedemo.other.util;

import android.content.Context;

public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param scale（DisplayMetrics类中属性density）
     * @return
     */
    public static float px2dip(float pxValue, float scale) {
        return  (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param context
     * @return
     */
    public static float px2dip(float pxValue,Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return  px2dip( pxValue,  scale);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param scale（DisplayMetrics类中属性density）
     * @return
     */
    public static float dip2px(float dipValue, float scale) {
        return  (dipValue * scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param context
     * @return
     */
    public static float dip2px(float dipValue, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dip2px(dipValue, scale);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static float px2sp(float pxValue, float fontScale) {
        return  (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static float sp2px(float spValue, float fontScale) {
        return  (spValue * fontScale + 0.5f);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context
     * @return
     */
    public static float sp2px(float spValue,Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return  sp2px( spValue,  scale);
    }
}
