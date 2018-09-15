package com.example.lxh.firstapp.category.sub;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.ContentInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public interface ISubCategoryView extends IView {
    void showEmptyView();

    void showErrorView();

    void onNoMore();

    void onRequestSuccess(List<ContentInfo> infoList);

    void onLoadMoreSuccess(List<ContentInfo> infoList);

}
