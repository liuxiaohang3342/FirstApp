package com.example.lxh.firstapp.home.weather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.WeatherInfo;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherFragment extends BaseMVPFragment<WeatherPresenter, IWeatherView> implements IWeatherView {

    private TextView mWenduView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

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
        mTabLayout = (TabLayout) view.findViewById(R.id.tl_weather);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_weather);
        mTabLayout.setupWithViewPager(mViewPager);
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
        mViewPager.setAdapter(new WeatherAdapter(getActivity(), weatherInfo.getForecast()));
    }
}
