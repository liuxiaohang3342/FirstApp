package com.example.lxh.firstapp.base.core.imageloader.transformation;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * Created by lxh on 2018/8/7.
 */

public class ScaleTransformation extends ImageViewTarget<Bitmap> {

    private ImageView mImageView;

    public ScaleTransformation(ImageView view) {
        super(view);
        mImageView = view;
    }

    @Override
    protected void setResource(Bitmap resource) {
        ViewGroup.LayoutParams params = mImageView.getLayoutParams();
        params.height = params.width * 2;
        mImageView.setLayoutParams(params);
        mImageView.setImageBitmap(resource);
    }
}
