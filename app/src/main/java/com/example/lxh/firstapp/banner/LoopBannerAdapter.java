package com.example.lxh.firstapp.banner;

import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

/**
 * Created by lxh on 2018/8/20.
 */

public abstract class LoopBannerAdapter extends PagerAdapter {

    @Override
    public final int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getRealPosition(int position) {
        int size = getDataSize();
        return position % size;
    }


    public abstract int getDataSize();

    @Override
    public final Object instantiateItem(ViewGroup container, int position) {
        return instantiate(container, getRealPosition(position));
    }

    @Override
    public final void destroyItem(ViewGroup container, int position, Object object) {
        destroy(container, getRealPosition(position), object);
    }

    public abstract Object instantiate(ViewGroup container, int position);

    public abstract void destroy(ViewGroup container, int position, Object object);
}
