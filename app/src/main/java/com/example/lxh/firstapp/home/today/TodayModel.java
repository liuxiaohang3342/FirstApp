package com.example.lxh.firstapp.home.today;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
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

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayModel implements ITodayModel<SourceInfo> {

    @Override
    public void request(final IDataLoadListener<SourceInfo> loadListener) {
        HttpMethod.day(new IListener<TodayResponse>() {
            @Override
            public void onSuccess(TodayResponse data) {
                List<SourceInfo> sourceInfos = new ArrayList<>();
                TodayResults todayResults = data.getResults();
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

            @Override
            public void onError(Throwable throwable) {
                loadListener.onError();
            }
        });
    }

    @Override
    public void request(Date date, final IDataLoadListener<SourceInfo> loadListener) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        HttpMethod.reqestDataByDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), new IListener<TodayResponse>() {
            @Override
            public void onSuccess(TodayResponse data) {
                List<SourceInfo> sourceInfos = new ArrayList<>();
                TodayResults todayResults = data.getResults();
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

            @Override
            public void onError(Throwable throwable) {
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
        HttpMethod.dayHistory(new IListener<HistoryResponse>() {
            @Override
            public void onSuccess(HistoryResponse data) {
                String[] results = data.getResults();
                List<String> historys = null;
                if (data != null) {
                    historys = Arrays.asList(results);
                }
                listener.onHistoryBack(historys);
            }

            @Override
            public void onError(Throwable throwable) {
                listener.onHistoryError();
            }
        });
    }


}
