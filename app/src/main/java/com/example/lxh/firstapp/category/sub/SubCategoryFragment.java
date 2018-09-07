package com.example.lxh.firstapp.category.sub;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.bean.ContentInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.category.CategoryAdapter;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public class SubCategoryFragment extends BaseMVPFragment<SubCategoryPresenter, ISubCategoryView> implements ISubCategoryView, CategoryAdapter.IGetTitle {
    private static final String KEY_NAME = "name", KEY_EN_NAME = "en_name";

    private String name;
    private String en_name;

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private SubCategoryAdapter mCategoryAdapter;

    public static SubCategoryFragment newInstance(CategoryInfo categoryInfo) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_EN_NAME, categoryInfo.getEn_name());
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected SubCategoryPresenter createPresenter() {
        return new SubCategoryPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            en_name = bundle.getString(KEY_EN_NAME);
        }
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCategoryAdapter = new SubCategoryAdapter(R.layout.content_item_layout, null);
        mCategoryAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mCategoryAdapter.setEnableLoadMore(true);
        mCategoryAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().loadMoreContent();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mCategoryAdapter);
        showLoadingView();
        getPresenter().requestSubCategory(en_name);
    }

    @Override
    public void onSubCategorySuccess(List<SubCategoryInfo> infoList) {

    }

    @Override
    public void onNoMore() {
        mCategoryAdapter.loadMoreEnd(true);
    }

    @Override
    public void onContentSuccess(List<ContentInfo> infoList) {
        showContentView();
        mCategoryAdapter.loadMoreComplete();
        mCategoryAdapter.addData(infoList);
    }


    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestSubCategory(en_name);
    }

    @Override
    public String getTitle() {
        return name;
    }
}
