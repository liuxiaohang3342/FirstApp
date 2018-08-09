package com.example.lxh.firstapp.home.girl;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.bean.GirlInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/9.
 */

public class GirlAdapter extends BaseQuickAdapter<GirlInfo, BaseViewHolder> {

    private ImageConfig mImageConfig;

    public GirlAdapter(@LayoutRes int layoutResId, @Nullable List<GirlInfo> data) {
        super(layoutResId, data);
        mImageConfig = new ImageConfig.Builder().setLoadingRes(R.drawable.img_default_meizi).setFailureDrawable(R.drawable.img_default_meizi).create();
    }

    @Override
    protected void convert(BaseViewHolder helper, GirlInfo item) {
        ImageLoader.getInstance().load(mContext, (ImageView) helper.getView(R.id.iv_girl), item.getUrl(), mImageConfig);
    }
}
