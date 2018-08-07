package com.example.lxh.firstapp.home.album;

import com.example.lxh.firstapp.base.core.http.RetrofitClient;
import com.example.lxh.firstapp.bean.Album;
import com.example.lxh.firstapp.bean.response.AlbumResponse;
import com.example.lxh.firstapp.api.SearchApi;
import com.example.lxh.firstapp.home.IListModel;
import com.example.lxh.firstapp.home.IDataLoadListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxh on 2018/8/3.
 */

public class AlbumModel implements IListModel<Album> {


    @Override
    public void requestData(String key, int page, int pageNum, String uid, final IDataLoadListener<Album> loadListener) {
        RetrofitClient.getInstance(SearchApi.SEARCH_URL).createService(SearchApi.class).searchAlbum(key, page, pageNum, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AlbumResponse>() {
                    @Override
                    public void accept(@NonNull AlbumResponse albumResponse) throws Exception {
                        loadListener.onSuccess(albumResponse.getAlbumlist());
                    }
                });

    }
}
