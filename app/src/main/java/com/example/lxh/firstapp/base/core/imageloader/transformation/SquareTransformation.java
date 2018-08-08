package com.example.lxh.firstapp.base.core.imageloader.transformation;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * Created by lxh on 2018/8/7.
 */

public class SquareTransformation extends ImageViewTarget<Bitmap> {


    private ImageView mImageView;

    public SquareTransformation(ImageView view) {
        super(view);
        mImageView = view;
    }

    @Override
    protected void setResource(Bitmap resource) {
        ViewGroup.LayoutParams params = mImageView.getLayoutParams();
        int lenght = params.height > params.width ? params.height : params.width;
        params.height = lenght;
        params.width = lenght;
        mImageView.setLayoutParams(params);
    }
}
