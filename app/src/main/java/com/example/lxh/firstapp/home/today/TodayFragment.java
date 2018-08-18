package com.example.lxh.firstapp.home.today;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.SourceInfo;
import com.example.lxh.firstapp.category.CategoryActivity;
import com.example.lxh.firstapp.web.WebActivity;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayFragment extends BaseMVPFragment<TodayPresenter, ITodayView> implements ITodayView<SourceInfo>, BaseQuickAdapter.OnItemClickListener, AppBarLayout.OnOffsetChangedListener {

    private static final String GANK_HOME_URL = "https://gank.io/xiandu";

    private RecyclerView mRecyclerView;
    private TodayAdapter mTodayAdapter;
    private AppBarLayout mAppBarLayout;
    private View mHeaderView;

    @Override
    protected TodayPresenter createPresenter() {
        return new TodayPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_today_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycle_album_list);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTodayAdapter = new TodayAdapter(R.layout.today_text_layout, null);
        mTodayAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mTodayAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().loadMore();
            }
        }, mRecyclerView);
        mTodayAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mTodayAdapter);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_banner);
        viewPager.setAdapter(new BannerAdapter(getActivity()));
        addHeaderView();
        showLoadingView();
        getPresenter().requestToday();
    }

    private void addHeaderView() {
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.recommend_header_layout, mRecyclerView, false);
//        ViewPager viewPager = (ViewPager) headerView.findViewById(R.id.vp_banner);
//        viewPager.setAdapter(new BannerAdapter(getActivity()));
        mHeaderView.findViewById(R.id.tv_gank).setOnClickListener(this);
        mHeaderView.findViewById(R.id.tv_category).setOnClickListener(this);
        mHeaderView.findViewById(R.id.tv_joker).setOnClickListener(this);
        mTodayAdapter.addHeaderView(mHeaderView);
    }

    @Override
    public void onRequestSuccess(List<SourceInfo> itemList) {
        showContentView();
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_gank:
                jumpToWeb(GANK_HOME_URL);
                break;
            case R.id.tv_category:
                jumpToCategory();
                break;
            case R.id.tv_joker:
                break;
        }
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SourceInfo info = mTodayAdapter.getItem(position);
        jumpToWeb(info.getUrl());
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestToday();
    }

    private void jumpToWeb(String url) {
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra(Constant.KEY_URL, url);
        startActivity(intent);
    }

    private void jumpToCategory() {
        Intent intent = new Intent(getContext(), CategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (Math.abs(verticalOffset) < 40) {
            mHeaderView.setTranslationY(-40 - verticalOffset);
        } else {
            mHeaderView.setTranslationY(0);
        }
    }
}

