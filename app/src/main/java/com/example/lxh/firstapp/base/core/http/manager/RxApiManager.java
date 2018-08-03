package com.example.lxh.firstapp.base.core.http.manager;

import android.util.ArrayMap;

import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxh on 2018/8/3.
 */

public class RxApiManager implements IActionManager<Object> {

    private static RxApiManager mInstance;

    private ArrayMap<Object, Disposable> mSubscriptionArrayMap;

    public static RxApiManager getInstance() {
        if (mInstance == null) {
            synchronized (RxApiManager.class) {
                if (mInstance == null) {
                    mInstance = new RxApiManager();
                }
            }
        }
        return mInstance;
    }

    private RxApiManager() {
        mSubscriptionArrayMap = new ArrayMap<>();
    }


    @Override
    public void add(Object key, Disposable disposable) {
        mSubscriptionArrayMap.put(key, disposable);
    }

    @Override
    public void remove(Object key) {
        if (!mSubscriptionArrayMap.isEmpty()) {
            mSubscriptionArrayMap.remove(key);
        }
    }

    public void removeAll() {
        if (!mSubscriptionArrayMap.isEmpty()) {
            mSubscriptionArrayMap.clear();
        }
    }

    @Override
    public void cancel(Object key) {
        if (mSubscriptionArrayMap.isEmpty()) {
            return;
        }
        Disposable disposable = mSubscriptionArrayMap.get(key);
        if (disposable == null) {
            return;
        }
        if (!disposable.isDisposed()) {
            disposable.dispose();
            mSubscriptionArrayMap.remove(key);
        }
    }

    @Override
    public void cancelAll() {
        if (mSubscriptionArrayMap.isEmpty()) {
            return;
        }
        Set<Object> keys = mSubscriptionArrayMap.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }
}
