package com.example.lxh.firstapp.home.weather;

import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.WeatherInfo;

/**
 * Created by lxh on 2018/8/3.
 */

public class WeatherPresenter extends BasePresenter<IWeatherView> implements IListener<WeatherInfo> {

    private IWeatherModel mAlbumModel = new WeatherModel();

    public WeatherPresenter(IWeatherView iView) {
        super(iView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }

    public void requestData() {
        mAlbumModel.getWeatherByCity("北京", this);
    }

    @Override
    public void onSuccess(WeatherInfo weatherInfo) {
        if (getView() == null) {
            return;
        }
        if (weatherInfo == null) {
            getView().showEmptyView();
            return;
        }
        getView().onRequestSuccess(weatherInfo);
    }

    @Override
    public void onError(Throwable throwable) {
        if (getView() == null) {
            return;
        }
        getView().showErrorView();
    }
}
