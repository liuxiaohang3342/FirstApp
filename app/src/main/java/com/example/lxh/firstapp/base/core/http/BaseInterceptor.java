package com.example.lxh.firstapp.base.core.http;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.*;
import okhttp3.Response;

/**
 * Created by lxh on 2018/8/2.
 */

public class BaseInterceptor implements Interceptor {
    private Map<String, String> headers;

    public BaseInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());
    }
}
