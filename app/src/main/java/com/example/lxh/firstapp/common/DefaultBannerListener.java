package com.example.lxh.firstapp.common;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lxh on 2018/9/14.
 */

public class DefaultBannerListener<T> implements CommonBannerPresenter.BannerListener<T> {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public Object instantiate(ViewGroup container, int position, T item) {
        return null;
    }

    @Override
    public View getView(ViewGroup viewGroup, int position) {
        return null;
    }
}
