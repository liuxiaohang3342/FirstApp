package com.example.lxh.firstapp.home.today;

import com.example.lxh.firstapp.api.IListener;
import com.example.lxh.firstapp.base.core.mvp.BasePresenter;
import com.example.lxh.firstapp.bean.SourceInfo;
import com.example.lxh.firstapp.utils.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayPresenter extends BasePresenter<ITodayView> implements IListener<List<SourceInfo>>, IHistoryListener {

    ITodayModel<List<SourceInfo>> mModel = new TodayModel();

    private List<String> mHistory;
    private int mNextPagePosition = -1;

    public TodayPresenter(ITodayView iTodayView) {
        super(iTodayView);
    }

    @Override
    public void onCreate() {
        dayHistory();
    }

    @Override
    public void onDestory() {

    }

    public void requestToday() {
        mNextPagePosition = -1;
        mModel.request(this);
    }

    public void loadMore() {
        if (getView() == null) {
            return;
        }
        if (mHistory == null || mHistory.size() <= mNextPagePosition || mNextPagePosition < 0) {
            getView().onNoMore();
            return;
        }
        Date date = DateUtil.fromDate(mHistory.get(mNextPagePosition));
        if (date == null) {
            getView().onError();
            return;
        }
        mModel.request(date, this);
    }

    public void dayHistory() {
        mModel.dayHistory(this);
    }


    @Override
    public void onSuccess(List<SourceInfo> list) {
        if (getView() == null) {
            return;
        }
        if (mNextPagePosition == -1) {
            getView().onRequestSuccess(list);
        } else {
            getView().onLoadMoreSuccess(list);
        }
        mNextPagePosition++;

    }

    @Override
    public void onError(Throwable throwable) {
        if (getView() == null) {
            return;
        }
        getView().onError();
    }

    @Override
    public void onHistoryBack(List<String> historys) {
        mHistory = historys;
    }

    @Override
    public void onHistoryError() {

    }
}
