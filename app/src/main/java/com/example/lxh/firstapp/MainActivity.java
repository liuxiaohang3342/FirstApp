package com.example.lxh.firstapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.lxh.firstapp.base.core.activity.BaseFragmentActivity;
import com.example.lxh.firstapp.home.AlbumFragment;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.tv_album).setOnClickListener(this);
        findViewById(R.id.tv_songlist).setOnClickListener(this);
        findViewById(R.id.tv_mv).setOnClickListener(this);
        findViewById(R.id.tv_mine).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_album:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl_content, new AlbumFragment());
                ft.commitAllowingStateLoss();
                replaceFragment(R.id.fl_content, AlbumFragment.class, null);
                break;
            case R.id.tv_songlist:
                break;
            case R.id.tv_mv:
                break;
            case R.id.tv_mine:
                break;

        }
    }
}
