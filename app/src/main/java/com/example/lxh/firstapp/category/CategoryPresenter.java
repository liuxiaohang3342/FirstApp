package com.example.lxh.firstapp.category;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.CategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryPresenter extends BasePresenter<ICategoryView> implements ICategoryDataListener {

    ICategoryModel mCategoryModel = new CategoryModel();

    public CategoryPresenter(ICategoryView iCategoryView) {
        super(iCategoryView);
    }

    @Override
    public void onCreate() {
        mCategoryModel.requestCategories(this);
    }

    @Override
    public void onDestory() {
    }

    public void requestData(){
        mCategoryModel.requestCategories(this);
    }

    @Override
    public void onCategoriesSuccess(List<CategoryInfo> infoList) {
        if (getView() == null) {
            return;
        }
        if (infoList == null || infoList.size() == 0) {
            getView().showEmptyView();
            return;
        }
        getView().onDataSuccess(infoList);
    }

    @Override
    public void onCategoriesFail() {
        if (getView() == null) {
            getView().showErrorView();
        }
    }
}
