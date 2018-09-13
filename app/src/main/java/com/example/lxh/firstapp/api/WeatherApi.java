package com.example.lxh.firstapp.api;

import com.example.lxh.firstapp.bean.response.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lxh on 2018/8/17.
 */
interface WeatherApi {

    String WEATHER_API = "https://www.sojson.com/open/api/weather/";

    @GET("json.shtml")
    Observable<WeatherResponse> weatherByCity(@Query("city") String city);

}
