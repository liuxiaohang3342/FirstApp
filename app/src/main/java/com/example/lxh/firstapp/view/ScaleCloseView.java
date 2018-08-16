package com.example.lxh.firstapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by lxh on 2018/8/14.
 */

public class ScaleCloseView extends RelativeLayout {

    private int mLastX;
    private int mLastY;

    private int mLastInterceptX;
    private int mLastInterceptY;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;


    public ScaleCloseView(Context context) {
        this(context, null, -1);
    }

    public ScaleCloseView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ScaleCloseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean mIntercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    mIntercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastInterceptX;
                int deltaY = y - mLastInterceptY;
                if (Math.abs(deltaX) < Math.abs(deltaY)) {
                    mIntercepted = true;
                } else {
                    mIntercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                mIntercepted = false;
                break;
        }
        mLastInterceptX = x;
        mLastInterceptY = y;
        mLastY = y;
        mLastX = x;
        return mIntercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastY;
                int deltaX = x - mLastX;
                translateView(deltaX, deltaY);
                scaleView();
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                float yVelocityTracker = mVelocityTracker.getYVelocity();
                if (Math.abs(yVelocityTracker) > 50) {

                }
                reset();
                break;
        }
        mLastY = y;
        mLastX = x;
        return true;
    }

    private void reset() {
        mScroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY(), 1000);
        setScaleX(1);
        setScaleY(1);
        invalidate();
    }

    private void scaleView() {
        float percent = 1 - Math.abs(getScrollY()) * 0.5f / getHeight();
        if (percent < 0) {
            percent = 0;
        }
        setScaleX(percent);
        setScaleY(percent);
    }

    private void translateView(int deltaX, int deltaY) {
        scrollBy(-deltaX, -deltaY);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
