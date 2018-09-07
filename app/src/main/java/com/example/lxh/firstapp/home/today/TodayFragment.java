package com.example.lxh.firstapp.home.today;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.bean.SourceInfo;
import com.example.lxh.firstapp.category.CategoryActivity;
import com.example.lxh.firstapp.common.CommonBannerPresenter;
import com.example.lxh.firstapp.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayFragment extends BaseMVPFragment<TodayPresenter, ITodayView> implements ITodayView<SourceInfo>, BaseQuickAdapter.OnItemClickListener, AppBarLayout.OnOffsetChangedListener, CommonBannerPresenter.BannerListener {

    private static final String GANK_HOME_URL = "https://gank.io/xiandu";

    private RecyclerView mRecyclerView;
    private TodayAdapter mTodayAdapter;
    private AppBarLayout mAppBarLayout;
    private View mHeaderView;
    private ImageConfig mImageConfig;


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
        mImageConfig = new ImageConfig.Builder().setLoadingRes(R.drawable.img_four_bi_three).setFailureDrawable(R.drawable.img_four_bi_three).create();
        addHeaderView();
        addBanner(view);
        showLoadingView();
        getPresenter().requestToday();
    }

    private void addHeaderView() {
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.recommend_header_layout, mRecyclerView, false);
        mHeaderView.findViewById(R.id.tv_gank).setOnClickListener(this);
        mHeaderView.findViewById(R.id.tv_category).setOnClickListener(this);
        mHeaderView.findViewById(R.id.tv_joker).setOnClickListener(this);
        mTodayAdapter.addHeaderView(mHeaderView);
    }

    private ArrayList<String> mUrlList = new ArrayList<>();

    {
        mUrlList.add("http://pic.soutu123.com/back_pic/00/00/40/82/7133a08eb7e18053c1d218bc799fd7ea.jpg!/fw/700/quality/100/unsharp/true/compress/true");
        mUrlList.add("http://pic.soutu123.com/back_pic/18/07/19/fa9d07804f2cb0f7e35b6745a06c3b60.jpg!/fw/700/quality/90/unsharp/true/compress/true");
        mUrlList.add("http://pic.soutu123.com/back_pic/18/08/07/462b32c156775461df87f112717438ce.jpg!/fw/700/quality/90/unsharp/true/compress/true");
        mUrlList.add("http://pic.soutu123.com/back_pic/00/15/66/75573ad8a85497e.jpg!/fw/700/quality/90/unsharp/true/compress/true");
        mUrlList.add("http://pic.soutu123.com/back_pic/00/03/74/53561f93062f858.jpg!/fw/700/quality/90/unsharp/true/compress/true");
        mUrlList.add("http://pic.soutu123.com/back_pic/18/07/19/ab299ec197f0826d7af49217322ed70a.jpg!/fw/700/quality/90/unsharp/true/compress/true");
    }


    private void addBanner(View view) {
        new CommonBannerPresenter<>(getContext(), mUrlList, this).setView(view);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public Object instantiate(ViewGroup container, int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.banner_item_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        ImageLoader.getInstance().load(imageView, mUrlList.get(position), mImageConfig);
        container.addView(view);
        return view;
    }

    @Override
    public View getView(ViewGroup viewGroup, int position) {
        return null;
    }
}

