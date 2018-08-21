package com.example.lxh.firstapp.banner;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;

import com.example.lxh.firstapp.common.CommonHandler;

import java.lang.reflect.Field;

/**
 * Created by lxh on 2018/8/20.
 * onAttachedToWindow里mFirstLayout设置true，导致滑出去再进来，下一次的缓慢滑动效果没了
 */

public class AutoScrollViewPager extends ViewPager implements CommonHandler.MessageHandler {
    private static final String TAG = AutoScrollViewPager.class.getSimpleName();

    private static final int INTERVAL_TIME = 3000;
    private static final int AUTO_SCROLL_CODE = 0;

    private boolean mIsStop;
    private CommonHandler mHandler;

    public AutoScrollViewPager(Context context) {
        this(context, null);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScroller(context);
        mHandler = new CommonHandler(this);
    }

    private void setScroller(Context context) {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mField.set(this, new FixedSpeedScroller(context));
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAutoScroll();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startAutoScroll();
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void stopAutoScroll() {
        mIsStop = true;
        mHandler.removeMessages(AUTO_SCROLL_CODE);
    }

    private void startAutoScroll() {
        mHandler.removeMessages(AUTO_SCROLL_CODE);
        mIsStop = false;
        mHandler.sendEmptyMessageDelayed(AUTO_SCROLL_CODE, INTERVAL_TIME);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setFirstLayout(false);
        startAutoScroll();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoScroll();
    }

    private void setFirstLayout(boolean flag) {
        try {
            Field mField = ViewPager.class.getDeclaredField("mFirstLayout");
            mField.setAccessible(true);
            mField.set(this, flag);
        } catch (Exception e) {
        }
    }

    @Override
    public void handleMessage(Message message) {
        if (mIsStop || getAdapter() == null || getAdapter().getCount() < 2) {
            return;
        }
        autoScroll();
    }

    private void autoScroll() {
        int currentPosition = getCurrentItem() + 1;
        setCurrentItem(currentPosition, true);
        startAutoScroll();
    }


    class FixedSpeedScroller extends Scroller {
        private static final int DURATION = 1500;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, DURATION);
        }
    }

}
