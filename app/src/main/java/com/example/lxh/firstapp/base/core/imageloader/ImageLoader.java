package com.example.lxh.firstapp.base.core.imageloader;

import android.app.Fragment;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lxh.firstapp.base.core.imageloader.config.ImageConfig;

/**
 * Created by lxh on 2018/8/6.
 * <p>
 * 避免以后换框架的时候需要改的地方太多。如果封装了只需要改封装的方法而不会影响到所有的代码。
 * 入口统一，所有图片加载都在这一个地方管理，一目了然，即使有什么改动我也只需要改这一个类就可以了。
 * 虽然现在的第三方库已经非常好用，但是如果我们看到第三方库就拿来用的话，很可能在第三方库无法满足业务需求或者停止维护的时候，发现替换库，工作量可见一斑。这就是不封装在切库时面临的窘境！
 * 外部表现一致，内部灵活处理原则
 */

public class ImageLoader {

    private static class SingletonHolder {
        private static ImageLoader sImageLoader = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return SingletonHolder.sImageLoader;
    }


    public void load(Context context, ImageView view, String url) {
        Glide.with(context).load(url).into(view);
    }

    public void load(Fragment fragment, ImageView view, String url) {
        Glide.with(fragment).load(url).into(view);
    }


    public void load(Context context, ImageView view, String url, ImageConfig config) {
        Glide.with(context)
                .load(url)
                .placeholder(config.mLoadingRes)
                .error(config.mFailureDrawable)
                .into(view);
    }

    public void load(Fragment fragment, ImageView view, String url, ImageConfig config) {
        Glide.with(fragment)
                .load(url)
                .placeholder(config.mLoadingRes)
                .error(config.mFailureDrawable)
                .into(view);
    }




}
