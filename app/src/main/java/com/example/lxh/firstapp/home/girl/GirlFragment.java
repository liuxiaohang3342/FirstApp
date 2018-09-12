package com.example.lxh.firstapp.home.girl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.GirlInfo;
import com.example.lxh.firstapp.img.ImageActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.lxh.firstapp.home.girl.Constant.KEY_POSITION;
import static com.example.lxh.firstapp.home.girl.Constant.KEY_URL_LIST;

/**
 * Created by lxh on 2018/8/9.
 */

public class GirlFragment extends BaseMVPFragment<GirlPresenter, IGrilView> implements IGrilView, BaseQuickAdapter.OnItemClickListener {


    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private GirlAdapter mGirlAdapter;

    @Override
    protected GirlPresenter createPresenter() {
        return new GirlPresenter(this);
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
                getPresenter().requestData();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycle_album_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mGirlAdapter = new GirlAdapter(R.layout.girl_item_layout, null);
        mGirlAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mGirlAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().loadMore();
            }
        }, mRecyclerView);
        mGirlAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mGirlAdapter);
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getActivity() == null) {
            return;
        }
        if (isVisibleToUser) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onNoMore() {
        if (mGirlAdapter != null) {
            mGirlAdapter.loadMoreEnd();
        }
    }

    @Override
    public void addData(List<GirlInfo> girlInfoList) {
        if (mGirlAdapter == null) {
            return;
        }
        mGirlAdapter.loadMoreComplete();
        mGirlAdapter.addData(girlInfoList);
    }

    @Override
    public void setNewData(List<GirlInfo> girlInfoList) {
        showContentView();
        mRefreshLayout.setRefreshing(false);
        mGirlAdapter.setNewData(girlInfoList);
    }

    @Override
    public void onError() {
        if (mGirlAdapter.getData() == null || mGirlAdapter.getData().size() == 0) {
            showErrorView();
            return;
        }
        mGirlAdapter.loadMoreFail();
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArrayList<String> list = new ArrayList<>();
        List<GirlInfo> girlInfos = mGirlAdapter.getData();
        for (GirlInfo info : girlInfos) {
            list.add(info.getUrl());
        }
        Intent intent = new Intent(getContext(), ImageActivity.class);
        intent.putExtra(KEY_POSITION, position);
        intent.putExtra(KEY_URL_LIST, list);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.scale_in, 0);
    }
}
