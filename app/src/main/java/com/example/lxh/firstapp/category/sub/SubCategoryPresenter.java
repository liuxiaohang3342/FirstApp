package com.example.lxh.firstapp.category.sub;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.SubCategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public class SubCategoryPresenter extends BasePresenter<ISubCategoryView> implements ISubCategoryDataListener {

    ISubCategoryModel mISubCategoryModel = new SubCategoryModel();

    public SubCategoryPresenter(ISubCategoryView iSubCategoryView) {
        super(iSubCategoryView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }

    public void requestData(String key) {
        mISubCategoryModel.requestSubCategories(key, this);
    }

    @Override
    public void onSubCategoriesSuccess(List<SubCategoryInfo> infoList) {
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
    public void onSubCategoriesFail() {
        if (getView() == null) {
            return;
        }
        getView().showErrorView();
    }
}
