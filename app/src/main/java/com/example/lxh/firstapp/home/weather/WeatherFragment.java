package com.example.lxh.firstapp.home.weather;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.WeatherDayInfo;
import com.example.lxh.firstapp.bean.WeatherInfo;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherFragment extends BaseMVPFragment<WeatherPresenter, IWeatherView> implements IWeatherView {

    private RecyclerView mRecyclerView;
    private TextView mWenduView;
    private TextView mPmView;
    private TextView mQualityView;
    private TextView mWindView;
    private TextView mTipView;

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_weather_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWenduView = (TextView) view.findViewById(R.id.tv_wendu);
        mQualityView = (TextView) view.findViewById(R.id.tv_quality);
        mPmView = (TextView) view.findViewById(R.id.tv_pm);
        mWindView = (TextView) view.findViewById(R.id.tv_wind);
        mTipView = (TextView) view.findViewById(R.id.tv_desc);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rlv_weather);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onRequestSuccess(WeatherInfo weatherInfo) {
        showContentView();
        mWenduView.setText(weatherInfo.getWendu());
        mQualityView.setText(weatherInfo.getQuality());
        mPmView.setText("PM:" + String.valueOf(weatherInfo.getPm25()));
        WeatherDayInfo todayInfo = weatherInfo.getForecast().get(0);
        mWindView.setText(todayInfo.getFx() + "   " + todayInfo.getFl() + "    湿度    " + weatherInfo.getShidu());
        mTipView.setText(weatherInfo.getGanmao());
        mRecyclerView.setAdapter(new WeatherAdapter(R.layout.home_weather_item_layout, weatherInfo.getForecast()));
    }
}
