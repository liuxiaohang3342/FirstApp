package com.example.lxh.firstapp.base.core.http.crypt;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lxh on 2018/7/26.
 */

public class IResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public IResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        System.out.println("#解密前@#" + string);
//        string = AesEncryptionUtil.decrypt(string);
        System.out.println("#解密后@#" + string);

        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
