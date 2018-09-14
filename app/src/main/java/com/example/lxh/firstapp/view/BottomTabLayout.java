package com.example.lxh.firstapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pools;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxh.firstapp.R;

import java.util.ArrayList;

/**
 * Created by lxh on 2018/9/14.
 */

public class BottomTabLayout extends LinearLayout implements View.OnClickListener {

    private ViewPager mViewPager;
    private static final Pools.Pool<Tab> sTabPool = new Pools.SynchronizedPool<>(16);
    private final Pools.Pool<TabView> mTabViewPool = new Pools.SimplePool<>(12);
    private final ArrayList<Tab> mTabs = new ArrayList<>();

    private int mIconSize;
    private int mTextSize;
    private int mTextColor;
    private int mSelectColor;

    private ColorMatrix mColorMatrix;

    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BottomTabLayout);
        mIconSize = array.getDimensionPixelOffset(R.styleable.BottomTabLayout_icon_size, 120);
        mTextSize = array.getDimensionPixelOffset(R.styleable.BottomTabLayout_text_size, 45);
        mTextColor = array.getColor(R.styleable.BottomTabLayout_text_color, context.getColor(R.color.skin_title_less_important_color));
        mSelectColor = array.getColor(R.styleable.BottomTabLayout_select_color, context.getColor(R.color.colorTheme));
        array.recycle();
    }

    public void setSelected(int position) {
        if (position < 0 || position >= mTabs.size()) {
            return;
        }
        for (int i = 0, N = mTabs.size(); i < N; i++) {
            Tab tab = mTabs.get(i);
            tab.setSelected(i == position);
        }
        updateTabView();
    }

    private void updateTabView() {
        for (Tab tab : mTabs) {
            tab.mView.update();
        }
    }

    public void addTab(Tab tab) {
        mTabs.add(tab);
        addView(tab.mView);
    }

    public void setViewPager(ViewPager viewpager) {
        mViewPager = viewpager;
    }

    public Tab newTab() {
        Tab tab = sTabPool.acquire();
        if (tab == null) {
            tab = new Tab();
        }
        tab.mView = createTabView(tab);
        return tab;
    }

    private TabView createTabView(Tab tab) {
        TabView tabView = mTabViewPool != null ? mTabViewPool.acquire() : null;
        if (tabView == null) {
            tabView = new TabView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            params.weight = 1;
            tabView.setGravity(Gravity.CENTER);
            tabView.setLayoutParams(params);
        }
        tabView.setOnClickListener(this);
        tabView.setTab(tab);
        tabView.setFocusable(true);
        return tabView;
    }

    @Override
    public void onClick(View v) {
        if (mViewPager != null) {
            int position = indexOfChild(v);
            mViewPager.setCurrentItem(position);
            setSelected(position);
        }
    }

    class TabView extends LinearLayout {

        private Tab mTab;
        private ImageView mIcon;
        private TextView mTextView;

        public TabView(Context context) {
            super(context);
            setOrientation(VERTICAL);
            initView(context);
        }

        private void initView(Context context) {
            mIcon = new ImageView(context);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(mIconSize, mIconSize);
            mIcon.setLayoutParams(imgParams);
            addView(mIcon);
            mTextView = new TextView(context);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            mTextView.setTextColor(mTextColor);
            mTextView.setLayoutParams(textParams);
            addView(mTextView);
        }

        public void setTab(Tab tab) {
            mTab = tab;
            update();
        }

        private void update() {
            Drawable drawable = mTab.getIcon();
            if (drawable != null) {
                mTab.getIcon().getCurrent().setColorFilter(mTab.isSelected() ? getColorFilter() : null);
            }
            mIcon.setImageDrawable(mTab.getIcon());
            mTextView.setTextColor(mTab.isSelected() ? mSelectColor : mTextColor);
            mTextView.setText(mTab.getText());
        }
    }


    public static final class Tab {

        private Drawable mIcon;
        private String mText;
        private TabView mView;
        private boolean mIsSelected;

        public Tab setIcon(Drawable icon) {
            mIcon = icon;
            mView.update();
            return this;
        }

        public Tab setText(String text) {
            mText = text;
            mView.update();
            return this;
        }

        public String getText() {
            return mText;
        }

        public Drawable getIcon() {
            return mIcon;
        }

        public boolean isSelected() {
            return mIsSelected;
        }

        public void setSelected(boolean selected) {
            mIsSelected = selected;
        }

        public TabView getView() {
            return mView;
        }

        public void setView(TabView view) {
            mView = view;
        }
    }

    public ColorMatrixColorFilter getColorFilter() {
        if (mColorMatrix == null) {
            mColorMatrix = new ColorMatrix();
        }
        mColorMatrix.set(new float[]{
                0, 0, 0, 0, Color.red(mSelectColor),
                0, 0, 0, 0, Color.green(mSelectColor),
                0, 0, 0, 0, Color.blue(mSelectColor),
                0, 0, 0, 1, 0
        });
        return new ColorMatrixColorFilter(mColorMatrix);
    }

}
