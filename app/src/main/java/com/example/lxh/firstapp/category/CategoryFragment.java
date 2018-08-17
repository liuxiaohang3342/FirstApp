package com.example.lxh.firstapp.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.fragment.BaseMVPFragment;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.category.sub.SubCategoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryFragment extends BaseMVPFragment<CategoryPresenter, ICategoryView> implements ICategoryView {

    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private CategoryAdapter mAdapter;


    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_category_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_category_content);
    }

    @Override
    public void onDataSuccess(List<CategoryInfo> infoList) {
        showContentView();
        initFragments(infoList);
        mAdapter = new CategoryAdapter(getFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
    }

    private void initFragments(List<CategoryInfo> infoList) {
        mFragmentList = new ArrayList<>();
        for (CategoryInfo info : infoList) {
            mFragmentList.add(SubCategoryFragment.newInstance(info));
        }
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestData();
    }
}
