package com.example.lxh.firstapp.home.book;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by lxh on 2018/9/14.
 */

public class BannerPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = .9f;
    private static final float MAX_SCALE = 1f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else {
            if (position < 0) {
                page.setScaleX(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 + position));
                page.setScaleY(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 + position));
            } else if (position >= 0) {
                page.setScaleX(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - position));
                page.setScaleY(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - position));
            }
        }
    }
}
