package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class Pgmisbrstranstbl extends SugarRecord {
    private String uuid;
    private String date;
    private String balcashbook;
    private String balpassbook;
    private String passbooklastpageimg;
    private String cashbooklastpageimg;
    private String isexported;
    private String entrydate;
    private String pgcode;

    public Pgmisbrstranstbl() {
    }

    public Pgmisbrstranstbl(String uuid, String date, String balcashbook, String balpassbook, String passbooklastpageimg, String cashbooklastpageimg, String isexported, String entrydate,String pgcode) {
        this.uuid = uuid;
        this.date = date;
        this.balcashbook = balcashbook;
        this.balpassbook = balpassbook;
        this.passbooklastpageimg = passbooklastpageimg;
        this.cashbooklastpageimg = cashbooklastpageimg;
        this.isexported = isexported;
        this.entrydate = entrydate;
        this.pgcode = pgcode;
    }

    public String getPgcode() {
        return pgcode;
    }

    public void setPgcode(String pgcode) {
        this.pgcode = pgcode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBalcashbook() {
        return balcashbook;
    }

    public void setBalcashbook(String balcashbook) {
        this.balcashbook = balcashbook;
    }

    public String getBalpassbook() {
        return balpassbook;
    }

    public void setBalpassbook(String balpassbook) {
        this.balpassbook = balpassbook;
    }

    public String getPassbooklastpageimg() {
        return passbooklastpageimg;
    }

    public void setPassbooklastpageimg(String passbooklastpageimg) {
        this.passbooklastpageimg = passbooklastpageimg;
    }

    public String getCashbooklastpageimg() {
        return cashbooklastpageimg;
    }

    public void setCashbooklastpageimg(String cashbooklastpageimg) {
        this.cashbooklastpageimg = cashbooklastpageimg;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }
}
