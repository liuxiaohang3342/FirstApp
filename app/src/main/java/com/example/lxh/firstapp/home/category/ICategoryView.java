package com.example.lxh.firstapp.home.category;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.CategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public interface ICategoryView extends IView {

    void showEmptyView();

    void onDataSuccess(List<CategoryInfo> infoList);

    void showErrorView();
}
