package com.example.lxh.firstapp.bean.response;

import java.util.List;

/**
 * Created by lxh on 2018/8/9.
 */

public class DataResponse<T> {

    private boolean error;

    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
