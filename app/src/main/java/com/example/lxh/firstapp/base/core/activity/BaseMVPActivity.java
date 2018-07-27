package com.example.lxh.firstapp.base.core.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.base.core.mvp.IView;

/**
 * Created by lxh on 2018/7/26.
 */

public abstract class BaseMVPActivity<T extends BasePresenter<U>, U extends IView> extends BaseActivity {

    private T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mPresenter = createPresenter();
        mPresenter.onCreate();
    }

    protected abstract T createPresenter();

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onCreate();
    }
}
