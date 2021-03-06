package com.example.lxh.firstapp.api;

import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.bean.ContentInfo;
import com.example.lxh.firstapp.bean.GirlInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.bean.response.DataResponse;
import com.example.lxh.firstapp.bean.response.HistoryResponse;
import com.example.lxh.firstapp.bean.response.TodayResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lxh on 2018/8/8.
 */
interface GankApi {

    String GANK_URL = "http://gank.io/api/";

    @GET("today")
    Observable<TodayResponse> today();

    @GET("day/{year}/{month}/{day}")
    Observable<TodayResponse> reqestDataByDate(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("day/history")
    Observable<HistoryResponse> dayHistory();

    @GET("data/福利/{num}/{page}")
    Observable<DataResponse<GirlInfo>> getGirlData(@Path("num") int num, @Path("page") int page);

    @GET("xiandu/categories")
    Observable<DataResponse<CategoryInfo>> getCategories();

    @GET("xiandu/category/{category}")
    Observable<DataResponse<SubCategoryInfo>> getSubCategories(@Path("category") String category);

    @GET("xiandu/data/id/{id}/count/{count}/page/{page}")
    Observable<DataResponse<ContentInfo>> getSubCategoriesData(@Path("id") String id, @Path("count") int count, @Path("page") int page);

}
