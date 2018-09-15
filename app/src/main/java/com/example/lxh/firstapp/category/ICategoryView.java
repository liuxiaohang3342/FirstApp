package com.example.lxh.firstapp.category;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public interface ICategoryView extends IView {

    void showEmptyView();

    void onDataSuccess(List<CategoryInfo> infoList);

    void showErrorView();

    void onSubCategoryFail();

    void onSubCategorySuccess(List<SubCategoryInfo> infoList);

}
