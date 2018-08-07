package com.example.lxh.firstapp.home.songlist;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.bean.SongList;
import com.example.lxh.firstapp.utils.NumberUtil;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class SongListAdapter extends BaseQuickAdapter<SongList, BaseViewHolder> {

    public SongListAdapter(@LayoutRes int layoutResId, @Nullable List<SongList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SongList item) {
        helper.setText(R.id.list_title_v3, item.getName());
        try {
            helper.setText(R.id.tv_desc, NumberUtil.formatCount(Long.valueOf(item.getPlaycnt())));
        } catch (Exception e) {
            helper.setText(R.id.tv_desc, item.getPlaycnt());
        }
        ImageLoader.getInstance().load(mContext, (ImageView) helper.getView(R.id.list_img_v3), item.getPic());
    }
}
