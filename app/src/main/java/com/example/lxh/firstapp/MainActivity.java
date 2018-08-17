package com.example.lxh.firstapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.lxh.firstapp.base.core.fragment.BaseFragment;
import com.example.lxh.firstapp.home.album.AlbumFragment;
import com.example.lxh.firstapp.home.category.CategoryFragment;
import com.example.lxh.firstapp.home.girl.GirlFragment;
import com.example.lxh.firstapp.home.today.TodayFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_home_button);
        mRadioGroup.setOnCheckedChangeListener(this);
        mFragments.add(new TodayFragment());
        mFragments.add(new CategoryFragment());
        mFragments.add(new AlbumFragment());
        mFragments.add(new GirlFragment());
        mViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(4);
        mRadioGroup.check(R.id.tv_album);
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
