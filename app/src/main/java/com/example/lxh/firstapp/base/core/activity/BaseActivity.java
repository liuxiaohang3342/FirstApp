package com.example.lxh.firstapp.base.core.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.lxh.firstapp.R;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lxh on 2018/7/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private View mErrorView;
    private View mEmptyView;
    private View mLoadingView;
    private View mContentView;
    private AnimationDrawable mLoadingDrawable;
    private Toolbar mToolbar;

    private CompositeSubscription mSubscription;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_base);
        initView();
        setToolBar();
        FrameLayout mContainer = getView(R.id.fl_container);
        mContentView = getLayoutInflater().inflate(getLayoutId(), mContainer, false);
        mContainer.addView(mContentView, 0);
    }

    private void initView() {
        mErrorView = getView(R.id.ll_error);
        mEmptyView = getView(R.id.ll_empty);
        mLoadingView = getView(R.id.ll_loading);
        mContentView = getView(R.id.fl_container);
        ImageView img = getView(R.id.img_loading);
        mLoadingDrawable = (AnimationDrawable) img.getDrawable();
        if (!mLoadingDrawable.isRunning()) {//进入页面默认开启
            mLoadingDrawable.start();
        }
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
    }

    protected abstract int getLayoutId();

    protected void setToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }


    private <T extends View> T getView(int id) {
        return (T) findViewById(id);
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

    /**
     * 所有rx订阅后，需要调用此方法，用于在detachView时取消订阅
     */
    protected void addSubscription(Subscription subscribe) {
        if (mSubscription == null)
            mSubscription = new CompositeSubscription();
        mSubscription.add(subscribe);
    }

    /**
     * 取消本页面所有订阅
     */
    protected void onUnsubscribe() {
        if (mSubscription != null && mSubscription.hasSubscriptions()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }
}
