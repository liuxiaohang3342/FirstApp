package com.example.lxh.firstapp.api;

/**
 * Created by lxh on 2018/8/9.
 */

public interface IListener<T>{

    void onSuccess(T data);

    void onError(Throwable throwable);
}
