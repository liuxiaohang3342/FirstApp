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

public class BookAdapter extends BaseQuickAdapter<BookInfo, BaseViewHolder> {

    private ImageConfig mImageConfig;

    public BookAdapter(@LayoutRes int layoutResId, @Nullable List<BookInfo> data) {
        super(layoutResId, data);
        mImageConfig = new ImageConfig.Builder().setFailureDrawable(R.drawable.img_default_book).setLoadingRes(R.drawable.img_default_book).create();
    }

    @Override
    protected void convert(BaseViewHolder helper, BookInfo item) {
        helper.setText(R.id.list_title_v3, item.getBookname());
        helper.setText(R.id.tv_desc, String.format(mContext.getString(R.string.book_desc), item.getAuthor(), item.getClass_name()));
        helper.setText(R.id.tv_content_info, item.getBook_info());
        ImageLoader.getInstance().load((ImageView) helper.getView(R.id.list_img_v3), item.getBook_cover(), mImageConfig);
        setTag(helper, item);
    }

    private void setTag(BaseViewHolder helper, BookInfo item) {
        String[] tags = item.getTag();
        if (tags != null) {
            StringBuilder tagText = new StringBuilder();
            for (String tag : tags) {
                tagText.append(tag).append("•");
            }
            if (tagText.length() > 1) {
                tagText.deleteCharAt(tagText.length() - 1);
                helper.setVisible(R.id.tv_tag, true);
                helper.setText(R.id.tv_tag, tagText.toString());
            } else {
                helper.setVisible(R.id.tv_tag, false);
            }
        } else {
            helper.setVisible(R.id.tv_tag, false);
        }
    }
}
