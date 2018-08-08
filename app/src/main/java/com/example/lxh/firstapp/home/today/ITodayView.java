package com.example.lxh.firstapp.home.today;

import com.example.lxh.firstapp.base.core.mvp.IView;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public interface ITodayView<T> extends IView {

    void onRequestSuccess(List<T> itemList);

    void onLoadMoreSuccess(List<T> itemList);

    void onNoMore();

    void onError();
}
