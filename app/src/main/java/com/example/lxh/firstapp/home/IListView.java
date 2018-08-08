package com.example.lxh.firstapp.home;

import com.example.lxh.firstapp.base.core.mvp.IView;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public interface IListView<T> extends IView {

    void onRequestSuccess(List<T> itemList);

}
