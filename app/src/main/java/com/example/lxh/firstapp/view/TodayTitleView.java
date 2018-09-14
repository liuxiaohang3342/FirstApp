package com.example.lxh.firstapp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.utils.DensityUtil;

public class TodayTitleView extends LinearLayout implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    public interface TitleListener {
        void onMenuClick();

        void onCityClick();

        void onSearchClick();

        void closeExpanded();
    }

    private final static int WAIT_TIME = 2000;

    private final static float ICON_SCALE = 0.3f;
    private final static float TEXT_SCALE = 0.5f;
    private int mTenDp;
    private int mFiveDp;

    private View mMenu;
    private View mWendu;
    private View mCity;
    private View mSearch;
    private View mUpdate;

    private TitleListener mListener;

    private boolean isScroll = false;


    public TodayTitleView(Context context) {
        this(context, null);
    }

    public TodayTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TodayTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflaterContent(context);
        mTenDp = DensityUtil.dp2px(context, 10);
        mFiveDp = DensityUtil.dp2px(context, 5);
    }

    public void setmListener(TitleListener mListener) {
        this.mListener = mListener;
    }

    private void inflaterContent(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.today_title_view_layout, this, true);
        mMenu = view.findViewById(R.id.iv_menu);
        mWendu = view.findViewById(R.id.tv_wendu);
        mCity = view.findViewById(R.id.tv_city);
        mSearch = view.findViewById(R.id.tv_search);
        mUpdate = view.findViewById(R.id.tv_update);
        mMenu.setOnClickListener(this);
        mWendu.setOnClickListener(this);
        mCity.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        isScroll = true;
        float percent = -verticalOffset * 1f / (appBarLayout.getHeight() - getMinimumHeight());
        mUpdate.setAlpha(1 - percent);
        mUpdate.setTranslationY(-verticalOffset);
        if (percent == 1) {
            mUpdate.setVisibility(GONE);
        } else {
            mUpdate.setVisibility(VISIBLE);
        }

        float offset = (mMenu.getWidth() + mTenDp) * percent;
        mSearch.setTranslationX(offset);
        mCity.setTranslationX(offset);

        mMenu.setTranslationY(-verticalOffset - mFiveDp * percent);
        mMenu.setScaleX(1 - percent * ICON_SCALE);
        mMenu.setScaleY(1 - percent * ICON_SCALE);

        mWendu.setScaleX(1 - percent * TEXT_SCALE);
        mWendu.setScaleY(1 - percent * TEXT_SCALE);
        mWendu.setTranslationY(-verticalOffset + mTenDp * percent);
        mWendu.setTranslationX(-(mMenu.getWidth() + mWendu.getWidth()) / 2 * percent);

        if (percent == 0) {
            isScroll = false;
            removeCallbacks(mRunner);
            postDelayed(mRunner, WAIT_TIME);
        }
    }

    private Runnable mRunner = new Runnable() {
        @Override
        public void run() {
            if (!isScroll && mListener != null) {
                mListener.closeExpanded();
            }
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mRunner);
    }

    @Override
    public void onClick(View view) {
        if (mListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_menu:
                mListener.onMenuClick();
                Toast.makeText(getContext(), "菜单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_city:
                mListener.onCityClick();
                Toast.makeText(getContext(), "城市", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search:
                mListener.onSearchClick();
                Toast.makeText(getContext(), "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
