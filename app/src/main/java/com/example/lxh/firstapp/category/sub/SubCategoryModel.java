package com.example.lxh.firstapp.category.sub;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.ContentInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.bean.response.DataResponse;

/**
 * Created by lxh on 2018/8/13.
 */

public class SubCategoryModel implements ISubCategoryModel {

    public void requestSubCategories(String category, final ISubCategoryDataListener listener) {
        HttpMethod.getSubGategories(category, new IListener<DataResponse<SubCategoryInfo>>() {
            @Override
            public void onSuccess(DataResponse<SubCategoryInfo> data) {
                if (listener != null) {
                    listener.onSubCategoriesSuccess(data.getResults());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (listener != null) {
                    listener.onSubCategoriesFail();
                }
            }
        });
    }

    public void requestSubCategoriesData(String id, int count, int page, final ISubCategoryDataListener listener) {
        HttpMethod.getSubCategoriesData(id, count, page, new IListener<DataResponse<ContentInfo>>() {
            @Override
            public void onSuccess(DataResponse<ContentInfo> data) {
                if (listener != null) {
                    listener.onContentSuccess(data.getResults());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (listener != null) {
                    listener.onContentFail();
                }
            }
        });
    }
}
