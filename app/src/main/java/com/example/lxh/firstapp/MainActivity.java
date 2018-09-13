package com.example.lxh.firstapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.example.lxh.firstapp.base.core.activity.BaseActivity;
import com.example.lxh.firstapp.base.core.fragment.BaseFragment;
import com.example.lxh.firstapp.home.weather.WeatherFragment;
import com.example.lxh.firstapp.home.book.BookFragment;
import com.example.lxh.firstapp.home.girl.GirlFragment;
import com.example.lxh.firstapp.home.today.TodayFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_home_button);
        mRadioGroup.setOnCheckedChangeListener(this);
        mFragments.add(new TodayFragment());
        mFragments.add(new BookFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new GirlFragment());
        mViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(this);
        mRadioGroup.check(R.id.tv_album);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isNeedToolBar() {
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tv_album:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_songlist:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tv_mv:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.tv_mine:
                mViewPager.setCurrentItem(3);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        int id = mRadioGroup.getChildAt(position).getId();
        mRadioGroup.check(id);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
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
