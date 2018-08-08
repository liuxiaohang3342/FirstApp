package com.example.lxh.firstapp.base.core.http.exception;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by lxh on 2018/8/8.
 * map()进行数据类型转换，并检测异常。如果正常，返回data类型的数据。如果不正常，onErrorResumeNext()判断异常类型并传递异常
 */

public class ErrorResumeFunction <T> implements Function<Throwable, ObservableSource<? extends Response<T>>> {

    @Override
    public ObservableSource<? extends Response<T>> apply(Throwable throwable) throws Exception {
        return Observable.error(CustomException.handleException(throwable));
    }
}
