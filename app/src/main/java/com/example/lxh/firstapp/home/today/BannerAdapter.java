package com.example.lxh.firstapp.home.today;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lxh.firstapp.R;
import com.example.lxh.firstapp.base.core.imageloader.ImageLoader;

/**
 * Created by lxh on 2018/8/17.
 */

public class BannerAdapter extends PagerAdapter {

    private String[] mUrls = {
            "http://pic.soutu123.com/back_pic/00/00/40/82/7133a08eb7e18053c1d218bc799fd7ea.jpg!/fw/700/quality/100/unsharp/true/compress/true",
            "http://pic.soutu123.com/back_pic/18/07/19/fa9d07804f2cb0f7e35b6745a06c3b60.jpg!/fw/700/quality/90/unsharp/true/compress/true",
            "http://pic.soutu123.com/back_pic/18/08/07/462b32c156775461df87f112717438ce.jpg!/fw/700/quality/90/unsharp/true/compress/true",
            "http://pic.soutu123.com/back_pic/00/15/66/75573ad8a85497e.jpg!/fw/700/quality/90/unsharp/true/compress/true",
            "http://pic.soutu123.com/back_pic/00/03/74/53561f93062f858.jpg!/fw/700/quality/90/unsharp/true/compress/true",
            "http://pic.soutu123.com/back_pic/18/07/19/ab299ec197f0826d7af49217322ed70a.jpg!/fw/700/quality/90/unsharp/true/compress/true"
    };

    private Context mContext;

    public BannerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mUrls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_item_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        ImageLoader.getInstance().load(mContext, imageView, mUrls[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
