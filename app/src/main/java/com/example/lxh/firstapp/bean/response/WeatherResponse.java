package com.example.lxh.firstapp.bean.response;

import com.example.lxh.firstapp.bean.WeatherInfo;

/**
 * Created by lxh on 2018/9/13.
 */

public class WeatherResponse {
    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private WeatherInfo data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WeatherInfo getData() {
        return data;
    }

    public void setData(WeatherInfo data) {
        this.data = data;
    }
}
