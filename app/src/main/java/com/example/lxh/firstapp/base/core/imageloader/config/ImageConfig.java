package com.example.lxh.firstapp.base.core.imageloader.config;

/**
 * Created by lxh on 2018/8/6.
 */

public class ImageConfig {
    public int mLoadingRes;
    public int mFailureDrawable;

    public ImageConfig(Builder builder) {
        mLoadingRes = builder.mLoadingRes;
        mFailureDrawable = builder.mFailureDrawable;
    }

    public static final class Builder {
        public int mLoadingRes;
        public int mFailureDrawable;

        public Builder setLoadingRes(int loadingRes) {
            mLoadingRes = loadingRes;
            return this;
        }

        public Builder setFailureDrawable(int failureDrawable) {
            mFailureDrawable = failureDrawable;
            return this;
        }

        public ImageConfig create() {
            return new ImageConfig(this);
        }
    }

}
