package com.baobing.cc.basemvpframemaster01.other.util;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * author:Created by LiangSJ
 * date: 2017/6/28.
 * description：动画工具类
 */

public class AnimationUtil {
    /*
    * 使某个控件往右边收缩(toolbar,searchview,800)
    * */
    public static void toOpacity(final View view, final View underView, final int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(true);
        view.startAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                underView.setVisibility(View.VISIBLE);
                ScaleAnimation a = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f);
                a.setDuration(duration);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                underView.setAnimation(a);
                a.startNow();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
//                scaleAnimation.setDuration(duration);
//                view.setAnimation(scaleAnimation);
//                scaleAnimation.startNow();
                Log.d("bigname", "onAnimationEnd: ");
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
