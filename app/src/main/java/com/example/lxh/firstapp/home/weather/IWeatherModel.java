package com.example.lxh.firstapp.home.weather;

import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.WeatherInfo;

/**
 * Created by lxh on 2018/9/13.
 */

public interface IWeatherModel {

    void getWeatherByCity(String city, final IListener<WeatherInfo> loadListener);

}
