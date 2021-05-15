package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class Pgcapitalsavetbl extends SugarRecord {

    private String Pgcode;
    private String Grpmemcode;
    private String Grpcode;
    private String Membername;
    private String Amount;
    private String Isexported;
    private String Uuid;
    private String createddate;
    private String Paymentdate;
    private String Paymentmode;

    public Pgcapitalsavetbl() {
    }

    public Pgcapitalsavetbl(String Pgcode, String grpmemcode, String grpcode, String membername, String amount, String isexported, String uuid, String createddate, String Paymentdate, String paymentmode) {
        this.Pgcode = Pgcode;
        Grpmemcode = grpmemcode;
        Grpcode = grpcode;
        Membername = membername;
        Amount = amount;
        Isexported = isexported;
        Uuid = uuid;
        this.createddate = createddate;
        this.Paymentdate = Paymentdate;
        this.Paymentmode = paymentmode;
    }

    public String getPgcode() {
        return Pgcode;
    }

    public void setPgcode(String pgcode) {
        pgcode = pgcode;
    }

    public String getGrpmemcode() {
        return Grpmemcode;
    }

    public void setGrpmemcode(String grpmemcode) {
        Grpmemcode = grpmemcode;
    }

    public String getGrpcode() {
        return Grpcode;
    }

    public void setGrpcode(String grpcode) {
        Grpcode = grpcode;
    }

    public String getMembername() {
        return Membername;
    }

    public void setMembername(String membername) {
        Membername = membername;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getIsexported() {
        return Isexported;
    }

    public void setIsexported(String isexported) {
        Isexported = isexported;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getPaymentdate() {
        return Paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.Paymentdate = paymentdate;
    }

    public String getPaymentmode() {
        return Paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.Paymentmode = paymentmode;
    }
}
