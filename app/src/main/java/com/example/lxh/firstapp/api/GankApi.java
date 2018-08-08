package com.example.lxh.firstapp.api;

import com.example.lxh.firstapp.bean.response.HistoryResponse;
import com.example.lxh.firstapp.bean.response.TodayResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lxh on 2018/8/8.
 */

public interface GankApi {

    String GANK_URL = "http://gank.io/api/";

    @GET("today")
    Observable<TodayResponse> today();

    @GET("day/{year}/{month}/{day}")
    Observable<TodayResponse> reqestDataByDate(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("day/history")
    Observable<HistoryResponse> dayHistory();

}
