package com.example.lxh.firstapp.home;

/**
 * Created by lxh on 2018/8/3.
 */

public interface IAlbumModel {
    void requestData(String key, int page, int pageNum, String uid, IDataLoadListener loadListener);
}
