package com.example.lxh.firstapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lxh on 2018/8/17.
 */

public interface OpenApi {

    String OPEN_URL = "https://www.apiopen.top/";

//    推荐小说
//    https://www.apiopen.top/novelApi
//    小说搜索接口
//    https://www.apiopen.top/novelSearchApi?name=盗墓笔记
//    小说详情接口
//    https://www.apiopen.top/novelInfoApi?name=盗墓笔记
//    新闻接口：
//    https://www.apiopen.top/journalismApi
//    天气
//    https://www.apiopen.top/weatherApi?city=%E5%8C%97%E4%BA%AC
//    美图
//    https://www.apiopen.top/meituApi?page=1

    @GET("novelApi")
    Observable recommendBook();

    @GET("novelSearchApi")
    Observable serachBook(@Query("name") String name);

    @GET("novelInfoApi")
    Observable bookDetail(@Query("name") String name);

}
