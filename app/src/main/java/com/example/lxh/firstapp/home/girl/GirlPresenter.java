package com.example.lxh.firstapp.home.girl;

import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.GirlInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/9.
 */

public class GirlPresenter extends BasePresenter<IGrilView> implements IListener<List<GirlInfo>> {

    private static final int PAGE_NUM = 30;

    IGirlModel mGirlModel = new GirlModel();

    private int mPage = 1;
    private boolean mHasMore = true;


    public GirlPresenter(IGrilView iGrilView) {
        super(iGrilView);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
    }


    public void requestData() {
        mPage = 1;
        mGirlModel.requestData(PAGE_NUM, mPage, this);
    }

    public void loadMore() {
        if (!mHasMore) {
            if (getView() != null) {
                getView().onNoMore();
            }
            return;
        }
        mGirlModel.requestData(PAGE_NUM, mPage, this);
    }

    @Override
    public void onSuccess(List<GirlInfo> list) {
        if (getView() == null) {
            return;
        }
        if (list != null && list.size() < PAGE_NUM) {
            mHasMore = false;
        }
        if (mPage == 1) {
            getView().setNewData(list);
        } else {
            getView().addData(list);
        }
        mPage++;
    }

    @Override
    public void onError(Throwable throwable) {
        if (getView() == null) {
            return;
        }
        getView().onError();
    }
}
