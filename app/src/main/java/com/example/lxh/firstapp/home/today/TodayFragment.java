package com.example.lxh.firstapp.home.today;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.SourceInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayFragment extends BaseMVPFragment<TodayPresenter, ITodayView> implements ITodayView<SourceInfo> {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private TodayAdapter mTodayAdapter;


    @Override
    protected TodayPresenter createPresenter() {
        return new TodayPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_album_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_album_list);
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().requestToday();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycle_album_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTodayAdapter = new TodayAdapter(R.layout.today_text_layout, null);
        mTodayAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mTodayAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().loadMore();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mTodayAdapter);
        showLoadingView();
        getPresenter().requestToday();
    }

    @Override
    public void onRequestSuccess(List<SourceInfo> itemList) {
        showContentView();
        mRefreshLayout.setRefreshing(false);
        mTodayAdapter.setNewData(itemList);
    }

    @Override
    public void onLoadMoreSuccess(List<SourceInfo> itemList) {
        mTodayAdapter.addData(itemList);
        mTodayAdapter.loadMoreComplete();
    }

    @Override
    public void onNoMore() {
        mTodayAdapter.loadMoreEnd();
    }

    @Override
    public void onError() {
        mTodayAdapter.loadMoreFail();
        if (mTodayAdapter.getData() == null || mTodayAdapter.getData().size() == 0) {
            showErrorView();
            return;
        }
        Toast.makeText(getContext(), getResources().getString(R.string.request_error), Toast.LENGTH_SHORT).show();
    }
}
