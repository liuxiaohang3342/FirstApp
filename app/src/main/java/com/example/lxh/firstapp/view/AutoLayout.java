package com.example.lxh.firstapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.lxh.firstapp.R;

import java.util.ArrayList;
import java.util.List;

public class AutoLayout extends ViewGroup implements View.OnClickListener {
    private static final String TAG = AutoLayout.class.getSimpleName();

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onClick(view, indexOfChild(view));
        }
    }


    public interface OnClickListener {
        void onClick(View v, int position);
    }

    private int mYSpace;
    private int mXSpace;

    private AutoAdapter mAdapter;
    private List<Line> mLineList;

    private OnClickListener mListener;

    public void setListener(OnClickListener mListener) {
        this.mListener = mListener;
    }

    public AutoLayout(Context context) {
        this(context, null);
    }

    public AutoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout);
        mXSpace = typedArray.getDimensionPixelOffset(R.styleable.AutoLayout_horizontal_space, 0);
        mYSpace = typedArray.getDimensionPixelOffset(R.styleable.AutoLayout_vertical_space, 0);
        typedArray.recycle();
    }

    public AutoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLineList = new ArrayList<>();
    }


    public void setAdapter(AutoAdapter mAdapter) {
        this.mAdapter = mAdapter;
        notifyDataChange();
    }

    public void notifyDataChange() {
        if (mAdapter == null) {
            return;
        }
        removeAllViews();
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = mAdapter.getView(this, i);
            addView(view);
            view.setOnClickListener(this);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
        allocationView();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        for (Line line : mLineList) {
            height += (line.height + mYSpace);
        }
        height -= mYSpace;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int top = 0;
        for (Line line : mLineList) {
            List<View> childViews = line.mViews;
            int left = 0;
            for (View child : childViews) {
                child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
                left += (mXSpace + child.getMeasuredWidth());
            }
            top += (mYSpace + line.height);
        }
    }

    private void allocationView() {
        mLineList.clear();
        int count = getChildCount();
        Line line = new Line(getMeasuredWidth());
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (line.addView(view)) {
                continue;
            }
            mLineList.add(line);
            line = new Line(getMeasuredWidth());
            if (!line.addView(view)) {
                Log.i(TAG, "超出父布局，被遗弃");
            }
        }
        mLineList.add(line);
    }


    public interface AutoAdapter {

        int getCount();

        View getView(ViewGroup viewGroup, int position);
    }

    class Line {
        int height;
        int totalWidth;
        private List<View> mViews;

        int mParentWidth;

        public Line(int mParentWidth) {
            this.mParentWidth = mParentWidth;
            mViews = new ArrayList<>();
        }


        public boolean addView(View view) {
            if (totalWidth + view.getMeasuredWidth() + mXSpace > mParentWidth) {
                return false;
            }
            int viewHeight = view.getMeasuredHeight();
            if (viewHeight > height) {
                height = viewHeight;
            }
            mViews.add(view);
            totalWidth += (view.getMeasuredWidth() + mXSpace);
            return true;
        }
    }


}
