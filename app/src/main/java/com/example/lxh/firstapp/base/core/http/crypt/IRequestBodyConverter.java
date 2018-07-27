package com.example.lxh.firstapp.base.core.http.crypt;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by lxh on 2018/7/26.
 */

public class IRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType
            .parse("application/json; charset=UTF-8");
    static final Charset UTF_8 = Charset.forName("UTF-8");

    final Gson gson;
    final TypeAdapter<T> adapter;

    IRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
        System.out.println("#IRequestBodyConverter初始化#");
    }

    // 接口如果这样定义 void doPost(@Body by); \才会执行到这里,
    @Override
    public RequestBody convert(T value) throws IOException {
        String json = value.toString();
        System.out.println("#加密前#" + json);
//        json = AesEncryptionUtil.encrypt(json);
        System.out.println("#加密后#" + json);
        return RequestBody.create(MEDIA_TYPE, json);
    }
}
