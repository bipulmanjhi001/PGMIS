package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PgmisChequeLoantbl extends SugarRecord {

    @Unique
    private String uuid;
    private String pgcode;
    private String grpcode;
    private String grpmemcode;
    private String isexported;
    private String entrydate;
    private String entryby;
    private String appliedforloan;
    private String chequedate;
    private String amount;
    private String remark;
    private String paymentmode;

    public PgmisChequeLoantbl() {

    }

    public PgmisChequeLoantbl(String uuid, String pgcode, String grpcode, String grpmemcode, String isexported, String entrydate, String entryby, String appliedforloan, String chequedate, String amount, String remark, String paymentmode) {
        this.uuid = uuid;
        this.pgcode = pgcode;
        this.grpcode = grpcode;
        this.grpmemcode = grpmemcode;
        this.isexported = isexported;
        this.entrydate = entrydate;
        this.entryby = entryby;
        this.appliedforloan = appliedforloan;
        this.chequedate = chequedate;
        this.amount = amount;
        this.remark = remark;
        this.paymentmode = paymentmode;
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

    public String getChequedate() {
        return chequedate;
    }

    public void setChequedate(String chequedate) {
        this.chequedate = chequedate;
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

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }
}
