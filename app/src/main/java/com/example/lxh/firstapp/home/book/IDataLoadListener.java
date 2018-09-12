package com.example.lxh.firstapp.home.book;

import com.example.lxh.firstapp.bean.BookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public interface IDataLoadListener {

    void onDataSuccess(List<BookInfo> list);

    void onRecommendSuccess(List<BookInfo> list);

    void onError();
}
