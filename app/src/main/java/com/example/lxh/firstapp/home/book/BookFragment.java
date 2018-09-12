package com.example.lxh.firstapp.home.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bookdetail.BookDetailActivity;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class BookFragment extends BaseMVPFragment<BookPresenter, IBookView> implements IBookView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;
    private View mHeaderView;
    private BookHeaderAdapter mBookHeaderAdapter;

    @Override
    protected BookPresenter createPresenter() {
        return new BookPresenter(this);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBookAdapter = new BookAdapter(R.layout.home_book_item_layout, null);
        mBookAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mBookAdapter);
        mBookAdapter.setEnableLoadMore(true);
        mBookAdapter.setOnLoadMoreListener(this, mRecyclerView);
        addHeaderView();
        initRefreshLayout();
        showLoadingView();
        getPresenter().requestData();
    }

    private void addHeaderView() {
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.book_header_layout, mRecyclerView, false);
        RecyclerView recyclerView = (RecyclerView) mHeaderView.findViewById(R.id.rlv_book_header);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBookHeaderAdapter = new BookHeaderAdapter(R.layout.book_header_item_layout, null);
        mBookHeaderAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mBookHeaderAdapter);
        mBookAdapter.addHeaderView(mHeaderView);
    }

    private void initRefreshLayout() {
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mRefreshLayout.setProgressViewOffset(true, 5, 20);
        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        // 通过 setEnabled(false) 禁用下拉刷新
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onRequestSuccess(List<BookInfo> itemList) {
        mRefreshLayout.setRefreshing(false);
        showContentView();
        mBookAdapter.setNewData(itemList);
    }

    @Override
    public void onLoadMoreSuccess(List<BookInfo> itemList) {
        mBookAdapter.loadMoreComplete();
        mBookAdapter.addData(itemList);
    }

    @Override
    public void onRecommendSuccess(List<BookInfo> itemList) {
        if (itemList == null || itemList.isEmpty()) {
            mHeaderView.setVisibility(View.GONE);
            return;
        }
        mHeaderView.setVisibility(View.VISIBLE);
        mBookHeaderAdapter.setNewData(itemList);
    }

    @Override
    public void hasNoMore() {
        mBookAdapter.loadMoreEnd();
    }

    @Override
    public void onRequestFailed() {
        if (mBookAdapter.getData() == null) {
            showEmptyView();
            return;
        }
        Toast.makeText(getContext(), "刷新失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMoreFailed() {
        mBookAdapter.loadMoreFail();
    }

    @Override
    public void onRefresh() {
        getPresenter().requestData();
    }

    @Override
    public void onLoadMoreRequested() {
        getPresenter().loadMore();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BookInfo bookInfo = (BookInfo) adapter.getItem(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.list_img_v3);
        BookDetailActivity.start(getActivity(), imageView, bookInfo);
    }
}
