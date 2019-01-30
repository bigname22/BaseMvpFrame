package com.example.administrator.mvpframedemo.other.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.TtsSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mvpframedemo.R;

/*
*   自定义View：可以用代码创建布局也可以加载布局，通过xml文件来读取参数
*   loadingView实现步骤：
*       1.定义参数
*       2.运行view动画
*       3.隐藏动画
* */

public class LoadingView extends LinearLayout {

    private ImageView mIv;
    private int imgResIds[];
    private Context mContext;

    private Paint mShadowPaint;
    private RectF mShadowRectF;
    private float mShadowFlatX = 0.8f;
    private float mShadowFlatY = 0.4f;

    private int mIvHeight;
    private int mIvWidth;
    private int mIvTop;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;

    /*
    *   image当前高度离完整高度的百分比
    * */
    private float mMovePercent;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        this.init();
        this.initView();
        this.logic();
    }

    private void init() {
        mShadowPaint = new Paint();
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setColor(Color.GRAY);
        mShadowRectF = new RectF();
    }


    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.item_loading_view, this);
        mIv = ((ImageView) findViewById(R.id.iv_loading));
//        mIvWidth = mIv.getMeasuredWidth();
//        mIvHeight = mIv.getMeasuredHeight();
        mIvWidth = 48;
        mIvHeight = 48;
    }

    private void logic() {
        ValueAnimator animator = ValueAnimator.ofInt(100,200,100);
        animator.setDuration(1200);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        ViewGroup.LayoutParams lp = mIv.getLayoutParams();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mIv.setTranslationY(-value);
                // 为了画shadow
                postInvalidate();
                mMovePercent = value / 100f;
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int ivHalfWidth = (mIvRight - mIvLeft) / 2;
        int ivHalfHeight = (mIvBottom - mIvTop) / 2;
        float yCenter = mIvLeft + ivHalfWidth;
        float xCenter = mIvTop + ivHalfHeight;
        float horizontalDiff = (1f - mMovePercent) * ivHalfWidth * mShadowFlatX;
        float verticalDiff = (1f - mMovePercent) * ivHalfHeight * mShadowFlatY;
        Log.d("bigname", "onDraw: " + yCenter + "-----" + xCenter + "------" + horizontalDiff + "--------" + verticalDiff + "-------" + mMovePercent);
        mShadowRectF.set(
                yCenter - horizontalDiff,
                xCenter - verticalDiff,
                yCenter + horizontalDiff,
                xCenter + verticalDiff
        );
        canvas.drawOval(mShadowRectF, mShadowPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mIvTop = mIv.getTop();
        mIvBottom = mIv.getBottom();
        mIvLeft = mIv.getLeft();
        mIvRight = mIv.getRight();
    }

}
