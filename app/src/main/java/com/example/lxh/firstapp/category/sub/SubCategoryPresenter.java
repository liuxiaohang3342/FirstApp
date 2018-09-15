package com.example.lxh.firstapp.category.sub;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.ContentInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public class SubCategoryPresenter extends BasePresenter<ISubCategoryView> implements ISubCategoryDataListener {

    private static final int PAGE_NUM = 10;

    ISubCategoryModel mISubCategoryModel = new SubCategoryModel();

    private int mPage = 1;
    private boolean mHasMore;


    public SubCategoryPresenter(ISubCategoryView iSubCategoryView) {
        super(iSubCategoryView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }

    public void requestContentByCategory(String id) {
        mPage = 1;
        mISubCategoryModel.requestSubCategoriesData(id, PAGE_NUM, mPage, this);
    }

    public void loadMoreContent(String id) {
        if (!mHasMore) {
            if (getView() != null) {
                getView().onNoMore();
            }
            return;
        }
        mISubCategoryModel.requestSubCategoriesData(id, mPage, PAGE_NUM, this);
    }


    @Override
    public void onContentSuccess(List<ContentInfo> infoList) {
        if (getView() == null) {
            return;
        }
        if (infoList == null || infoList.size() == 0) {
            getView().showEmptyView();
            return;
        }
        if (infoList.size() < PAGE_NUM) {
            mHasMore = false;
        }
        getView().onContentSuccess(infoList);
        mPage++;
    }

    @Override
    public void onContentFail() {
        if (getView() == null) {
            return;
        }
        getView().showErrorView();
    }
}
