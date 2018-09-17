package com.example.lxh.firstapp.home.today;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.bean.SourceInfo;
import com.example.lxh.firstapp.utils.DateUtil;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayAdapter extends BaseQuickAdapter<SourceInfo, BaseViewHolder> {


    private ImageConfig mConfig;

    public TodayAdapter(@LayoutRes int layoutResId, @Nullable List<SourceInfo> data) {
        super(layoutResId, data);
        mConfig = new ImageConfig.Builder().setFailureDrawable(R.drawable.img_one_bi_one).setLoadingRes(R.drawable.img_one_bi_one).create();

    }

    @Override
    protected void convert(BaseViewHolder helper, SourceInfo item) {
        helper.setText(R.id.tv_title, item.getDesc());
        helper.setText(R.id.tv_tag, item.getType());
        helper.setText(R.id.tv_name, item.getWho());
        helper.setText(R.id.tv_time, DateUtil.parseDate(item.getPublishedAt(), "yyyy-MM-dd"));
        String[] images = item.getImages();
        if (images != null && images.length > 0) {
            ImageLoader.getInstance().load((ImageView) helper.getView(R.id.iv_image), images[0], mConfig);
        }
    }
}
