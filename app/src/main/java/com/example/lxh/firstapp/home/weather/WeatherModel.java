package com.example.lxh.firstapp.home.weather;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.WeatherInfo;
import com.example.lxh.firstapp.bean.response.WeatherResponse;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherModel implements IWeatherModel {


    @Override
    public void getWeatherByCity(String city, final IListener<WeatherInfo> loadListener) {
        HttpMethod.getWeatherByCity(city, new IListener<WeatherResponse>() {
            @Override
            public void onSuccess(WeatherResponse data) {
                if (loadListener != null) {
                    loadListener.onSuccess(data.getData());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (loadListener != null) {
                    loadListener.onError(throwable);
                }
            }
        });
    }
}
