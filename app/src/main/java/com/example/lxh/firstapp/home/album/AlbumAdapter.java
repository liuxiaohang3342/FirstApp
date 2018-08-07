package com.example.lxh.firstapp.home.album;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.bean.Album;

import java.util.List;

/**
 * Created by lxh on 2018/8/3.
 */

public class AlbumAdapter extends BaseQuickAdapter<Album, BaseViewHolder> {

    public AlbumAdapter(@LayoutRes int layoutResId, @Nullable List<Album> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Album item) {
        helper.setText(R.id.list_title_v3, item.getName());
        helper.setText(R.id.digital_desc, item.getPub());
        helper.setText(R.id.tv_album_tag, item.getArtist());
        ImageLoader.getInstance().load(mContext, (ImageView) helper.getView(R.id.list_img_v3), item.getImg());
    }
}