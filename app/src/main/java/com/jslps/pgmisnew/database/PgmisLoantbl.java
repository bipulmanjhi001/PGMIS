package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PgmisLoantbl extends SugarRecord {

    @Unique
    private String uuid;
    private String pgcode;
    private String grpcode;
    private String grpmemcode;
    private String itemcode;
    private String itemname;
    private String rate;
    private String unit;
    private String isexported;
    private String entrydate;
    private String entryby;
    private String appliedforloan;
    private String quantity;
    private String paymentdate;
    private String BMID;

    public PgmisLoantbl() {

    }

    public PgmisLoantbl(String uuid, String pgcode, String grpcode, String grpmemcode, String itemcode, String itemname, String rate, String unit, String isexported, String entrydate, String entryby, String appliedforloan, String quantity, String paymentdate, String BMID) {
        this.uuid = uuid;
        this.pgcode = pgcode;
        this.grpcode = grpcode;
        this.grpmemcode = grpmemcode;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.rate = rate;
        this.unit = unit;
        this.isexported = isexported;
        this.entrydate = entrydate;
        this.entryby = entryby;
        this.appliedforloan = appliedforloan;
        this.quantity = quantity;
        this.paymentdate = paymentdate;
        this.BMID = BMID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPgcode() {
        return pgcode;
    }

    public void setPgcode(String pgcode) {
        this.pgcode = pgcode;
    }

    public String getGrpcode() {
        return grpcode;
    }

    public void setGrpcode(String grpcode) {
        this.grpcode = grpcode;
    }

    public String getGrpmemcode() {
        return grpmemcode;
    }

    public void setGrpmemcode(String grpmemcode) {
        this.grpmemcode = grpmemcode;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getAppliedforloan() {
        return appliedforloan;
    }

    public void setAppliedforloan(String appliedforloan) {
        this.appliedforloan = appliedforloan;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getBMID() {
        return BMID;
    }

    public void setBMID(String BMID) {
        this.BMID = BMID;
    }
}
