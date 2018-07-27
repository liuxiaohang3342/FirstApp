package com.example.lxh.firstapp.base.core.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxh on 2018/7/26.
 */

public class HttpMethod {


    //接口根地址
    public static final String BASE_URL = "http://www.baidu.com";

    private static class SingletonHolder {
        private static final HttpMethod INSTANCE = new HttpMethod();
    }

    public static HttpMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private Retrofit mRetrofit;
    private OkHttpClient mClient;

    private HttpMethod() {
        mClient = new OkHttpClient.Builder()
                .connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                //添加请求头
                //.addInterceptor(new HeaderInterceptor())
                //添加日志打印拦截器
//                .addInterceptor(new LoggerInterceptor("===", true))
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mClient)
                //添加Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                //添加rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T createService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
