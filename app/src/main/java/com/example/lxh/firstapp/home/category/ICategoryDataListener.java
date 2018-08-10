package com.example.lxh.firstapp.home.category;

import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/10.
 */

public interface ICategoryDataListener {

    void onCategoriesSuccess(List<CategoryInfo> infoList);

    void onCategoriesFail();

    void onSubCategoriesSuccess(List<SubCategoryInfo> infoList);

    void onSubCategoriesFail();

}
