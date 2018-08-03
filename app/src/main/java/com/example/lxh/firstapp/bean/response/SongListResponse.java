package com.example.lxh.firstapp.bean.response;

import com.example.lxh.firstapp.bean.SongList;

import java.util.List;

/**
 * Created by lxh on 2018/8/2.
 */

public class SongListResponse {

    private String RN;
    private String PN;
    private String HIT;
    private String TOTAL;

    private String SHOW;
    private String NEW;
    private String MSHOW;
    private String HITMODE;

    private String ARTISTPIC;
    private String HIT_BUT_OFFLINE;

    private List<SongList> abslist;


    public String getRN() {
        return RN;
    }

    public void setRN(String RN) {
        this.RN = RN;
    }

    public String getPN() {
        return PN;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    public String getHIT() {
        return HIT;
    }

    public void setHIT(String HIT) {
        this.HIT = HIT;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getSHOW() {
        return SHOW;
    }

    public void setSHOW(String SHOW) {
        this.SHOW = SHOW;
    }

    public String getNEW() {
        return NEW;
    }

    public void setNEW(String NEW) {
        this.NEW = NEW;
    }

    public String getMSHOW() {
        return MSHOW;
    }

    public void setMSHOW(String MSHOW) {
        this.MSHOW = MSHOW;
    }

    public String getHITMODE() {
        return HITMODE;
    }

    public void setHITMODE(String HITMODE) {
        this.HITMODE = HITMODE;
    }

    public String getARTISTPIC() {
        return ARTISTPIC;
    }

    public void setARTISTPIC(String ARTISTPIC) {
        this.ARTISTPIC = ARTISTPIC;
    }

    public String getHIT_BUT_OFFLINE() {
        return HIT_BUT_OFFLINE;
    }

    public void setHIT_BUT_OFFLINE(String HIT_BUT_OFFLINE) {
        this.HIT_BUT_OFFLINE = HIT_BUT_OFFLINE;
    }

    public List<SongList> getAbslist() {
        return abslist;
    }

    public void setAbslist(List<SongList> abslist) {
        this.abslist = abslist;
    }
}
