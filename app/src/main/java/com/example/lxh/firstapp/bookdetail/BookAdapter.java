package com.example.lxh.firstapp.bookdetail;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.bean.BookInfo;
import com.example.lxh.firstapp.bean.SimpleBookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class BookAdapter extends BaseQuickAdapter<SimpleBookInfo, BaseViewHolder> {

    private ImageConfig mImageConfig;

    public BookAdapter(@LayoutRes int layoutResId, @Nullable List<SimpleBookInfo> data) {
        super(layoutResId, data);
        mImageConfig = new ImageConfig.Builder().setFailureDrawable(R.drawable.img_default_book).setLoadingRes(R.drawable.img_default_book).create();
    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleBookInfo item) {
        ImageLoader.getInstance().load((ImageView) helper.getView(R.id.list_img_v3), item.getCover(), mImageConfig);
    }
}
