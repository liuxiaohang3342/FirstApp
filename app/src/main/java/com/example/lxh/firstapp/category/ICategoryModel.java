package com.example.lxh.firstapp.category;

/**
 * Created by lxh on 2018/8/10.
 */

public interface ICategoryModel {

    void requestCategories(ICategoryDataListener listener);

    void requestSubCategories(String category, final ICategoryDataListener listener);
}
