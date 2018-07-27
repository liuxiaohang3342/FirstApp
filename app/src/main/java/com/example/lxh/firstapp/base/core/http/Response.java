package com.example.lxh.firstapp.base.core.http;

/**
 * Created by lxh on 2018/7/26.
 */

public class Response<T> {
    private int code;
    private T data;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
