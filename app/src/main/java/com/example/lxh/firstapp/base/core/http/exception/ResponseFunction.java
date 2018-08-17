package com.example.lxh.firstapp.base.core.http.exception;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by lxh on 2018/8/8.
 * map()进行数据类型转换，并检测异常。如果正常，返回data类型的数据。如果不正常，onErrorResumeNext()判断异常类型并传递异常
 * 接口返回数据格式需要一致
 */

public class ResponseFunction<T> implements Function<Response<T>, ObservableSource<T>> {

    @Override
    public ObservableSource<T> apply(Response<T> tResponse) throws Exception {
        int code = tResponse.getCode();
        String message = tResponse.getMsg();
        if (code == 200) {
            return Observable.just(tResponse.getData());
        } else {
            return Observable.error(new ApiException(code, message));
        }
    }
}
