package com.example.lxh.firstapp.bean.response;

import com.example.lxh.firstapp.bean.Album;

import java.util.List;

/**
 * Created by lxh on 2018/8/2.
 */

public class AlbumResponse {
    private String total;
    private String pn;
    private String rn;
    private String BASEPICPATH;
    private List<Album> albumlist;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getBASEPICPATH() {
        return BASEPICPATH;
    }

    public void setBASEPICPATH(String BASEPICPATH) {
        this.BASEPICPATH = BASEPICPATH;
    }

    public List<Album> getAlbumlist() {
        return albumlist;
    }

    public void setAlbumlist(List<Album> albumlist) {
        this.albumlist = albumlist;
    }
}
