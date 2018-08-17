package com.example.lxh.firstapp.category.sub;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
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
}
