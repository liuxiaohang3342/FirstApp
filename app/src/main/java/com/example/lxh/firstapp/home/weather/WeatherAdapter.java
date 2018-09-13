package com.example.lxh.firstapp.home.weather;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.bean.WeatherDayInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherAdapter extends PagerAdapter {


    private Context mContext;
    private List<WeatherDayInfo> mList;

    public WeatherAdapter(Context context, List<WeatherDayInfo> mList) {
        mContext = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout, container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getDate();
    }
}
