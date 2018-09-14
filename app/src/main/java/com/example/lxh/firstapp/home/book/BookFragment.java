package com.example.lxh.firstapp.home.book;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.banner.BannerIndicator;
import com.example.lxh.firstapp.banner.IndicatorAdapter;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bookdetail.BookDetailActivity;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 * <p>
 * 只用Recycler没有snap的效果
 */

public class BookFragment extends BaseMVPFragment<BookPresenter, IBookView> implements IBookView, BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener, AppBarLayout.OnOffsetChangedListener {

    private static final int INDEX = 1000;
    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;
    private ViewPager mViewPager;
    private BannerIndicator mIndicator;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private View mSearchView;
    private View mTitleView;


    @Override
    protected BookPresenter createPresenter() {
        return new BookPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_book_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.abl_book_list);
        mToolbar = (Toolbar) view.findViewById(R.id.tool_bar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycle_album_list);
        mSearchView = view.findViewById(R.id.tv_search);
        mTitleView = view.findViewById(R.id.tv_title);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBookAdapter = new BookAdapter(R.layout.home_book_item_layout, null);
        mBookAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mBookAdapter);
        mBookAdapter.setEnableLoadMore(true);
        mBookAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAppBarLayout.addOnOffsetChangedListener(this);
        showLoadingView();
        getPresenter().requestData();
    }

    private void addBanner(final List<BookInfo> arrayList) {
        mViewPager = (ViewPager) getView().findViewById(R.id.asvp_viewpager);
        mIndicator = (BannerIndicator) getView().findViewById(R.id.indicator);
        mViewPager.setAdapter(new BookHeaderAdapter(getContext(), arrayList));
        mIndicator.setAdapter(new IndicatorAdapter() {
            @Override
            public int getCount() {
                return arrayList.size();
            }

            @Override
            public View getView(ViewGroup viewGroup, int position) {
                return LayoutInflater.from(getContext()).inflate(R.layout.banner_indicator_layout, viewGroup, false);
            }
        });
        mViewPager.setPageTransformer(false, new BannerPageTransformer());
        mIndicator.setViewPager(mViewPager);
        mViewPager.setCurrentItem(INDEX);
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onRequestSuccess(List<BookInfo> itemList) {
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
            showEmptyView();
            return;
        }
        addBanner(itemList);
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
    public void onLoadMoreRequested() {
        getPresenter().loadMore();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BookInfo bookInfo = (BookInfo) adapter.getItem(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.list_img_v3);
        BookDetailActivity.start(getActivity(), imageView, bookInfo);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float percent = -verticalOffset * 1f / (appBarLayout.getHeight() - mToolbar.getHeight());
//        int color = getResources().getColor(R.color.colorTheme, null);
//        mToolbar.setBackgroundColor(Color.argb((int) (percent * 255), Color.red(color), Color.green(color), Color.blue(color)));
        mSearchView.setScaleX(1 - percent);
        mSearchView.setScaleY(1 - percent);
        if (percent == 1) {
            mTitleView.setVisibility(View.VISIBLE);
            mSearchView.setVisibility(View.GONE);
        } else {
            mTitleView.setVisibility(View.GONE);
            mSearchView.setVisibility(View.VISIBLE);
        }
    }

}
