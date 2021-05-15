package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PhCollectingOutputtbl extends SugarRecord {

    @Unique
    private String uuid;
    private String Pgcode;
    private String Pgmemcode;
    private String Grpcode;
    private String Adapterposition;
    private String selecteddate;
    private String type;
    private String producername;
    private String qty;
    private String rate;
    private String isexported;
    private String entrydate;
    private String entryby;

    public PhCollectingOutputtbl() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPgcode() {
        return Pgcode;
    }

    public void setPgcode(String pgcode) {
        Pgcode = pgcode;
    }

    public String getPgmemcode() {
        return Pgmemcode;
    }

    public void setPgmemcode(String pgmemcode) {
        Pgmemcode = pgmemcode;
    }

    public String getGrpcode() {
        return Grpcode;
    }

    public void setGrpcode(String grpcode) {
        Grpcode = grpcode;
    }

    public String getAdapterposition() {
        return Adapterposition;
    }

    public void setAdapterposition(String adapterposition) {
        Adapterposition = adapterposition;
    }

    public String getSelecteddate() {
        return selecteddate;
    }

    public void setSelecteddate(String selecteddate) {
        this.selecteddate = selecteddate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProducername() {
        return producername;
    }

    public void setProducername(String producername) {
        this.producername = producername;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

    public String getEntryby() {
        return entryby;
    }

    public void setEntryby(String entryby) {
        this.entryby = entryby;
    }
}
