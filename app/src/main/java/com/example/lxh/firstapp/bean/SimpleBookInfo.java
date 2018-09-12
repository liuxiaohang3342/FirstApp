package com.example.lxh.firstapp.bean;

import java.util.List;

/**
 * Created by lxh on 2018/9/12.
 */

public class SimpleBookInfo {

    private String id;
    private String bookname;
    private String introduction;
    private String book_info;
    private String author_name;
    private String state;
    private String class_name;
    private String cover;
    private List<TagInfo> tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBook_info() {
        return book_info;
    }

    public void setBook_info(String book_info) {
        this.book_info = book_info;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<TagInfo> getTag() {
        return tag;
    }

    public void setTag(List<TagInfo> tag) {
        this.tag = tag;
    }
}
