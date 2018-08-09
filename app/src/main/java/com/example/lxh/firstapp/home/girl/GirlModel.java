package com.example.lxh.firstapp.home.girl;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.GirlInfo;
import com.example.lxh.firstapp.bean.response.DataResponse;
import com.example.lxh.firstapp.home.IDataLoadListener;

/**
 * Created by lxh on 2018/8/9.
 */

public class GirlModel implements IGirlModel {

    @Override
    public void requestData(int num, int page, final IDataLoadListener<GirlInfo> loadListener) {
        HttpMethod.getGirlData(num, page, new IListener<DataResponse<GirlInfo>>() {
            @Override
            public void onSuccess(DataResponse<GirlInfo> data) {
                loadListener.onSuccess(data.getResults());
            }

            @Override
            public void onError(Throwable throwable) {
                loadListener.onError();
            }
        });
    }
}
