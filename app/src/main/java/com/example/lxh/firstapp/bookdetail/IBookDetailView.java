package com.example.lxh.firstapp.bookdetail;

import com.example.lxh.firstapp.base.core.mvp.IView;
import com.example.lxh.firstapp.bean.AuthorInfo;
import com.example.lxh.firstapp.bean.SimpleBookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/9/12.
 */

public interface IBookDetailView extends IView {

    void showSimilarView(List<SimpleBookInfo> bookInfoList);

    void showAuthorInfo(String authorInfo);

    void showOtherBookView(List<SimpleBookInfo> bookInfoList);

    void hideSimilarView();

    void hideAuthorView();

    void hideOtherBookView();

}
