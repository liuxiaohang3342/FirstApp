package com.example.lxh.firstapp.category;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.bean.response.DataResponse;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryModel implements ICategoryModel {

    @Override
    public void requestCategories(final ICategoryDataListener listener) {
        HttpMethod.getGategories(new IListener<DataResponse<CategoryInfo>>() {
            @Override
            public void onSuccess(DataResponse<CategoryInfo> data) {
                if (listener != null) {
                    listener.onCategoriesSuccess(data.getResults());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (listener != null) {
                    listener.onCategoriesFail();
                }
            }
        });
    }

    public void requestSubCategories(String category, final ICategoryDataListener listener) {
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
}
