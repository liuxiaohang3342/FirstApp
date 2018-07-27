package com.example.lxh.firstapp.base.core;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxh.firstapp.R;

/**
 * Created by lxh on 2018/7/26.
 */

public class Toolbar {
    private ImageView mLeftView;
    private TextView mTitleView;
    private ImageView mRightView;

    public Toolbar(View view) {
        mLeftView = (ImageView) view.findViewById(R.id.iv_left);
        mTitleView = (TextView) view.findViewById(R.id.tv_title);
        mRightView = (ImageView) view.findViewById(R.id.iv_right);
    }

    public Toolbar setLeftIcon(int id) {
        mLeftView.setImageResource(id);
        return this;
    }

    public Toolbar setLeftClickListener(View.OnClickListener l) {
        mLeftView.setOnClickListener(l);
        return this;
    }


    public Toolbar setTitle(String title) {
        mTitleView.setText(title);
        return this;
    }

    public Toolbar setRightIcon(int id) {
        mRightView.setImageResource(id);
        return this;
    }

    public Toolbar setRightClickListener(View.OnClickListener l) {
        mRightView.setOnClickListener(l);
        return this;
    }

}
