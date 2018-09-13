package com.example.lxh.firstapp.home.today;

import com.example.lxh.firstapp.api.IListener;

import java.util.Date;

/**
 * Created by lxh on 2018/8/8.
 */

public interface ITodayModel<T> {
    void request(IListener<T> loadListener);

    void request(Date date, IListener<T> loadListener);

    void dayHistory(IHistoryListener listener);

}
