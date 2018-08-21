package com.example.lxh.firstapp.base.core.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.Toolbar;

/**
 * Created by lxh on 2018/7/26.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private View mErrorView;
    private View mEmptyView;
    private View mLoadingView;
    private View mContentView;

    private AnimationDrawable mLoadingDrawable;
    private Toolbar mToolbar;

    private Bundle mBundle;

    public BaseFragment() {
    }

    public BaseFragment(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(getActivity()).resumeRequests();
    }

    @Override
    public void onPause() {
        super.onPause();
        Glide.with(getActivity()).pauseRequests();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        FrameLayout containerView = (FrameLayout) view.findViewById(R.id.fl_container);
        mContentView = inflater.inflate(getLayoutId(), containerView, false);
        containerView.addView(mContentView, 0);
        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isNeedToolBar()) {
            ViewStub viewStub = (ViewStub) view.findViewById(R.id.vs_toolbar);
            mToolbar = new Toolbar(viewStub.inflate());
        }
        initView();
    }

    private void initView() {
        mEmptyView = getView(R.id.ll_empty);
        mErrorView = getView(R.id.ll_error);
        mErrorView.setOnClickListener(this);
        mLoadingView = getView(R.id.ll_loading);
        ImageView img = getView(R.id.img_loading);
        mLoadingDrawable = (AnimationDrawable) img.getDrawable();
        if (!mLoadingDrawable.isRunning()) {
            mLoadingDrawable.start();
        }
    }

    private <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    public boolean isNeedToolBar() {
        return false;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void showErrorView() {
        if (mLoadingDrawable.isRunning()) {
            mLoadingDrawable.stop();
        }
        setViewVisibility(mLoadingView, View.GONE);
        setViewVisibility(mEmptyView, View.GONE);
        setViewVisibility(mContentView, View.GONE);
        setViewVisibility(mErrorView, View.VISIBLE);
    }

    public void showEmptyView() {
        if (mLoadingDrawable.isRunning()) {
            mLoadingDrawable.stop();
        }
        setViewVisibility(mLoadingView, View.GONE);
        setViewVisibility(mErrorView, View.GONE);
        setViewVisibility(mContentView, View.GONE);
        setViewVisibility(mEmptyView, View.VISIBLE);
    }


    public void showLoadingView() {
        if (!mLoadingDrawable.isRunning()) {
            mLoadingDrawable.start();
        }
        setViewVisibility(mEmptyView, View.GONE);
        setViewVisibility(mErrorView, View.GONE);
        setViewVisibility(mContentView, View.GONE);
        setViewVisibility(mLoadingView, View.VISIBLE);
    }

    public void showContentView() {
        if (mLoadingDrawable.isRunning()) {
            mLoadingDrawable.stop();
        }
        setViewVisibility(mEmptyView, View.GONE);
        setViewVisibility(mErrorView, View.GONE);
        setViewVisibility(mLoadingView, View.GONE);
        setViewVisibility(mContentView, View.VISIBLE);
    }

    private void setViewVisibility(View view, int visibility) {
        if (view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_error:
                onErrorViewClick();
                break;
        }
    }

    public void onErrorViewClick() {
    }


}
