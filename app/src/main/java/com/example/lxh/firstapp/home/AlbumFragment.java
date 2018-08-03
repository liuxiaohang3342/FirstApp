package com.example.lxh.firstapp.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.Album;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public class AlbumFragment extends BaseMVPFragment<AlbumPresenter, IAlbumView> implements IAlbumView {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected AlbumPresenter createPresenter() {
        return new AlbumPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_album_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_album_list);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycle_album_list);
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onSuccess(List<Album> albumList) {
        showContentView();
        mRecyclerView.setAdapter(new AlbumAdapter(R.layout.home_album_item_layout, albumList));
    }
}
