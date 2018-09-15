package com.example.lxh.firstapp.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public class CategoryAdapter extends FragmentPagerAdapter {


    private List<Fragment> mFragmentList;
    private List<String> mTitles;

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    public CategoryAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titles) {
        super(fm);
        mFragmentList = fragmentList;
        mTitles = titles;
    }

    @Override
    public int getCount() {
        if (mFragmentList == null) {
            return 0;
        }
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
