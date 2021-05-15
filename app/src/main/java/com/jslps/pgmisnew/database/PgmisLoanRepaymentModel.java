package com.jslps.pgmisnew.database;

import com.orm.dsl.Unique;

public class PgmisLoanRepaymentModel {

    @Unique
    private String loanid;
    private String itemuuids;
    private String pgcode;
    private String grpcode;
    private String grpmemcode;
    private String entrydate;
    private String isexported;
    private String amount;
    private String paymentmode;

    private String entryby;
    private String appliedforloan;
    private String chequedate;
    private String remark;

    public PgmisLoanRepaymentModel(){

    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getItemuuids() {
        return itemuuids;
    }

    public void setItemuuids(String itemuuids) {
        this.itemuuids = itemuuids;
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

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
