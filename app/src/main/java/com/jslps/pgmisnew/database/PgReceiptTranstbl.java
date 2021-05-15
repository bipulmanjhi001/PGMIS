package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PgReceiptTranstbl extends SugarRecord {

    @Unique
    private String uuid;
    private String budgetcode;
    private String headname;
    private String date;
    private String amount;
    private String remark;
    private String pgcode;
    private String createdby;
    private String createdid;
    private String isexported;
    private String quantity;
    private String unittype;
    private String Paymentmode;
    private String BMID;

    public PgReceiptTranstbl() {
    }

    public PgReceiptTranstbl(String uuid, String budgetcode, String headname, String date, String amount, String remark, String pgcode, String createdby, String createdid, String isexported, String quantity, String unittype, String paymentmode, String BMID) {
        this.uuid = uuid;
        this.budgetcode = budgetcode;
        this.headname = headname;
        this.date = date;
        this.amount = amount;
        this.remark = remark;
        this.pgcode = pgcode;
        this.createdby = createdby;
        this.createdid = createdid;
        this.isexported = isexported;
        this.quantity = quantity;
        this.unittype = unittype;
        Paymentmode = paymentmode;
        this.BMID = BMID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBudgetcode() {
        return budgetcode;
    }

    public void setBudgetcode(String budgetcode) {
        this.budgetcode = budgetcode;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPgcode() {
        return pgcode;
    }

    public void setPgcode(String pgcode) {
        this.pgcode = pgcode;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreatedid() {
        return createdid;
    }

    public void setCreatedid(String createdid) {
        this.createdid = createdid;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getPaymentmode() {
        return Paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        Paymentmode = paymentmode;
    }

    public String getBMID() {
        return BMID;
    }

    public void setBMID(String BMID) {
        this.BMID = BMID;
    }
}
