package com.example.lxh.firstapp.home.girl;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.GirlInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/9.
 */

public interface IGrilView extends IView {

    void onNoMore();

    void addData(List<GirlInfo> girlInfoList);

    void setNewData(List<GirlInfo> girlInfoList);

    void onError();

}
