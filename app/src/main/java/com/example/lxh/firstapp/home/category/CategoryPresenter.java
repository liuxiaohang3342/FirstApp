package com.example.lxh.firstapp.home.category;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryPresenter extends BasePresenter<ICategoryView> {

    ICategoryModel mCategoryModel = new CategoryModel();

    public CategoryPresenter(ICategoryView iCategoryView) {
        super(iCategoryView);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestory() {

    }
}
