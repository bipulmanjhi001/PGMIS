package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class MembershipFeeModel extends SugarRecord {
    private String Pgcode;
    private String Pgmemcode;
    private String Grpcode;
    private String Adapterposition;
    private String Amount;
    private String Updateamount;

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    private String payment_mode;



    public String getUpdateamount() {
        return Updateamount;
    }

    public void setUpdateamount(String updateamount) {
        Updateamount = updateamount;
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

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
