package com.example.lxh.firstapp.bookdetail;

import com.example.lxh.firstapp.api.HttpMethod;
import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.bean.ExtraBookInfo;
import com.example.lxh.firstapp.bean.response.BookResponse;

/**
 * Created by lxh on 2018/9/12.
 */

public class BookDetailModel implements IBookDetailModel {

    /**
     * @param bookId
     * @param bookName
     * @param author
     * @param authorId
     * @param tk  不知道通过什么算出来的,对不上接口失败，只能先写死
     * @param listener
     */
    @Override
    public void requestExtraInfo(String bookId, String bookName, String author, String authorId, String tk, final IDataLoadListener<ExtraBookInfo> listener) {
        HttpMethod.getBookExtraInfo("3157284", "匹夫的逆袭", "骁骑校", "15673", "MzE1NzI4NDQxN2FjNTllOWYxNTY3Mw%3D%3D", new IListener<BookResponse<ExtraBookInfo>>() {
            @Override
            public void onSuccess(BookResponse<ExtraBookInfo> data) {
                if (listener != null) {
                    listener.onSuccess(data.getData());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (listener != null) {
                    listener.onError();
                }
            }
        });
    }

}
