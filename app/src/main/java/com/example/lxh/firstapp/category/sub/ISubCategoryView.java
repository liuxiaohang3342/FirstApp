package com.example.lxh.firstapp.category.sub;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.SubCategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public interface ISubCategoryView extends IView {
    void showEmptyView();

    void onDataSuccess(List<SubCategoryInfo> infoList);

    void showErrorView();
}
