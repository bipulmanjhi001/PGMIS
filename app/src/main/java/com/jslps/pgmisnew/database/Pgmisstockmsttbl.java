package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class Pgmisstockmsttbl extends SugarRecord {
    private String itemcode;
    private String itemname;
    private String itemunit;

    public Pgmisstockmsttbl() { }

    public Pgmisstockmsttbl(String itemcode, String itemname, String itemunit) {
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.itemunit = itemunit;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemunit() {
        return itemunit;
    }

    public void setItemunit(String itemunit) {
        this.itemunit = itemunit;
    }

    @Override
    public String toString() {
        return itemname;
    }
}
