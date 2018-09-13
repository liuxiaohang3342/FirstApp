package com.example.lxh.firstapp.bean;

import java.util.List;

/**
 * Created by lxh on 2018/9/13.
 */

public class WeatherInfo {
    private String shidu;
    private int pm25;
    private int pm10;
    private String quality;
    private String wendu;
    private String ganmao;

    private WeatherDayInfo yesterday;
    private List<WeatherDayInfo> forecast;


    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public WeatherDayInfo getYesterday() {
        return yesterday;
    }

    public void setYesterday(WeatherDayInfo yesterday) {
        this.yesterday = yesterday;
    }

    public List<WeatherDayInfo> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherDayInfo> forecast) {
        this.forecast = forecast;
    }
}
