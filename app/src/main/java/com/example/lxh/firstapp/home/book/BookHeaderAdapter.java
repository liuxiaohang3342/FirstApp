package com.example.lxh.firstapp.home.book;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.bean.BookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class BookHeaderAdapter extends BaseQuickAdapter<BookInfo, BaseViewHolder> {

    private ImageConfig mImageConfig;

    public BookHeaderAdapter(@LayoutRes int layoutResId, @Nullable List<BookInfo> data) {
        super(layoutResId, data);
        mImageConfig = new ImageConfig.Builder().setFailureDrawable(R.drawable.img_default_book).setLoadingRes(R.drawable.img_default_book).create();
    }

    @Override
    protected void convert(BaseViewHolder helper, BookInfo item) {
        ImageLoader.getInstance().load((ImageView) helper.getView(R.id.list_img_v3), item.getBook_cover(), mImageConfig);
    }
}
