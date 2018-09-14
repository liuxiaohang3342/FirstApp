package com.example.lxh.firstapp.home.weather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.WeatherInfo;

import org.w3c.dom.Text;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherFragment extends BaseMVPFragment<WeatherPresenter, IWeatherView> implements IWeatherView {

    private TextView mWenduView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private TextView mCityView;
    private TextView mShiduView;
    private TextView mQualityView;

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
        mCityView = (TextView) view.findViewById(R.id.tv_city);
        mCityView.setOnClickListener(this);
        mShiduView = (TextView) view.findViewById(R.id.tv_shidu);
        mQualityView = (TextView) view.findViewById(R.id.tv_quality);
        mTabLayout = (TabLayout) view.findViewById(R.id.tl_weather);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_weather);
        mTabLayout.setupWithViewPager(mViewPager);
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_city:
                Toast.makeText(getContext(), "切换城市", Toast.LENGTH_SHORT).show();
                break;
        }
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
        mShiduView.setText(String.format(getString(R.string.weather_shidu_pm), weatherInfo.getShidu(), weatherInfo.getPm25()));
        mQualityView.setText(weatherInfo.getQuality());
        mViewPager.setAdapter(new WeatherAdapter(getActivity(), weatherInfo.getForecast()));
    }
}
