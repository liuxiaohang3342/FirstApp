package com.example.lxh.firstapp.home.weather;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.WeatherInfo;

/**
 * Created by lxh on 2018/9/13.
 */

public interface IWeatherView extends IView {

    void showEmptyView();

    void showErrorView();

    void onRequestSuccess(WeatherInfo weatherInfo);

}
