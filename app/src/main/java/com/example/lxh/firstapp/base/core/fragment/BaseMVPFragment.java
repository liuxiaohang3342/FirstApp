package com.example.lxh.firstapp.base.core.fragment;

import android.os.Bundle;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.base.core.mvp.IView;

/**
 * Created by lxh on 2018/7/26.
 */

public abstract class BaseMVPFragment<T extends BasePresenter<U>, U extends IView> extends BaseFragment {

    private T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onCreate();
    }

    protected abstract T createPresenter();

    protected T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }
}
