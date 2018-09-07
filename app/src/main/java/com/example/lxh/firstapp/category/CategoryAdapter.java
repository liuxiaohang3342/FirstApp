package com.example.lxh.firstapp.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    public interface IGetTitle {
        String getTitle();
    }


    private List<Fragment> mFragmentList;

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    public CategoryAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        mFragmentList = fragmentList;
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
        Fragment fragment = mFragmentList.get(position);
        if (!(fragment instanceof IGetTitle)) {
            throw new RuntimeException("Fragment must implements IGetTitle");
        }
        return ((IGetTitle) fragment).getTitle();
    }
}
