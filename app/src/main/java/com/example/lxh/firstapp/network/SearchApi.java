package com.example.lxh.firstapp.network;

import com.example.lxh.firstapp.bean.User;
import com.example.lxh.firstapp.bean.response.AlbumResponse;
import com.example.lxh.firstapp.bean.response.SongListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lxh on 2018/7/26.
 */

public interface SearchApi {

    String SEARCH_URL = "http://search.kuwo.cn/";

    @GET
    Observable<User> getUser(@Query("uid") String uid);

    @GET("r.s?client=kt&ver=kwplayer_ar_9.0.2.0&ft=album&vipver=1&cluster=0&encoding=utf8&rformat=json&mobi=1&strategy=2012&presell=1")
    Observable<AlbumResponse> searchAlbum(@Query("all") String key, @Query("pn") int pn, @Query("rn") int rn, @Query("uid") String uid);

    @GET("r.s?client=kt&ver=kwplayer_ar_9.0.2.0&ft=playlist&vipver=1&encoding=utf8&rformat=json&mobi=1&from=mobile")
    Observable<SongListResponse> searchSongList(@Query("all") String key, @Query("pn") String pn, @Query("rn") String rn, @Query("uid") String uid);

}
