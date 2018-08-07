package com.example.lxh.firstapp.home.songlist;

import com.example.lxh.firstapp.api.SearchApi;
import com.example.lxh.firstapp.base.core.http.RetrofitClient;
import com.example.lxh.firstapp.bean.SongList;
import com.example.lxh.firstapp.bean.response.SongListResponse;
import com.example.lxh.firstapp.home.IDataLoadListener;
import com.example.lxh.firstapp.home.IListModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxh on 2018/8/7.
 */

public class SongListModel implements IListModel<SongList> {

    @Override
    public void requestData(String key, int page, int pageNum, String uid, final IDataLoadListener<SongList> loadListener) {
        RetrofitClient.getInstance(SearchApi.SEARCH_URL).createService(SearchApi.class).searchSongList(key, page, pageNum, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SongListResponse>() {
                    @Override
                    public void accept(@NonNull SongListResponse songListResponse) throws Exception {
                        loadListener.onSuccess(songListResponse.getAbslist());
                    }
                });
    }
}
