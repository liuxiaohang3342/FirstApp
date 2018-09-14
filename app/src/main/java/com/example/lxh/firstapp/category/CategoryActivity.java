package com.example.lxh.firstapp.category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.activity.BaseMVPActivity;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.category.sub.SubCategoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryActivity extends BaseMVPActivity<CategoryPresenter, ICategoryView> implements ICategoryView {

    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private CategoryAdapter mAdapter;
    private TabLayout mTabLayout;


    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_category_layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = (ViewPager) findViewById(R.id.vp_category_content);
        mTabLayout = (TabLayout) findViewById(R.id.tl_category);
        setTitle("分类");
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onDataSuccess(List<CategoryInfo> infoList) {
        showContentView();
        initFragments(infoList);
        mAdapter = new CategoryAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addTab(mTabLayout.newTab());
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
    }

    private void initFragments(List<CategoryInfo> infoList) {
        mFragmentList = new ArrayList<>();
        for (CategoryInfo info : infoList) {
            SubCategoryFragment fragment = SubCategoryFragment.newInstance(info);
            fragment.setName(info.getName());
            mFragmentList.add(fragment);
        }
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestData();
    }

}
