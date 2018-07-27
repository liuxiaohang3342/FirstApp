package com.example.lxh.firstapp.base.network;

import com.example.lxh.firstapp.bean.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lxh on 2018/7/26.
 */

public interface Api {
    @GET
    Observable<User> getUser(@Query("uid") String uid);
}
