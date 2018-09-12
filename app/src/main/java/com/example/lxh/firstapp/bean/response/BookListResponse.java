package com.example.lxh.firstapp.bean.response;

import com.example.lxh.firstapp.bean.ModuleInfo;

import java.util.List;

/**
 * Created by lxh on 2018/9/11.
 */

public class BookListResponse {

    private List<ModuleInfo> module;

    public List<ModuleInfo> getModule() {
        return module;
    }

    public void setModule(List<ModuleInfo> module) {
        this.module = module;
    }
}
