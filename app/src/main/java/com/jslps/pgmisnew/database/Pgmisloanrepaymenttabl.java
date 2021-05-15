package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class Pgmisloanrepaymenttabl extends SugarRecord {

    private String pgcode;
    private String uuid;
    private String loanid;
    private String entrydate;
    private String amount;
    private String isexported;
    private String selectedPaymentMode;
    private String paymentdate;

    public Pgmisloanrepaymenttabl() {

    }

    public Pgmisloanrepaymenttabl(String pgcode, String uuid, String loanid, String entrydate, String amount, String isexported, String selectedPaymentMode, String paymentdate) {
        this.pgcode = pgcode;
        this.uuid = uuid;
        this.loanid = loanid;
        this.entrydate = entrydate;
        this.amount = amount;
        this.isexported = isexported;
        this.selectedPaymentMode = selectedPaymentMode;
        this.paymentdate = paymentdate;
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

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getSelectedPaymentMode() {
        return selectedPaymentMode;
    }

    public void setSelectedPaymentMode(String selectedPaymentMode) {
        this.selectedPaymentMode = selectedPaymentMode;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }
}
