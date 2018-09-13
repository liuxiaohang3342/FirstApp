package com.example.lxh.firstapp.home.weather;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.bean.WeatherDayInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherAdapter extends BaseQuickAdapter<WeatherDayInfo, BaseViewHolder> {

    public WeatherAdapter(@LayoutRes int layoutResId, @Nullable List<WeatherDayInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherDayInfo item) {
    }
}
