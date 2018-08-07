package com.example.lxh.firstapp.home.songlist;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.SongList;
import com.example.lxh.firstapp.home.IListView;
import com.example.lxh.firstapp.home.IDataLoadListener;
import com.example.lxh.firstapp.home.IListModel;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class SongListPresenter extends BasePresenter<IListView> implements IDataLoadListener<SongList> {

    private IListModel<SongList> mModel = new SongListModel();

    public SongListPresenter(IListView iSongListView) {
        super(iSongListView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }


    public void requestData() {
        mModel.requestData("周杰伦", 0, 100, "", this);
    }

    @Override
    public void onSuccess(List<SongList> itemList) {
        if (getView() == null) {
            return;
        }
        getView().onSuccess(itemList);
    }
}
