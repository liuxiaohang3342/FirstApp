package com.example.lxh.firstapp.banner;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lxh on 2018/8/20.
 */

public interface IndicatorAdapter {

    int getCount();

    View getView(ViewGroup viewGroup, int position);
}
