package com.example.lxh.firstapp.category.sub;

import com.example.lxh.firstapp.bean.ContentInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public interface ISubCategoryDataListener {

    void onContentSuccess(List<ContentInfo> infoList);

    void onContentFail();
}
