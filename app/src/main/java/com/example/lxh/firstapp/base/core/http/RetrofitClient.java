package com.example.lxh.firstapp.base.core.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxh on 2018/7/26.
 */

public class RetrofitClient {

    private static class SingletonHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static RetrofitClient getInstance(String url) {
        return new RetrofitClient(url);
    }


    private Retrofit mRetrofit;

    private RetrofitClient() {
        this(null);
    }

    private RetrofitClient(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                //添加请求头
//                .addInterceptor(new BaseInterceptor())
                //添加日志打印拦截器
//                .addInterceptor(new LoggerInterceptor("===", true))
                .build();
        Gson gson = new GsonBuilder().setLenient().create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mClient)
                //添加Gson解析
                .addConverterFactory(GsonConverterFactory.create(gson))
                //添加rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T createService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
