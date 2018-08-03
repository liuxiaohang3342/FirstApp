package com.example.lxh.firstapp.home;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.Album;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public class AlbumPresenter extends BasePresenter<IAlbumView> implements IDataLoadListener {

    private IAlbumModel mAlbumModel = new AlbumModel();

    public AlbumPresenter(IAlbumView iView) {
        super(iView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }

    public void requestData() {
        mAlbumModel.requestData("周杰伦", 0, 100, "", this);
    }

    @Override
    public void onSuccess(List<Album> albumList) {
        if (getView() == null) {
            return;
        }
        getView().onSuccess(albumList);
    }
}
