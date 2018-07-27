package com.example.lxh.firstapp.base.core.http.crypt;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lxh on 2018/7/26.
 */

public class IResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        String string = value.string();
        System.out.println("#解密前@#" + string);
//        string = AesEncryptionUtil.decrypt(string);
        System.out.println("#解密后@#" + string);
        return string;
    }
}
