package com.example.lxh.firstapp.common;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.banner.BannerIndicator;
import com.example.lxh.firstapp.banner.LoopBannerAdapter;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by lxh on 2018/8/20.
 */

public class CommonBannerPresenter<T> implements ViewPager.OnPageChangeListener {

    public interface BannerListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);

        Object instantiate(ViewGroup container, int position);

        View getView(ViewGroup viewGroup, int position);
    }

    private ViewPager mViewPager;
    private BannerIndicator mIndicator;
    private BannerListener mBannerListener;

    private ArrayList<T> mArrayList;

    private Context mContext;

    public CommonBannerPresenter(Context context, ArrayList<T> arrayList, BannerListener bannerListener) {
        mContext = context;
        mBannerListener = bannerListener;
        mArrayList = arrayList;
    }

    public void setView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.asvp_viewpager);
        mIndicator = (BannerIndicator) view.findViewById(R.id.indicator);
        mViewPager.setAdapter(new BannerAdapter());
        mIndicator.setAdapter(new IndicatorAdapter());
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mBannerListener != null) {
            mBannerListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mIndicator.setCurrentItem(position);
        if (mBannerListener != null) {
            mBannerListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mBannerListener != null) {
            mBannerListener.onPageScrollStateChanged(state);
        }
    }

    class BannerAdapter extends LoopBannerAdapter {

        @Override
        public int getDataSize() {
            return mArrayList.size();
        }

        @Override
        public Object instantiate(ViewGroup container, int position) {
            return mBannerListener.instantiate(container, position);
        }

        @Override
        public void destroy(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    class IndicatorAdapter implements com.example.lxh.firstapp.banner.IndicatorAdapter {
        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public View getView(ViewGroup viewGroup, int position) {
            if (mBannerListener != null) {
                View view = mBannerListener.getView(viewGroup, position);
                if (view != null) {
                    return view;
                }
            }
            return LayoutInflater.from(mContext).inflate(R.layout.banner_indicator_layout, viewGroup, false);
        }
    }


}
