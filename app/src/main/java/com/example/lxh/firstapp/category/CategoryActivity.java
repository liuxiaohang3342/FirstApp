package com.example.lxh.firstapp.category;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.activity.BaseMVPActivity;
import com.example.lxh.firstapp.bean.CategoryInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.category.sub.SubCategoryFragment;
import com.example.lxh.firstapp.utils.DensityUtil;
import com.example.lxh.firstapp.view.AutoLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryActivity extends BaseMVPActivity<CategoryPresenter, ICategoryView> implements ICategoryView, AppBarLayout.OnOffsetChangedListener, AutoLayout.OnClickListener, View.OnLayoutChangeListener {

    private int mTenDp;

    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private CategoryAdapter mAdapter;
    private TabLayout mTabLayout;
    private AppBarLayout mAppBarLayout;
    private AutoLayout mAutoLayout;

    private List<CategoryInfo> mInfoList;

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
        mTenDp = DensityUtil.dp2px(this, 10);
        mViewPager = (ViewPager) findViewById(R.id.vp_category_content);
        mTabLayout = (TabLayout) findViewById(R.id.tl_category);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.abl_category);
        mAutoLayout = (AutoLayout) findViewById(R.id.al_category);
        mAppBarLayout.addOnOffsetChangedListener(this);
        mAutoLayout.setListener(this);
        mAutoLayout.addOnLayoutChangeListener(this);
        setTitle("分类");
        showLoadingView();
        mFragmentList = new ArrayList<>();
        getPresenter().requestData();
    }

    @Override
    public void onDataSuccess(List<CategoryInfo> infoList) {
        showContentView();
        mInfoList = infoList;
        mAutoLayout.setAdapter(new TagAdapter(this, mInfoList));
        mAppBarLayout.getLayoutParams().height = mAutoLayout.getLayoutParams().height;
        getPresenter().requestSubCategory(infoList.get(0).getEn_name());
    }

    @Override
    public void onSubCategoryFail() {

    }

    @Override
    public void onSubCategorySuccess(List<SubCategoryInfo> infoList) {
        initFragments(infoList);
        if (mAdapter == null) {
            mAdapter = new CategoryAdapter(getSupportFragmentManager(), mFragmentList, getTitles(infoList));
            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private List<String> getTitles(List<SubCategoryInfo> infoList) {
        List<String> titles = new ArrayList<>();
        for (SubCategoryInfo info : infoList) {
            titles.add(info.getTitle());
        }
        return titles;
    }


    private void initFragments(List<SubCategoryInfo> infoList) {
        mFragmentList.clear();
        for (SubCategoryInfo info : infoList) {
            SubCategoryFragment fragment = SubCategoryFragment.newInstance(info);
            mFragmentList.add(fragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (mAppBarLayout.getTop() + mAppBarLayout.getTotalScrollRange() <= 0) {
            mAppBarLayout.setExpanded(true, true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onErrorViewClick() {
        super.onErrorViewClick();
        showLoadingView();
        getPresenter().requestData();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float percent = -verticalOffset * 1f / appBarLayout.getTotalScrollRange();
        mTabLayout.setTranslationY(-mTabLayout.getHeight() * percent);
    }

    @Override
    public void onClick(View v, int position) {
        getPresenter().requestSubCategory(mInfoList.get(position).getEn_name());
    }

    @Override
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        ViewGroup.LayoutParams params = mAppBarLayout.getLayoutParams();
        params.height = mAutoLayout.getHeight() + mTenDp * 2;
    }
}
