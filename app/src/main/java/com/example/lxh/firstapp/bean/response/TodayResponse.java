package com.example.lxh.firstapp.bean.response;

import com.example.lxh.firstapp.bean.TodayResults;

/**
 * Created by lxh on 2018/8/8.
 */

public class TodayResponse {
    private String[] category;
    private boolean error;
    private TodayResults results;


    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public TodayResults getResults() {
        return results;
    }

    public void setResults(TodayResults results) {
        this.results = results;
    }
}
