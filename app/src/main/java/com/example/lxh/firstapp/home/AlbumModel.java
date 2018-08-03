package com.example.lxh.firstapp.home;

import com.example.lxh.firstapp.base.core.http.RetrofitClient;
import com.example.lxh.firstapp.bean.response.AlbumResponse;
import com.example.lxh.firstapp.network.SearchApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxh on 2018/8/3.
 */

public class AlbumModel implements IAlbumModel {


    @Override
    public void requestData(String key, int page, int pageNum, String uid, final IDataLoadListener loadListener) {
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
