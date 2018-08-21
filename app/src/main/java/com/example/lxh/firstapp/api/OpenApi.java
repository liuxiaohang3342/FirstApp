package com.example.lxh.firstapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lxh on 2018/8/17.
 */

public interface OpenApi {

    String OPEN_URL = "https://www.apiopen.top/";

    @GET("novelApi")
    Observable recommendBook();

    @GET("novelSearchApi")
    Observable serachBook(@Query("name") String name);

    @GET("novelInfoApi")
    Observable bookDetail(@Query("name") String name);

    @GET("journalismApi")
    Observable news();

    @GET("weatherApi")
    Observable weatherByCity(@Query("city") String city);

}
