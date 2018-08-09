package com.example.lxh.firstapp.api;

import com.example.lxh.firstapp.base.core.http.RetrofitClient;
import com.example.lxh.firstapp.bean.GirlInfo;
import com.example.lxh.firstapp.bean.SourceInfo;
import com.example.lxh.firstapp.bean.response.DataResponse;
import com.example.lxh.firstapp.bean.response.HistoryResponse;
import com.example.lxh.firstapp.bean.response.TodayResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxh on 2018/8/8.
 */

public class HttpMethod {

    static <T> Observable<T> packObservable(Observable<T> observable) {
        return observable
//                .map(new ResponseFunction())
//                .onErrorResumeNext(new ErrorResumeFunction<TodayResponse>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static void day(final IListener<TodayResponse> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).today())
                .subscribe(new Consumer<TodayResponse>() {
                    @Override
                    public void accept(@NonNull TodayResponse todayResponse) throws Exception {
                        listener.onSuccess(todayResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }

    public static void reqestDataByDate(int year, int month, int day, final IListener<TodayResponse> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).reqestDataByDate(year, month, day))
                .subscribe(new Consumer<TodayResponse>() {
                    @Override
                    public void accept(@NonNull TodayResponse todayResponse) throws Exception {
                        listener.onSuccess(todayResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }


    public static void dayHistory(final IListener<HistoryResponse> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).dayHistory())
                .subscribe(new Consumer<HistoryResponse>() {
                    @Override
                    public void accept(@NonNull HistoryResponse historyResponse) throws Exception {
                        listener.onSuccess(historyResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }


    public static void getGirlData(int num, int page, final IListener<DataResponse<GirlInfo>> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).getGirlData(num, page))
                .subscribe(new Consumer<DataResponse>() {
                    @Override
                    public void accept(@NonNull DataResponse historyResponse) throws Exception {
                        listener.onSuccess(historyResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }


}
