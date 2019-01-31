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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.mvpframedemo.R;
import com.example.administrator.mvpframedemo.other.util.DisplayUtil;

/*
*   自定义View：可以用代码创建布局也可以加载布局，通过xml文件来读取参数
*   loadingView实现步骤：
*       1.定义参数
*       2.运行view动画
*       3.隐藏动画
* */

public class LoadingView extends FrameLayout {

    private ImageView mIv;
    private int imgResIds[];
    private Context mContext;

    private Paint mShadowPaint;
    private RectF mShadowRectF;
    private float mShadowFlatX = 0.6f;
    private float mShadowFlatY = 0.3f;

    private int mIvHeight;
    private int mIvWidth;
    private int mIvTop;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mJumpHeightPx;

    /*
    *   image当前高度离完整高度的百分比
    * */
    private float mMovePercent;
    private View mContentView;

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
        setWillNotDraw(false);
        mShadowPaint = new Paint();
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setColor(Color.LTGRAY);
        mShadowRectF = new RectF();

        mJumpHeightPx = (int)DisplayUtil.dip2px(24, mContext);
    }


    private void initView() {
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.item_loading_view, this);
        mIv = ((ImageView) findViewById(R.id.iv_loading));
//        mIvWidth = mIv.getMeasuredWidth();
//        mIvHeight = mIv.getMeasuredHeight();
        mIvWidth = 48;
        mIvHeight = 48;
    }

    private void logic() {
        ValueAnimator animator = ValueAnimator.ofInt(0,mJumpHeightPx,0);
        animator.setDuration(800);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mIv.setTranslationY(-value);
                // 为了画shadow
                mMovePercent = value / 100f;
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 阴影宽高的一半   最大不超过16 / 8
        float shadowHalfWidth = Math.max(16f,(mIvRight - mIvLeft) / 2 );
        float shadowHalfHeight = Math.max(8f,(mIvBottom - mIvTop) / 2 );
        // 阴影区域中线位置（决定阴影所在位置） yCenter:垂直中线的纵坐标 xCenter：水平中线的横坐标
        float yCenter = mIvLeft + shadowHalfWidth;
        float xCenter = mIvTop + shadowHalfHeight * 2;
        // 缩放的变化量（决定阴影变化大小）
        float horizontalDiff = Math.max(2,(1f - mMovePercent) * shadowHalfWidth * mShadowFlatX);
        float verticalDiff = Math.max(2,(1f - mMovePercent) * shadowHalfHeight * mShadowFlatY);
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
        Log.d("bigname", "onLayout: ");
        super.onLayout(changed, left, top, right, bottom);
        mIvTop = mIv.getTop();
        mIvBottom = mIv.getBottom();
        mIvLeft = mIv.getLeft();
        mIvRight = mIv.getRight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("bigname", "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
