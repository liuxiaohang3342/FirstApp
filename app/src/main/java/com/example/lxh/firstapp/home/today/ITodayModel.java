package com.example.lxh.firstapp.home.today;

import com.example.lxh.firstapp.home.IDataLoadListener;

import java.util.Date;

/**
 * Created by lxh on 2018/8/8.
 */

public interface ITodayModel<T> {
    void request(IDataLoadListener<T> loadListener);

    void request(Date date, IDataLoadListener<T> loadListener);

    void dayHistory(IHistoryListener listener);

}
