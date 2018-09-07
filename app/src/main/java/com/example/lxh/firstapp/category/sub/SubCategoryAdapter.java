package com.example.lxh.firstapp.category.sub;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.bean.ContentInfo;
import com.example.lxh.firstapp.bean.SiteInfo;
import com.example.lxh.firstapp.bean.SubCategoryInfo;
import com.example.lxh.firstapp.utils.DateUtil;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public class SubCategoryAdapter extends BaseQuickAdapter<ContentInfo, BaseViewHolder> {

    public SubCategoryAdapter(@LayoutRes int layoutResId, @Nullable List<ContentInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContentInfo item) {
        SiteInfo siteInfo = item.getSite();
        if (siteInfo != null) {
            ImageLoader.getInstance().load(mContext, (ImageView) helper.getView(R.id.iv_icon), siteInfo.getIcon());
            helper.setText(R.id.tv_name, siteInfo.getName());
        }

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_desc, item.getContent());

        helper.setText(R.id.tv_create_time, "创建:" + DateUtil.parseDate(item.getCreated_at(), "yyyy-MM-dd"));
        helper.setText(R.id.tv_publish_time, "发布:" + DateUtil.parseDate(item.getPublished_at(), "yyyy-MM-dd"));
        helper.setText(R.id.tv_update_time, "更新:" + DateUtil.parseDate(item.getUpdated_at(), "yyyy-MM-dd"));


    }
}
