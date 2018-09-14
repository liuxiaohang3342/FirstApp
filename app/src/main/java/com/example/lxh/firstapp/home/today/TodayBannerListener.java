package com.example.lxh.firstapp.home.today;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.common.DefaultBannerListener;

public class TodayBannerListener extends DefaultBannerListener<String> {

    private Context mContext;
    private ImageConfig mImageConfig;

    public TodayBannerListener(Context mContext) {
        this.mContext = mContext;
        mImageConfig = new ImageConfig.Builder().setLoadingRes(R.drawable.img_four_bi_three).setFailureDrawable(R.drawable.img_four_bi_three).create();
    }

    @Override
    public Object instantiate(ViewGroup container, int position, String url) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_item_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        ImageLoader.getInstance().load(imageView, url, mImageConfig);
        container.addView(view);
        return view;
    }
}
