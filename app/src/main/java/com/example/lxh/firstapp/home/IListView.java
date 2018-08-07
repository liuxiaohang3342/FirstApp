package com.example.lxh.firstapp.home;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.Album;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public interface IListView<T> extends IView {
    void onSuccess(List<T> itemList);
}
