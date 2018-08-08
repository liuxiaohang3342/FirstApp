package com.example.lxh.firstapp.home;

/**
 * Created by lxh on 2018/8/3.
 */

public interface IListModel<T> {
    void requestData(String key, int page, int pageNum, String uid, IDataLoadListener<T> loadListener);
}
