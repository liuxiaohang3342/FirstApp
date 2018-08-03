package com.example.lxh.firstapp.base.core.http.manager;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxh on 2018/8/3.
 */

public interface IActionManager<T> {

    void add(T key, Disposable disposable);

    void remove(T key);

    void cancel(T key);

    void cancelAll();
}
