package com.example.lxh.firstapp.api;

import com.example.lxh.firstapp.base.core.http.exception.ErrorResumeFunction;
import com.example.lxh.firstapp.base.core.http.exception.ResponseFunction;
import com.example.lxh.firstapp.bean.response.TodayResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxh on 2018/8/8.
 */

public class HttpMethod {

    public static <T> Observable<T> packObservable(Observable<T> observable) {
        return observable.map(new ResponseFunction())
                .onErrorResumeNext(new ErrorResumeFunction<TodayResponse>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
