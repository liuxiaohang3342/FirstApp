package com.example.lxh.firstapp.bean.response;

import com.example.lxh.firstapp.bean.AndroidInfo;
import com.example.lxh.firstapp.bean.AppInfo;
import com.example.lxh.firstapp.bean.GirlInfo;
import com.example.lxh.firstapp.bean.IosInfo;
import com.example.lxh.firstapp.bean.MvInfo;
import com.example.lxh.firstapp.bean.WebInfo;

import java.util.List;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayResults {

    private List<AndroidInfo> Android;

    private List<AppInfo> App;

    private List<IosInfo> iOS;

    private List<MvInfo> 休息视频;

    private List<WebInfo> 前端;

    private List<GirlInfo> 福利;


    public List<AndroidInfo> getAndroid() {
        return Android;
    }

    public void setAndroid(List<AndroidInfo> android) {
        Android = android;
    }

    public List<AppInfo> getApp() {
        return App;
    }

    public void setApp(List<AppInfo> app) {
        App = app;
    }

    public List<IosInfo> getiOS() {
        return iOS;
    }

    public void setiOS(List<IosInfo> iOS) {
        this.iOS = iOS;
    }

    public List<MvInfo> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<MvInfo> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<WebInfo> get前端() {
        return 前端;
    }

    public void set前端(List<WebInfo> 前端) {
        this.前端 = 前端;
    }

    public List<GirlInfo> get福利() {
        return 福利;
    }

    public void set福利(List<GirlInfo> 福利) {
        this.福利 = 福利;
    }
}
