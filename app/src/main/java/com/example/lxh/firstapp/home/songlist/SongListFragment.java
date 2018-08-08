package com.example.lxh.firstapp.home.songlist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.SongList;
import com.example.lxh.firstapp.home.IListView;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class SongListFragment extends BaseMVPFragment<SongListPresenter, IListView> implements IListView<SongList> {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected SongListPresenter createPresenter() {
        return new SongListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_album_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_album_list);
        mRefreshLayout.setEnabled(false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycle_album_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onRequestSuccess(List<SongList> itemList) {
        showContentView();
        mRecyclerView.setAdapter(new SongListAdapter(R.layout.home_songlist_item_layout, itemList));
    }
}
