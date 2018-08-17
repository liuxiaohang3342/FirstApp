package com.example.lxh.firstapp.category;

import com.example.lxh.firstapp.bean.CategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/10.
 */

public interface ICategoryDataListener {

    void onCategoriesSuccess(List<CategoryInfo> infoList);

    void onCategoriesFail();
}
