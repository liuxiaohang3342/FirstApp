package com.example.lxh.firstapp.base.core.activity;

import android.os.Bundle;

import com.example.lxh.firstapp.base.core.fragment.BaseFragment;

/**
 * Created by lxh on 2018/8/3.
 */

public class FragmentParam {

    public enum TYPE {
        ADD, REPLACE
    }

    public BaseFragment mFrom;

    public Bundle mBundle;

    public Class<?> mClass;

    public boolean addToBackStack = true;

    public TYPE type = TYPE.ADD;

    public int id;

}
