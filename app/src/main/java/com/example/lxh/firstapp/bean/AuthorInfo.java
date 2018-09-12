package com.example.lxh.firstapp.bean;

import java.util.List;

/**
 * Created by lxh on 2018/9/12.
 */

public class AuthorInfo {
    private List<SimpleBookInfo> bookinfo;
    private String author_name;
    private String introduction;
    private int author_id;
    private String head_icon;
    private int count;

    public List<SimpleBookInfo> getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(List<SimpleBookInfo> bookinfo) {
        this.bookinfo = bookinfo;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
