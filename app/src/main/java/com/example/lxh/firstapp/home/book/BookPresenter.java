package com.example.lxh.firstapp.home.book;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.BookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class BookPresenter extends BasePresenter<IBookView> implements IDataLoadListener {

    private IBookModel mModel = new BookModel();
    private int mPage;

    public BookPresenter(IBookView iSongListView) {
        super(iSongListView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }


    public void requestData() {
        mPage = 0;
        mModel.requestRecommendData(this);
        mModel.requestData(mPage, this);
    }

    public void loadMore() {
        mModel.requestData(mPage, this);
    }

    @Override
    public void onDataSuccess(List<BookInfo> list) {
        if (getView() == null) {
            return;
        }
        mPage++;
        if (mPage == 1) {
            if (list == null || list.isEmpty()) {
                getView().showEmptyView();
            } else {
                getView().onRequestSuccess(list);
            }
            return;
        }
        if (list == null || list.isEmpty()) {
            getView().hasNoMore();
        } else {
            getView().onLoadMoreSuccess(list);
        }
    }

    @Override
    public void onRecommendSuccess(List<BookInfo> list) {
        if (getView() == null) {
            return;
        }
        getView().onRecommendSuccess(list);
    }

    @Override
    public void onError() {
        if (getView() == null) {
            return;
        }
        if (mPage == 0) {
            getView().onRequestFailed();
            return;
        }
        getView().loadMoreFailed();
    }
}
