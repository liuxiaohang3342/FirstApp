package com.example.lxh.firstapp.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lxh.firstapp.R;

/**
 * Created by lxh on 2018/8/20.
 */

public class BannerIndicator extends LinearLayout {

    private IndicatorAdapter mAdapter;

    private int mSpace;

    public BannerIndicator(Context context) {
        this(context, null);
    }

    public BannerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BannerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BannerIndicator, defStyleAttr, -1);
        mSpace = array.getDimensionPixelOffset(R.styleable.BannerIndicator_space, 10);
        array.recycle();
        setOrientation(HORIZONTAL);
    }


    public void setAdapter(IndicatorAdapter adapter) {
        mAdapter = adapter;
        clearIndicator();
        addIndicator();
        requestLayout();
    }

    private void addIndicator() {
        int size = mAdapter.getCount();
        for (int i = 0; i < size; i++) {
            View child = mAdapter.getView(this, i);
            LinearLayout.LayoutParams params = (LayoutParams) child.getLayoutParams();
            params.leftMargin = mSpace / 2;
            params.rightMargin = mSpace / 2;
            child.setLayoutParams(params);
            addView(child);
        }
    }

    private void clearIndicator() {
        removeAllViews();
    }

    public void setCurrentItem(int position) {
        int size = getChildCount();
        int realPosition = getRealPosition(position);
        if (realPosition < 0 || realPosition >= size) {
            return;
        }
        for (int i = 0; i < size; i++) {
            View child = getChildAt(i);
            if (i == realPosition) {
                child.setSelected(true);
            } else {
                child.setSelected(false);
            }
        }
    }

    private int getRealPosition(int position) {
        int size = mAdapter.getCount();
        return position % size;
    }

}
