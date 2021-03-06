package com.example.lxh.firstapp.img;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.activity.BaseActivity;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.home.girl.Constant;
import com.example.lxh.firstapp.view.MatrixView;

import java.util.ArrayList;

/**
 * Created by lxh on 2018/8/13.
 */

public class ImageActivity extends BaseActivity implements ImageLoader.ILoadListener {

    private ViewPager mViewPager;

    private ArrayList<String> mUrls;
    private int mPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mPosition = intent.getIntExtra(Constant.KEY_POSITION, 0);
        mUrls = intent.getStringArrayListExtra(Constant.KEY_URL_LIST);
        mViewPager = (ViewPager) findViewById(R.id.vp_image);
        mViewPager.setAdapter(new ImageAdapter());
        mViewPager.setCurrentItem(mPosition);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public boolean isNeedToolBar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_layout;
    }

    private MatrixView.OnFlingListener mTouchListener = new MatrixView.OnFlingListener() {

        @Override
        public void onFling() {
            finish();
            overridePendingTransition(0, R.anim.scale_out);
        }
    };

    @Override
    public void loadSuccess() {
        showContentView();
    }

    @Override
    public void loadError() {
        showLoadingView();
    }

    class ImageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ImageActivity.this).inflate(R.layout.image_item_layout, container, false);
            MatrixView imageView = (MatrixView) view.findViewById(R.id.iv_image);
            imageView.setListener(mTouchListener);
            ImageLoader.getInstance().load(imageView, mUrls.get(position), ImageActivity.this);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
