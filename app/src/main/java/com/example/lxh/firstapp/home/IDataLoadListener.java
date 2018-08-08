package com.example.lxh.firstapp.home;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public interface IDataLoadListener<T> {
    void onSuccess(List<T> list);

    void onError();
}
