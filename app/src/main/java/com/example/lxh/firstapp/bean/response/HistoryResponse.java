package com.example.lxh.firstapp.bean.response;

/**
 * Created by lxh on 2018/8/8.
 */

public class HistoryResponse {

    private boolean error;
    private String[] results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String[] getResults() {
        return results;
    }

    public void setResults(String[] results) {
        this.results = results;
    }
}
