package com.example.lxh.firstapp.bean;

import com.example.lxh.firstapp.home.today.ItemType;

/**
 * Created by lxh on 2018/8/8.
 */

public class AppInfo extends SourceInfo {

    @Override
    public int getItemType() {
        return ItemType.TYPE_APP;
    }
}
