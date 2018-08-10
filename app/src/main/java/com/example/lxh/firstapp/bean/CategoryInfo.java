package com.example.lxh.firstapp.bean;

/**
 * Created by lxh on 2018/8/10.
 */

public class CategoryInfo {

    private String _id;
    private String en_name;
    private String name;
    private String rank;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
