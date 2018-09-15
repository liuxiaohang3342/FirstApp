package com.example.lxh.firstapp.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.category.sub.SubCategoryFragment;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 * viewPager + fragment notify无效，只覆写getItemPosition()并不能更新fragment,instantiateItem里拿到的fragment还是FragmentManager里的
 *
 * 两种方式更新：
 * 1.如果Fragment是同一个类型，提供更新接口。
 *   优点：Fragment复用   缺点：Fragment类型不同，不能用此种方式
 * 2.在destroyItem的时候把该Fragment从FragmentManager移除
 */

public class CategoryAdapter extends FragmentPagerAdapter {


    private List<SubCategoryInfo> mInfoList;

    public CategoryAdapter(FragmentManager fm, List<SubCategoryInfo> infos) {
        super(fm);
        mInfoList = infos;
    }

    public void setmInfoList(List<SubCategoryInfo> mInfoList) {
        this.mInfoList = mInfoList;
    }

    @Override
    public int getCount() {
        if (mInfoList == null) {
            return 0;
        }
        return mInfoList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return SubCategoryFragment.newInstance(mInfoList.get(position));
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        FragmentTransaction transaction = (FragmentTransaction) getSuperField(this, "mCurTransaction");
        transaction.remove((Fragment) object);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mInfoList.get(position).getTitle();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    public static Object getSuperField(Object paramClass, String paramString) {
        Field field = null;
        Object object = null;
        try {
            field = paramClass.getClass().getSuperclass().getDeclaredField(paramString);
            field.setAccessible(true);
            object = field.get(paramClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
