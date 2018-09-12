package com.example.lxh.firstapp.api;

import com.example.lxh.firstapp.base.core.http.RetrofitClient;
import com.example.lxh.firstapp.base.core.http.exception.ErrorResumeFunction;
import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.bean.ContentInfo;
import com.example.lxh.firstapp.bean.ExtraBookInfo;
import com.example.lxh.firstapp.bean.GirlInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.bean.response.BookListResponse;
import com.example.lxh.firstapp.bean.response.BookResponse;
import com.example.lxh.firstapp.bean.response.DataResponse;
import com.example.lxh.firstapp.bean.response.HistoryResponse;
import com.example.lxh.firstapp.bean.response.TodayResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxh on 2018/8/8.
 */

public class HttpMethod {

    static <T> Observable<T> packObservable(Observable<T> observable) {
        return observable
                //拦截服务器返回的错误
//                .map(new ResponseFunction())
                //ErrorResumeFunction（）为拦截onError事件的拦截器
                .onErrorResumeNext(new ErrorResumeFunction<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static void day(final IListener<TodayResponse> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).today())
                .subscribe(new Consumer<TodayResponse>() {
                    @Override
                    public void accept(@NonNull TodayResponse todayResponse) throws Exception {
                        listener.onSuccess(todayResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }

    public static void reqestDataByDate(int year, int month, int day, final IListener<TodayResponse> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).reqestDataByDate(year, month, day))
                .subscribe(new Consumer<TodayResponse>() {
                    @Override
                    public void accept(@NonNull TodayResponse todayResponse) throws Exception {
                        listener.onSuccess(todayResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }


    public static void dayHistory(final IListener<HistoryResponse> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).dayHistory())
                .subscribe(new Consumer<HistoryResponse>() {
                    @Override
                    public void accept(@NonNull HistoryResponse historyResponse) throws Exception {
                        listener.onSuccess(historyResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }


    public static void getGirlData(int num, int page, final IListener<DataResponse<GirlInfo>> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).getGirlData(num, page))
                .subscribe(new Consumer<DataResponse>() {
                    @Override
                    public void accept(@NonNull DataResponse historyResponse) throws Exception {
                        listener.onSuccess(historyResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }

    public static void getGategories(final IListener<DataResponse<CategoryInfo>> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).getCategories()).subscribe(new Consumer<DataResponse<CategoryInfo>>() {
            @Override
            public void accept(@NonNull DataResponse<CategoryInfo> categoryInfoDataResponse) throws Exception {
                listener.onSuccess(categoryInfoDataResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                listener.onError(throwable);
            }
        });
    }

    public static void getSubGategories(String category, final IListener<DataResponse<SubCategoryInfo>> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).getSubCategories(category)).subscribe(new Consumer<DataResponse<SubCategoryInfo>>() {
            @Override
            public void accept(@NonNull DataResponse<SubCategoryInfo> categoryInfoDataResponse) throws Exception {
                listener.onSuccess(categoryInfoDataResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                listener.onError(throwable);
            }
        });
    }

    public static void getSubCategoriesData(String id, int count, int page, final IListener<DataResponse<ContentInfo>> listener) {
        packObservable(RetrofitClient.getInstance(GankApi.GANK_URL).createService(GankApi.class).getSubCategoriesData(id, count, page)).subscribe(new Consumer<DataResponse<ContentInfo>>() {
            @Override
            public void accept(@NonNull DataResponse<ContentInfo> categoryInfoDataResponse) throws Exception {
                listener.onSuccess(categoryInfoDataResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                listener.onError(throwable);
            }
        });
    }

    public static void getRecommendBook(final IListener<BookResponse<List<BookInfo>>> listener) {
        packObservable(RetrofitClient.getInstance(BookApi.OPEN_API).createService(BookApi.class).recommendBook()).subscribe(new Consumer<BookResponse<List<BookInfo>>>() {
            @Override
            public void accept(@NonNull BookResponse<List<BookInfo>> categoryInfoDataResponse) throws Exception {
                listener.onSuccess(categoryInfoDataResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                listener.onError(throwable);
            }
        });
    }

    public static void getBookList(int page, final IListener<BookResponse<BookListResponse>> listener) {
        packObservable(RetrofitClient.getInstance(BookApi.BOOK_STORE_API).createService(BookApi.class).getRecommendBook()).subscribe(new Consumer<BookResponse<BookListResponse>>() {
            @Override
            public void accept(@NonNull BookResponse<BookListResponse> categoryInfoDataResponse) throws Exception {
                listener.onSuccess(categoryInfoDataResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                listener.onError(throwable);
            }
        });
    }

    public static void getBookExtraInfo(String bookId, String bookName, String author, String authorId, String tk, final IListener<BookResponse<ExtraBookInfo>> listener) {
        packObservable(RetrofitClient.getInstance(BookApi.BOOK_API).createService(BookApi.class).getAuthorOtherBook(bookId, bookName, author, authorId, tk, System.currentTimeMillis())).subscribe(new Consumer<BookResponse<ExtraBookInfo>>() {
            @Override
            public void accept(@NonNull BookResponse<ExtraBookInfo> categoryInfoDataResponse) throws Exception {
                listener.onSuccess(categoryInfoDataResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                listener.onError(throwable);
            }
        });
    }

}
