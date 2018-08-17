package com.example.lxh.firstapp.category.sub;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.bean.SubCategoryInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/13.
 */

public class SubCategoryAdapter extends BaseQuickAdapter<SubCategoryInfo, BaseViewHolder> {

    public SubCategoryAdapter(@LayoutRes int layoutResId, @Nullable List<SubCategoryInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoryInfo item) {
        helper.setText(R.id.list_title_v3, item.getTitle());
        helper.setText(R.id.digital_desc, item.getCreated_at());
        helper.setText(R.id.tv_album_tag, item.getId());
        ImageLoader.getInstance().load(mContext, (ImageView) helper.getView(R.id.list_img_v3), item.getIcon());
    }
}
