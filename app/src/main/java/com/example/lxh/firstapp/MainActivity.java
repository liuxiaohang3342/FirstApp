package com.example.lxh.firstapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.lxh.firstapp.base.core.activity.BaseActivity;
import com.example.lxh.firstapp.base.core.fragment.BaseFragment;
import com.example.lxh.firstapp.home.girl.GirlFragment;
import com.example.lxh.firstapp.home.today.TodayFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager mViewPager;

    private ArrayList<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        findViewById(R.id.tv_album).setOnClickListener(this);
        findViewById(R.id.tv_songlist).setOnClickListener(this);
        findViewById(R.id.tv_mv).setOnClickListener(this);
        findViewById(R.id.tv_mine).setOnClickListener(this);
        mFragments.add(new TodayFragment());
        mFragments.add(new GirlFragment());
        mViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(4);
        setTitle("干货");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                break;

        }
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
