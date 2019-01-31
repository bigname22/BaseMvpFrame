package com.example.administrator.mvpframedemo.other.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.TextView;

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
    private TextView mTv;

    private int mImgResIds[];
    private Context mContext;

    private Paint mShadowPaint;
    private RectF mShadowRectF;
    private float mShadowFlatX = 0.6f;
    private float mShadowFlatY = 0.3f;
    private int mShadowColor = Color.LTGRAY;

    private int mIvTop;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mJumpHeightPx;

    private ValueAnimator mAnimation;
    private int mDuration = 1000;
    private int mRepeatCount = 0;


    /*
    *   image当前高度离完整高度的百分比
    * */
    private float mMovePercent;


    private final int DEFAULT_JUMP_HEIGHT_DP = 24;



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
        setVisibility(View.GONE);
        mShadowPaint = new Paint();
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setColor(mShadowColor);
        mShadowRectF = new RectF();

        mJumpHeightPx = (int)DisplayUtil.dip2px(DEFAULT_JUMP_HEIGHT_DP, mContext);
    }


    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.item_loading_view, this);
        mIv = ((ImageView) findViewById(R.id.iv_loading));
        mTv = ((TextView) findViewById(R.id.tv_loading));
    }

    private void logic() {
        this.mAnimation = createAnimation();
    }

    private ValueAnimator createAnimation () {
        ValueAnimator animation = ValueAnimator.ofInt(0,mJumpHeightPx,0);
        animation.setDuration(mDuration);
        animation.setRepeatMode(ValueAnimator.RESTART);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mIv.setTranslationY(-value);
                // 为了画shadow
                mMovePercent = value / (float)mJumpHeightPx;
                postInvalidate();
            }
        });
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                mRepeatCount++;
                if(mImgResIds !=null && mImgResIds.length > 0) {
                    mIv.setImageResource(mImgResIds[mRepeatCount%mImgResIds.length]);
                }
            }

            @Override
            public void onAnimationResume(Animator animation) {
            }
        });
        return animation;
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
//        Log.d("bigname", "onDraw: " + yCenter + "-----" + xCenter + "------" + horizontalDiff + "--------" + verticalDiff + "-------" + mMovePercent);
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


    // 供外部设置 ---------------------
    /*
    *   设置跳跃高度
    * */
    public LoadingView setJumpHeightPx(int jumpPx) {
        this.mJumpHeightPx = jumpPx;
        mAnimation.setIntValues(0,mJumpHeightPx,0);
        return this;
    }

    public LoadingView setJumpHeightDp(int jumpDp) {
        int height = (int)DisplayUtil.dip2px((float) jumpDp, mContext);
        setJumpHeightPx(height);
        return this;
    }

    /*
    *   设置一次跳跃的动画时间
    * */
    public LoadingView setDuration(int duration) {
        this.mDuration = duration;
        mAnimation.setDuration(this.mDuration);
        return this;
    }

    /*
    *   设置动画重复模式
    * */
    private void setRepeatMode (int mode) {

    }

    /*
    *   设置阴影颜色
    * */
    public LoadingView setShadowColor (int color) {
        this.mShadowColor = color;
        mShadowPaint.setColor(this.mShadowColor);
        return this;
    }

    /*
    *   以跳跃图片大小为准，进行缩小
    * */
    public LoadingView setShadowFlat (float x, float y) {
        this.mShadowFlatX = x;
        this.mShadowFlatY = y;
        return this;
    }

    /*
    *   设置加载文字
    * */
    public LoadingView setTips(String tips) {
        mTv.setText(tips);
        return this;
    }

    /*
    *   显示loading
    * */
    public void load () {
        setVisibility(View.VISIBLE);
        if (mAnimation == null) {
            mAnimation = createAnimation();
        }
        mAnimation.start();
    }

    /*
    *   隐藏loading
    * */
    public void hide(){
        setVisibility(View.GONE);
        if(mAnimation != null){
            mAnimation.cancel();
            mAnimation = null;
        }
    }

    /*
    *   设置播放图片列表
    * */
    public LoadingView setPlayImgs (int[] imgResIds) {
        this.mImgResIds = imgResIds;
        return this;
    }
}
