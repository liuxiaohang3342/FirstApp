package com.example.lxh.firstapp.home.today;

import com.example.lxh.firstapp.api.GankApi;
import com.example.lxh.firstapp.base.core.http.RetrofitClient;
import com.example.lxh.firstapp.bean.AndroidInfo;
import com.example.lxh.firstapp.bean.SourceInfo;
import com.example.lxh.firstapp.bean.response.HistoryResponse;
import com.example.lxh.firstapp.bean.response.TodayResponse;
import com.example.lxh.firstapp.bean.response.TodayResults;
import com.example.lxh.firstapp.home.IDataLoadListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayModel implements ITodayModel<SourceInfo> {

    @Override
    public void request(final IDataLoadListener<SourceInfo> loadListener) {
        RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).today()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TodayResponse>() {
                    @Override
                    public void accept(@NonNull TodayResponse todayResponse) throws Exception {
                        List<SourceInfo> sourceInfos = new ArrayList<>();
                        TodayResults todayResults = todayResponse.getResults();
                        if (todayResults != null) {
                            sourceInfos.addAll(todayResults.getAndroid());
                            sourceInfos.addAll(todayResults.getApp());
                            sourceInfos.addAll(todayResults.getiOS());
                            sourceInfos.addAll(todayResults.get前端());
                            sourceInfos.addAll(todayResults.get福利());
                            sourceInfos.addAll(todayResults.get休息视频());
                        }
                        loadListener.onSuccess(sourceInfos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        loadListener.onError();
                    }
                });
    }

    @Override
    public void request(Date date, final IDataLoadListener<SourceInfo> loadListener) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).reqestDataByDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TodayResponse>() {
                    @Override
                    public void accept(@NonNull TodayResponse todayResponse) throws Exception {
                        List<SourceInfo> sourceInfos = new ArrayList<>();
                        TodayResults todayResults = todayResponse.getResults();
                        if (todayResults != null) {
                            addList(sourceInfos, todayResults.getAndroid());
                            addList(sourceInfos, todayResults.getApp());
                            addList(sourceInfos, todayResults.getiOS());
                            addList(sourceInfos, todayResults.get前端());
                            addList(sourceInfos, todayResults.get福利());
                            addList(sourceInfos, todayResults.get休息视频());
                        }
                        loadListener.onSuccess(sourceInfos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        loadListener.onError();
                    }
                });
    }

    private void addList(List<SourceInfo> total, List<? extends SourceInfo> subList) {
        if (subList != null) {
            total.addAll(subList);
        }
    }

    @Override
    public void dayHistory(final IHistoryListener listener) {
        RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).dayHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HistoryResponse>() {
                    @Override
                    public void accept(@NonNull HistoryResponse historyResponse) throws Exception {
                        String[] data = historyResponse.getResults();
                        List<String> historys = null;
                        if (data != null) {
                            historys = Arrays.asList(data);
                        }
                        listener.onHistoryBack(historys);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onHistoryError();
                    }
                });
    }


}
