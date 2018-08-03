package com.example.lxh.firstapp.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lxh on 2018/8/2.
 */

public class Album implements Serializable {
    private String albumid;
    private String artistid;
    private String id;
    private String name;
    private String falbum;
    private String artist;

    private String fartist;
    private String aartist;
    private String lang;
    private String company;
    private String pub;
    private String pic;


    private String score;
    private String info;
    private String PAY;
    private String musiccnt;
    private String imgscript;

    private String flag;
    private String desc_url;
    private String isstar;
    private String showtime;
    private String img;

    @SerializedName("new")
    private String isNew;

    public String getAlbumid() {
        return albumid;
    }

    public void setAlbumid(String albumid) {
        this.albumid = albumid;
    }

    public String getArtistid() {
        return artistid;
    }

    public void setArtistid(String artistid) {
        this.artistid = artistid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFalbum() {
        return falbum;
    }

    public void setFalbum(String falbum) {
        this.falbum = falbum;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFartist() {
        return fartist;
    }

    public void setFartist(String fartist) {
        this.fartist = fartist;
    }

    public String getAartist() {
        return aartist;
    }

    public void setAartist(String aartist) {
        this.aartist = aartist;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPAY() {
        return PAY;
    }

    public void setPAY(String PAY) {
        this.PAY = PAY;
    }

    public String getMusiccnt() {
        return musiccnt;
    }

    public void setMusiccnt(String musiccnt) {
        this.musiccnt = musiccnt;
    }

    public String getImgscript() {
        return imgscript;
    }

    public void setImgscript(String imgscript) {
        this.imgscript = imgscript;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDesc_url() {
        return desc_url;
    }

    public void setDesc_url(String desc_url) {
        this.desc_url = desc_url;
    }

    public String getIsstar() {
        return isstar;
    }

    public void setIsstar(String isstar) {
        this.isstar = isstar;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }
}
