package com.example.lxh.firstapp.bookdetail;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.AuthorInfo;
import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bean.ExtraBookInfo;
import com.example.lxh.firstapp.bean.SimpleBookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/9/12.
 */

public class BookDetailPresenter extends BasePresenter<IBookDetailView> implements IDataLoadListener<ExtraBookInfo> {

    private IBookDetailModel mBookDetailModel = new BookDetailModel();

    public BookDetailPresenter(IBookDetailView iBookDetailView) {
        super(iBookDetailView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }

    public void requestData(BookInfo info) {
        mBookDetailModel.requestExtraInfo(info.getBid(), info.getBookname(), info.getAuthor_name(), info.getAuthor(), "", this);
    }


    @Override
    public void onSuccess(ExtraBookInfo info) {
        if (getView() == null) {
            return;
        }
        if (info == null) {
            hideView();
            return;
        }
        AuthorInfo authorInfo = info.getAt();
        if (authorInfo == null) {
            getView().hideAuthorView();
            getView().hideOtherBookView();
            return;
        }
        getView().showAuthorInfo(authorInfo.getIntroduction());
        List<SimpleBookInfo> otherBookList = authorInfo.getBookinfo();
        if (otherBookList == null || otherBookList.isEmpty()) {
            getView().hideOtherBookView();
            return;
        }
        getView().showOtherBookView(otherBookList);

        ExtraBookInfo.SimilarBook similarBook = info.getDk();
        if (similarBook == null || similarBook.getBookinfo() == null || similarBook.getBookinfo().isEmpty()) {
            getView().hideSimilarView();
            return;
        }
        getView().showSimilarView(similarBook.getBookinfo());
    }

    @Override
    public void onError() {
        if (getView() == null) {
            return;
        }
        hideView();
    }

    private void hideView() {
        getView().hideAuthorView();
        getView().hideOtherBookView();
        getView().hideSimilarView();
    }
}
