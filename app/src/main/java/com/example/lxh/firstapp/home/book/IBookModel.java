package com.example.lxh.firstapp.home.book;

/**
 * Created by lxh on 2018/9/11.
 */

public interface IBookModel {
    void requestData(int page, IDataLoadListener loadListener);

    void requestRecommendData(IDataLoadListener loadListener);
}
