package com.example.lxh.firstapp.home.girl;

import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.GirlInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/9.
 */

public interface IGirlModel {
    void requestData(int num, int page, IListener<List<GirlInfo>> listener);

}
