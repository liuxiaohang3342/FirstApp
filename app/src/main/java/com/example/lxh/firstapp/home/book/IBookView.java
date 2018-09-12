package com.example.lxh.firstapp.home.book;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.ModuleInfo;
import com.example.lxh.firstapp.bean.BookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/9/11.
 */

public interface IBookView extends IView{


    void onRequestSuccess(List<BookInfo> itemList);

    void onLoadMoreSuccess(List<BookInfo> itemList);

    void onRecommendSuccess(List<BookInfo> itemList);

    void hasNoMore();

    void showEmptyView();

    void onRequestFailed();

    void loadMoreFailed();
}
