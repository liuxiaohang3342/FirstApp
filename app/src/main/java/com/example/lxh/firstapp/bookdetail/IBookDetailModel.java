package com.example.lxh.firstapp.bookdetail;

import com.example.lxh.firstapp.bean.ExtraBookInfo;

/**
 * Created by lxh on 2018/9/12.
 */

public interface IBookDetailModel {

    void requestExtraInfo(String bookId, String bookName, String author, String authorId, String tk, IDataLoadListener<ExtraBookInfo> listener);
}
