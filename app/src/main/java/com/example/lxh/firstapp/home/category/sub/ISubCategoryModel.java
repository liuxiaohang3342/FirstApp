package com.example.lxh.firstapp.home.category.sub;

/**
 * Created by lxh on 2018/8/13.
 */

public interface ISubCategoryModel {
    void requestSubCategories(String category, final ISubCategoryDataListener listener);
}
