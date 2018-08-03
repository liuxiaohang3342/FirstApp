package com.example.lxh.firstapp.base.core.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.lxh.firstapp.base.core.fragment.BaseFragment;

import java.lang.reflect.Constructor;

/**
 * Created by lxh on 2018/8/3.
 */

public abstract class BaseFragmentActivity extends BaseActivity {

    protected BaseFragment mCurrentFragment;

    public void addFragment(int id, Class<?> clazz, Bundle bundle) {
        FragmentParam param = new FragmentParam();
        param.mClass = clazz;
        param.mBundle = bundle;
        param.type = FragmentParam.TYPE.ADD;
        param.addToBackStack = true;
        param.id = id;
        processFragment(param);
    }

    public void replaceFragment(int id, Class<?> clazz, Bundle bundle) {
        FragmentParam param = new FragmentParam();
        param.mClass = clazz;
        param.mBundle = bundle;
        param.type = FragmentParam.TYPE.REPLACE;
        param.addToBackStack = false;
        param.id = id;
        processFragment(param);
    }


    private void processFragment(FragmentParam param) {
        if (param.id <= 0) {
            return;
        }
        Class<?> clazz = param.mClass;
        if (clazz == null) {
            return;
        }
        try {
            String fragmentTag = clazz.getSimpleName();
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag);
            if (fragment == null) {
                if (param.mBundle == null) {
                    fragment = (BaseFragment) clazz.newInstance();
                } else {
                    Constructor constructor = clazz.getConstructor(Bundle.class);
                    fragment = (BaseFragment) constructor.newInstance(param.mBundle);
                }
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (param.type == FragmentParam.TYPE.ADD) {
                if (fragment.isAdded()) {
                    transaction.show(fragment);
                } else {
                    transaction.add(param.id, fragment, fragmentTag);
                }
            } else {
                transaction.replace(param.id, fragment, fragmentTag);
            }
            mCurrentFragment = fragment;
            if (param.addToBackStack) {
                transaction.addToBackStack(fragmentTag);
            }
            // 允许可能丢失状态的情况下提交事务
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
        }
    }

    public void goToFragment(Class<?> cls, Bundle data) {
        if (cls == null) {
            return;
        }
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(cls.toString());
        if (fragment != null) {
            mCurrentFragment = fragment;
        }
        getSupportFragmentManager().popBackStackImmediate(cls.toString(), 0);
    }

    public void popTopFragment(Bundle data) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        int cnt = fm.getBackStackEntryCount();
        String name = fm.getBackStackEntryAt(cnt - 1).getName();
        mCurrentFragment = (BaseFragment) fm.findFragmentByTag(name);
    }

    /**
     * popToRoot:回退到栈低Fragment. <br/>
     *
     * @param data 需要传递给栈低Fragment的数据
     * @author adison
     */
    public void popToRoot(Bundle data) {
        FragmentManager fm = getSupportFragmentManager();
        while (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
        }
        popTopFragment(data);
    }

    @Override
    public void onBackPressed() {
        int cnt = getSupportFragmentManager().getBackStackEntryCount();
        if (cnt <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

}
