package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PgPaymentTranstbl extends SugarRecord {

    @Unique
    private String Uuid;
    private String Budgetcode;
    private String Headname;
    private String Date;
    private String Amount;
    private String remark;
    private String PGCode;
    private String Createdby;
    private String Createdid;
    private String isexported;
    private String Paymentmode;
    private String qty;
    private String Paymentunit;
    private String BMID;

    //here in place of budgetcode budgetid is put,if budgetcode is needed it can be found from TblMstpgPaymentReceipthead

    public PgPaymentTranstbl() {
    }

    public PgPaymentTranstbl(String uuid, String budgetcode, String headname, String date, String amount, String remark, String PGCode, String createdby, String createdid, String isexported, String paymentmode, String qty, String paymentunit, String BMID) {
        Uuid = uuid;
        Budgetcode = budgetcode;
        Headname = headname;
        Date = date;
        Amount = amount;
        this.remark = remark;
        this.PGCode = PGCode;
        Createdby = createdby;
        Createdid = createdid;
        this.isexported = isexported;
        Paymentmode = paymentmode;
        this.qty = qty;
        Paymentunit = paymentunit;
        this.BMID = BMID;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public String getBudgetcode() {
        return Budgetcode;
    }

    public void setBudgetcode(String budgetcode) {
        Budgetcode = budgetcode;
    }

    public String getHeadname() {
        return Headname;
    }

    public void setHeadname(String headname) {
        Headname = headname;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPGCode() {
        return PGCode;
    }

    public void setPGCode(String PGCode) {
        this.PGCode = PGCode;
    }

    public String getCreatedby() {
        return Createdby;
    }

    public void setCreatedby(String createdby) {
        Createdby = createdby;
    }

    public String getCreatedid() {
        return Createdid;
    }

    public void setCreatedid(String createdid) {
        Createdid = createdid;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getPaymentmode() {
        return Paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        Paymentmode = paymentmode;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPaymentunit() {
        return Paymentunit;
    }

    public void setPaymentunit(String paymentunit) {
        Paymentunit = paymentunit;
    }

    public String getBMID() {
        return BMID;
    }

    public void setBMID(String BMID) {
        this.BMID = BMID;
    }
}
