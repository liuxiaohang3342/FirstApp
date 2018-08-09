package com.example.lxh.firstapp.home.girl;

import com.example.lxh.firstapp.bean.GirlInfo;
import com.example.lxh.firstapp.home.IDataLoadListener;

/**
 * Created by lxh on 2018/8/9.
 */

public interface IGirlModel {
    void requestData(int num, int page, IDataLoadListener<GirlInfo> listener);

}
