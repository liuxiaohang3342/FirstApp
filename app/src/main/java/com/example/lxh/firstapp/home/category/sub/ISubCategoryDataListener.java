package com.example.lxh.firstapp.home.category.sub;

import com.example.lxh.firstapp.bean.SubCategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public interface ISubCategoryDataListener {

    void onSubCategoriesSuccess(List<SubCategoryInfo> infoList);

    void onSubCategoriesFail();
}
