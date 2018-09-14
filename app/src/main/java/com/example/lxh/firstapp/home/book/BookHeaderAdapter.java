package com.example.lxh.firstapp.home.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.banner.LoopBannerAdapter;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;
import com.example.lxh.firstapp.bean.BookInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/7.
 */

public class BookHeaderAdapter extends LoopBannerAdapter {

    private Context mContext;
    private ImageConfig mImageConfig;
    private List<BookInfo> mInfoList;

    public BookHeaderAdapter(Context context, List<BookInfo> infoList) {
        mContext = context;
        mInfoList = infoList;
        mImageConfig = new ImageConfig.Builder().setLoadingRes(R.drawable.img_four_bi_three).setFailureDrawable(R.drawable.img_four_bi_three).create();
    }

    @Override
    public int getDataSize() {
        return mInfoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiate(ViewGroup container, int position) {
        BookInfo info = mInfoList.get(position);
        View view = LayoutInflater.from(mContext).inflate(R.layout.book_header_viewpager_item_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.list_img_v3);
        ImageLoader.getInstance().load(imageView, info.getBook_cover(), mImageConfig);
        container.addView(view);
        return view;
    }

    @Override
    public void destroy(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
