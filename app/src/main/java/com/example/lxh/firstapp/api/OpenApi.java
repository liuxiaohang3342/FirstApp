package com.example.lxh.firstapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lxh on 2018/8/17.
 */
interface OpenApi {

    @GET("weatherApi")
    Observable weatherByCity(@Query("city") String city);

}
