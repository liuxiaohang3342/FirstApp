package com.example.lxh.firstapp.base.core.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by lxh on 2018/7/26.
 */

public abstract class BasePresenter<T extends IView> implements IPresenter {

    private WeakReference<IView> mReference;

    public BasePresenter(T t) {
        mReference = new WeakReference<IView>(t);
    }

    @SuppressWarnings("unchecked")
    public IView getView() {
        if (mReference == null
                || mReference.get() == null) {
            return null;
        }
        return mReference.get();
    }

}
