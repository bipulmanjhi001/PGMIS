package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class PgCapitalSavetbl  extends SugarRecord {

    private String Pgcode;
    private String Grpmemcode;
    private String Grpcode;

    private String Membername;
    private String amount;
    private String Isexported;

    private String Uid;
    private String createddate;
    private String paymentdate;

    private String paymentmode;

    public PgCapitalSavetbl(){

    }

    public PgCapitalSavetbl(String pgcode, String grpmemcode, String grpcode, String membername, String amount, String isexported, String uid, String created_date, String payment_date, String payment_mode) {
        Pgcode = pgcode;
        Grpmemcode = grpmemcode;
        Grpcode = grpcode;
        Membername = membername;
        this.amount = amount;
        Isexported = isexported;
        Uid = uid;
        this.createddate = created_date;
        this.paymentdate = payment_date;
        this.paymentmode = payment_mode;
    }

    public String getPgcode() {
        return Pgcode;
    }

    public void setPgcode(String pgcode) {
        Pgcode = pgcode;
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
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIsexported() {
        return Isexported;
    }

    public void setIsexported(String isexported) {
        Isexported = isexported;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }
}
