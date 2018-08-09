package com.example.lxh.firstapp.base.core.imageloader.transformation;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * Created by lxh on 2018/8/9.
 */

public class TransformationFactory {
    public static final int SQUARE = 0, WH2 = 1;

    public static ImageViewTarget<Bitmap> createTransformation(int type, ImageView view) {
        switch (type) {
            case SQUARE:
                return new SquareTransformation(view);
            case WH2:
                return new ScaleTransformation(view);
        }
        return null;
    }

}
