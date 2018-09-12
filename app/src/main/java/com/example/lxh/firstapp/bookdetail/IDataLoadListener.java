package com.example.lxh.firstapp.bookdetail;

/**
 * Created by lxh on 2018/9/12.
 */

public interface IDataLoadListener<T> {
    void onSuccess(T list);

    void onError();
}
