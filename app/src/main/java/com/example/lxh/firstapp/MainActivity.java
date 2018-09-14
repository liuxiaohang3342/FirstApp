package com.example.lxh.firstapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.lxh.firstapp.base.core.activity.BaseActivity;
import com.example.lxh.firstapp.base.core.fragment.BaseFragment;
import com.example.lxh.firstapp.home.book.BookFragment;
import com.example.lxh.firstapp.home.girl.GirlFragment;
import com.example.lxh.firstapp.home.today.TodayFragment;
import com.example.lxh.firstapp.home.weather.WeatherFragment;
import com.example.lxh.firstapp.view.BottomTabLayout;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private BottomTabLayout mTabLayout;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mTabLayout = (BottomTabLayout) findViewById(R.id.tl_home_bottom);
        mFragments.add(new TodayFragment());
        mFragments.add(new BookFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new GirlFragment());
        mViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(4);
        initTab();
    }

    private void initTab() {
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getDrawable(R.drawable.nav_home)).setText("干货"));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getDrawable(R.drawable.nav_classify)).setText("书籍"));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getDrawable(R.drawable.nav_live)).setText("天气"));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getDrawable(R.drawable.nav_mine)).setText("福利"));
        mTabLayout.setSelected(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isNeedToolBar() {
        return false;
    }

    private class HomeAdapter extends FragmentPagerAdapter {

        public HomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
