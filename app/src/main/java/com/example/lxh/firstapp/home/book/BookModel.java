package com.example.lxh.firstapp.home.book;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bean.ModuleInfo;
import com.example.lxh.firstapp.bean.response.BookListResponse;
import com.example.lxh.firstapp.bean.response.BookResponse;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class BookModel implements IBookModel {

    @Override
    public void requestData(int page, final IDataLoadListener loadListener) {
        HttpMethod.getBookList(page, new IListener<BookResponse<BookListResponse>>() {
            @Override
            public void onSuccess(BookResponse<BookListResponse> data) {
                if (loadListener != null) {
                    BookListResponse response = data.getData();
                    if (response != null) {
                        List<ModuleInfo> module = response.getModule();
                        if (module != null && !module.isEmpty()) {
                            loadListener.onDataSuccess(module.get(0).getContent());
                            return;
                        }
                    }
                    loadListener.onDataSuccess(null);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (loadListener != null) {
                    loadListener.onError();
                }
            }
        });
    }

    @Override
    public void requestRecommendData(final IDataLoadListener loadListener) {
        HttpMethod.getRecommendBook(new IListener<BookResponse<List<BookInfo>>>() {
            @Override
            public void onSuccess(BookResponse<List<BookInfo>> data) {
                if (loadListener != null) {
                    loadListener.onRecommendSuccess(data.getData());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (loadListener != null) {
                    loadListener.onError();
                }
            }
        });
    }
}
