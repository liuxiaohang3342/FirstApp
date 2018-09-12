package com.example.lxh.firstapp.bean;

import java.util.List;

/**
 * Created by lxh on 2018/9/11.
 */

public class ModuleInfo {
    private String page_id;
    private String func_id;
    private String orderid;
    private String max_ver;
    private String min_ver;
    private String id;
    private String m_s_name;
    private String m_s_class;
    private boolean _uniq;
    private List<BookInfo> content;

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public String getFunc_id() {
        return func_id;
    }

    public void setFunc_id(String func_id) {
        this.func_id = func_id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMax_ver() {
        return max_ver;
    }

    public void setMax_ver(String max_ver) {
        this.max_ver = max_ver;
    }

    public String getMin_ver() {
        return min_ver;
    }

    public void setMin_ver(String min_ver) {
        this.min_ver = min_ver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getM_s_name() {
        return m_s_name;
    }

    public void setM_s_name(String m_s_name) {
        this.m_s_name = m_s_name;
    }

    public String getM_s_class() {
        return m_s_class;
    }

    public void setM_s_class(String m_s_class) {
        this.m_s_class = m_s_class;
    }

    public boolean is_uniq() {
        return _uniq;
    }

    public void set_uniq(boolean _uniq) {
        this._uniq = _uniq;
    }

    public List<BookInfo> getContent() {
        return content;
    }

    public void setContent(List<BookInfo> content) {
        this.content = content;
    }
}
